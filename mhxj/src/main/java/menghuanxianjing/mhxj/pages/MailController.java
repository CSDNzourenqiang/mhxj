package menghuanxianjing.mhxj.pages;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import menghuanxianjing.mhxj.App;
import menghuanxianjing.mhxj.base.BaseController;
import menghuanxianjing.mhxj.model.Server;
import menghuanxianjing.utils.Constants;

@Controller
public class MailController  extends BaseController{

	@RequestMapping(value = "/mail.php")
	public ModelAndView mail() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/mail/mail");
		
		List<Server> area_list=getServers();
		modelAndView.addObject("area_list",area_list);
		modelAndView.addObject("items",Constants.itemString.replace("\"", ""));
		return modelAndView;
	}
}
