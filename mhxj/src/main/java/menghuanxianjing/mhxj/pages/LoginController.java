package menghuanxianjing.mhxj.pages;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import menghuanxianjing.mhxj.model.UserModel;
import menghuanxianjing.mhxj.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/api_logout.php")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/login/login");
		return modelAndView;
	}
	@RequestMapping(value = "/api_login.php",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String login(HttpServletRequest request,@RequestParam Map<String, Object> modelMap) {
		UserModel userModel=new UserModel();
		userModel.setAccount((String)modelMap.get("account"));
		userModel.setPass((String)modelMap.get("pass"));
		
		UserModel userModel2=userService.findUserByPassword(userModel);
		String token=UUID.randomUUID().toString();
		if (userModel2!=null) {
			userModel2.setToken(token);
			userModel.setToken(token);
			userService.updateUser(userModel);
			request.getSession().setAttribute("user", userModel2);
			return "redirect:/index.php";
		}else {
			return "redirect:/api_logout.php";
		}
		
	}
	
	@RequestMapping(value = "/api_do.php")
	public String dologin() {
		return "/index.php";
	}
}
