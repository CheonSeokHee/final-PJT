package kr.or.fineapple.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community/*")
public class CommunityController {
	
	@GetMapping(value = "getViewTest")
	public String getViewTest() {
		System.out.println("getViewTest ��Ʈ�ѷ� ��ħ");
		return "community/addPost.html";
	}
	
	
	
}

