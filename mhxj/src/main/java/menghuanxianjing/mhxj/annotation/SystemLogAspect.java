package menghuanxianjing.mhxj.annotation;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import menghuanxianjing.mhxj.model.UserModel;
import menghuanxianjing.mhxj.pojo.Action;
import menghuanxianjing.mhxj.service.ActionService;
import menghuanxianjing.utils.HttpUtils;
import net.sf.json.JSONObject;

@Aspect
@Component
@SuppressWarnings("all")
public class SystemLogAspect {
	@Resource
	ActionService actionService;
	
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    
    //service层切点
    @Pointcut("@annotation(menghuanxianjing.mhxj.annotation.SystemServiceLog)")
    public void serviceAspect() {
    	
    }
    //controller层切点
    @Pointcut("@annotation(menghuanxianjing.mhxj.annotation.SystemControllerLog)")
    public void controllerAspect() {
    	System.out.println(123);
    }
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
    	 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         HttpSession session = request.getSession();
         //读取session中的用户
         UserModel user = (UserModel) session.getAttribute("user");
         if (user==null) {
			return;
		}
         String ip = HttpUtils.getIpAddress(request);
         String content="";
         Map<String, String[]> paramsMap=request.getParameterMap();
         Set<String> kSet=paramsMap.keySet();
         for (String key : kSet) {
			for(String string:paramsMap.get(key)) {
				content+=key+":"+string+";";
			}
		}
			System.out.println(content);

         try {
             //*========控制台输出=========*//
             System.out.println("==============前置通知开始==============");
             System.out.println("请求方法" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
             System.out.println("方法描述：" + getControllerMethodDescription(joinPoint));
             System.out.println("请求人："+user.getAccount());
             System.out.println("请求ip："+ip);
  
             //*========数据库日志=========*//
             Action action = new Action();
             action.setActionDes(getControllerMethodDescription(joinPoint));
             action.setActionType("0");
             action.setActionIp(ip);
             action.setContent(content);
             action.setUserId(user.getId());
             action.setAccount(user.getAccount());
             action.setActionTime(new Date());
             //保存数据库
             actionService.add(action);
  
         }catch (Exception e){
             //记录本地异常日志
        	 e.printStackTrace();
             logger.error("==前置通知异常==");
             logger.error("异常信息：{}",e.getMessage());
         }
    }
    /**
     * @Description  异常通知 用于拦截service层记录异常日志
     * @date 
     */
    @AfterThrowing(pointcut = "serviceAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable e){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        UserModel user = (UserModel) session.getAttribute("user");
        //获取请求ip
        String ip = HttpUtils.getIpAddress(request);
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs()!=null&&joinPoint.getArgs().length>0){
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params+= JSONObject.fromObject(joinPoint.getArgs()[i])+";";
            }
        }
        try{
            /*========控制台输出=========*/
            System.out.println("=====异常通知开始=====");
            System.out.println("异常代码:" + e.getClass().getName());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));
            System.out.println("请求人:" + user.getAccount());
            System.out.println("请求IP:" + ip);
            System.out.println("请求参数:" + params);
            /*==========数据库日志=========*/
            Action action = new Action();
            action.setActionDes(getServiceMethodDescription(joinPoint));
            action.setActionType("1");
            action.setUserId(user.getId());
            action.setActionIp(ip);
            action.setActionTime(new Date());
            //保存到数据库
            actionService.add(action);
        }catch (Exception ex){
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
    }
    /**
     * @Description  获取注解中对方法的描述信息 用于service层注解
     * @date 
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint)throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
    /**
     * @Description  获取注解中对方法的描述信息 用于Controller层注解
     * @date 
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

}
