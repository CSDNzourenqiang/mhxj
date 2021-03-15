package menghuanxianjing.mhxj.pages;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import menghuanxianjing.mhxj.base.BaseController;
import menghuanxianjing.mhxj.model.Server;
import menghuanxianjing.utils.Constants;

@Controller
public class UserController extends BaseController{
	@RequestMapping(value = "/user.php")
	public ModelAndView user() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/user/user");
		
		modelAndView.addObject("area_list", getServers());
		return modelAndView;
	}
	@RequestMapping(value = "/user_test.php")
	public String user1() {
		return "/user/user";
	}
}
