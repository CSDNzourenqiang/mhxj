package menghuanxianjing.mhxj.pages;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import menghuanxianjing.mhxj.base.BaseController;
import menghuanxianjing.mhxj.model.Server;
import menghuanxianjing.mhxj.pojo.NoticeEntity;
import menghuanxianjing.mhxj.service.NoticeService;
import menghuanxianjing.mhxj.service.PlayerService;
import menghuanxianjing.utils.Constants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class NoticeController extends BaseController{
	
	@Autowired
	NoticeService noticeService;
	
	
	@RequestMapping(value = "/notice.php")
	public ModelAndView user() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/notice/notice");
		List<NoticeEntity> list=noticeService.getAllNotice();
		JSONObject jsonObject=new JSONObject();
		for(NoticeEntity noticeEntity:list) {
			if (noticeEntity.getName().equals("svrsetter")) {
				jsonObject=JSONObject.fromObject(noticeEntity.getData());
			}
		}
		JSONObject notice_json=JSONObject.fromObject(jsonObject.get("notice"));
		JSONArray jsonArray=new JSONArray();
		for (int i = 1; i <=10; i++) {
			if (notice_json.containsKey(i+"")) {
				//有公告
				JSONObject jsonObject2=notice_json.getJSONObject(i+"");
				jsonArray.add(jsonObject2);
			}
		}
		modelAndView.addObject("noticeList", jsonArray);
		return modelAndView;
	}
}
