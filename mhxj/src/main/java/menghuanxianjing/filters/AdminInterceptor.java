package menghuanxianjing.filters;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import menghuanxianjing.mhxj.model.UserModel;
import menghuanxianjing.mhxj.service.UserService;

public class AdminInterceptor implements HandlerInterceptor{

	@Autowired
	UserService userService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//获取session中的token，比较，如果token不一样，返回登陆页面
		UserModel userModel=(UserModel)request.getSession().getAttribute("user");
		if (userModel!=null&&userModel.getAccount()!=null&&userService.isValidToken(userModel.getToken())) {
			return true;
		}
		response.sendRedirect("/api_logout.php");
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println(123412);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
