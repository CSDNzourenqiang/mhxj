package menghuanxianjing.mhxj.pages;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import menghuanxianjing.mhxj.base.BaseController;
import menghuanxianjing.mhxj.model.Server;
import menghuanxianjing.utils.Constants;

@Controller
public class GMOrderController extends BaseController{

	
	@RequestMapping(value = "/gm_cmd.php")
	public ModelAndView gmcmd() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/gmcmd/gmcmd");
		modelAndView.addObject("area_list", getServers());
		return modelAndView;
	}
}
