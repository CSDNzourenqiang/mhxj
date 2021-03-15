package menghuanxianjing.mhxj.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import menghuanxianjing.mhxj.base.BaseController;


@Controller
public class HomeController extends BaseController{
	
	@RequestMapping(value = "/index.php")
	public ModelAndView home() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/user/user");
		modelAndView.addObject("area_list", getServers());
		return modelAndView;
	}
	
	
}
