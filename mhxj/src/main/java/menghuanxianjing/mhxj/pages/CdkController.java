package menghuanxianjing.mhxj.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CdkController {
	
	@RequestMapping(value = "/cdk.php")
	public String cdk() {
		return "/cdk/cdk";
	}

}
