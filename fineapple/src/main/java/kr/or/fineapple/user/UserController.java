package kr.or.fineapple.user;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.service.user.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Value("${file.upload.directory}")
	private String filePath ;
	
	public UserController(){
		System.out.println("UserController����");
	}
	
	@RequestMapping(value="banner")
    public String banner() {
    	System.out.println("banner");
    	return "index/index.html";
    }
	
	@RequestMapping(value="login",method = RequestMethod.GET)
    public String login(){
		System.out.println("redirect:/user/login.html:GET");
		return "user/login.html";
    }
	
	@RequestMapping(value="login/redirect",method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, HttpSession session) throws Exception{
		
		System.out.println("login�õ�:POST");
		User userDB = userService.getUser(user.getUserId());
		if(user.getPassword().equals(userDB.getPassword())) {
			session.setAttribute("user",userDB);
		}
		System.out.println("user : "+userDB);
		System.out.println("login �����߳���");
		
		return "redirect:/";
		}
	
	@RequestMapping( value="logout",method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		System.out.println("�α׾ƿ� �õ��մϴ�");
		
		session.invalidate();
		
		System.out.println("�α׾ƿ� �Ƴ���");
		return "redirect:/";
	}
	
	
	@RequestMapping(value="addUser")
	public String addUser(){
		System.out.println("UserController:addUser()");
		return "user/addUser.html";
	}
	
	
	@RequestMapping(value="addUser/redirect", method= RequestMethod.POST)
	public String addUserRedirect(@ModelAttribute("user") User user , @RequestParam(value="userImg1", required = false) MultipartFile file) throws Exception {  
		System.out.println("addUserRedirect");
		System.out.println("user�� ������" + user);
		
		
		if(!file.getOriginalFilename().isEmpty()) {
			System.out.println("if�� ����");
			file.transferTo(new File(filePath, file.getOriginalFilename()));
			user.setUserImg(file.getOriginalFilename());
			
		}
		else {
			System.out.println("�⺻�̹���");
			user.setUserImg("defaultProfile.jpg"); 
		}

		user.setStrdWtrIntake(user.getWeight()*0.03);	//�������м��뷮 ����(������*0.03L) ����
		userService.addUser(user);
		System.out.println("user:"+user.toString());
		System.out.println("ȸ������ �Ƴ���");
		return "redirect:/user/login";
	}
	
	@RequestMapping(value="getUser",method=RequestMethod.GET)
	public String getUser(@RequestParam("userId") String userId,Model model)throws Exception{
		System.out.println("getUser:POST ���Գ���");
		
		User user = userService.getUser(userId);
		
		model.addAttribute("user",user);
		
		return "user/getUser.html";
		
	}
	

	
	

}
