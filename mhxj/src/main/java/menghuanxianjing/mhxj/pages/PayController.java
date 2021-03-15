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
public class PayController extends BaseController{
	@RequestMapping("/PayGM.php")
	public ModelAndView pay() {
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.addObject("area_list", getServers());
		modelAndView.addObject("items",Constants.itemString.replace("\"", ""));
		
		modelAndView.setViewName("/paygm/paygm");
		return modelAndView;
	}
}
