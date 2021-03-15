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
public class StatController extends BaseController{
	@RequestMapping(value = "/stat.php")
	public ModelAndView user() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/stat/stat");		
		modelAndView.addObject("area_list", getServers());
		return modelAndView;
	}
}
