package kr.or.fineapple.service.user;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	@RequestMapping(value="login",method=RequestMethod.GET )
    public ModelAndView login(){
		System.out.println("/user/login:GET");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/user/login.html");
		System.out.println("redirect:/user/login.html:GET");
		return mav;
    }
	
	@RequestMapping(value="loginResult",method=RequestMethod.POST )
    public ModelAndView login(HttpSession session) throws Exception{
		System.out.println("/user/login:POST");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index/index.html");
		System.out.println("index������");
		return mav;
    }
	
}
