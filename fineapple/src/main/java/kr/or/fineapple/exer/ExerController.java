package kr.or.fineapple.exer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.ExerServ;
import kr.or.fineapple.domain.Routine;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.service.exer.ExerService;


@Controller
@RequestMapping("/exer/*")
public class ExerController {
	
	@Autowired
	@Qualifier("ExerServiceImpl")
	private ExerService exerService;

	public ExerController() {
	}


@GetMapping("addUserService")
public String addUserService(HttpServletRequest request,Model model) throws Exception  {
	
    System.out.println("get: addUserService");


	if((User)request.getSession(true).getAttribute("user")!=null) {
		
		User user =(User)request.getSession(true).getAttribute("user");
		
		System.out.println(user);
	
		ExerServ serv = exerService.getUserService(user.getUserId());
		
		System.out.println(serv);
		
		if(serv!=null) {
				if(serv.getUserServiceNo()!=0) {
		
					model.addAttribute("user",user);
					model.addAttribute("exerServ",serv);
					
					return "exer/getUserService.html";
					
					}else {
						return "exer/addUserService.html";
						}
		}else {
			model.addAttribute("user",user);
			
			return "exer/addUserService.html";}
		
	}else {
		
		return "user/login";
		
	}
	
}
    
@PostMapping("addUserService")
public String addUserService(@ModelAttribute("ExerServ")ExerServ serv,
							HttpServletRequest request,
							Model model) throws Exception{
	
	System.out.println("post:addUserService");
	
	System.out.println(serv);
	
	User user =(User)request.getSession(true).getAttribute("user");
	String userId = user.getUserId();
	serv.setUserId(userId);
	
	if(user.getExerServiceNo()==0) {
		
	exerService.addUserService(serv);
	
	}else {
		
		exerService.updateUserService(serv);
	}
			
	serv = exerService.getUserService(userId);
	model.addAttribute("exerServ",serv);
	model.addAttribute("user",user);
	
	

	return "exer/getUserService.html";
}




@GetMapping("getUserService")
public String getUserService(Model model, HttpServletRequest request) {
	
	return "exer/getUserService.html";
}




@GetMapping("updateUserService")
public String updateUserService(Model model, HttpServletRequest request) throws Exception {
	System.out.println("get:updateUserService");
	
	User user =(User)request.getSession(true).getAttribute("user");
	System.out.println(user);

	ExerServ serv = exerService.getUserService(user.getUserId());
	
	
	model.addAttribute("user",user);
	model.addAttribute("exerServ",serv);
	
	return "exer/updateUserService.html";
		
	
}
	


@RequestMapping("getExerList")
public String getExerList( @ModelAttribute("search") Search search, Model model) throws Exception {
	
	  System.out.println("getExerList");
 
	  
	  Map<String, Object> map = exerService.getExerList(search);
	  
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
			  
		
	return "exer/getExerList.html";	
	
}



@GetMapping("getExer")
public String getExer(@ModelAttribute("exer") Exer exer, Model model) throws Exception{
	
	
	exer = exerService.getExer(exer.getExerNo());
	
	System.out.println("getExer");
	
	if(exer.getExerVideoName() == null) {
		exer.setExerVideoName("null");
		
		System.out.println("exerVideoName�� nullüũ" + exer);
		
		model.addAttribute("exer", exer);
		
	}else {
	
	System.out.println("exerVideoName�� nullüũ" + exer);
		
	model.addAttribute("exer", exer);
	
	}
	
	return "exer/getExer.html";
	
	
}



@GetMapping("getUpdateExer")
public String getUpdateExer(@ModelAttribute("exer") Exer exer, Model model) throws Exception {
	
	
	exer= exerService.getExer(exer.getExerNo());
	
	model.addAttribute("exer", exer);
	
	System.out.println("getUpdateExer");
	
	
	return "exer/updateExer.html";
	
}


private static String FILE_SERVER_PATH = "C:\\Users\\82105\\git\\fineapple\\fineapple\\src\\main\\resources\\templates\\assets\\video";

@PostMapping("postUpdateExer")
public String postUpdateExer(@ModelAttribute("exer") Exer exer , Model model, @RequestParam("exerFile") MultipartFile exerFile ) throws Exception {
	
	if(!exerFile.getOriginalFilename().isEmpty()) {
		
		exerFile.transferTo(new File(FILE_SERVER_PATH, exerFile.getOriginalFilename()));
		
		model.addAttribute("msg", "File uploaded successfully");
		
		System.out.println("����� �������ε� ����~");
		
	}else {
		
		model.addAttribute("msg", "Please select a valid mediaFile..");
		
	}
	
	exer.setExerVideoName(exerFile.getOriginalFilename());
	
	System.out.println("postUpdateExer");
	
	exerService.postUpdateExer(exer);
	

	model.addAttribute("exer", exer);
	
	
	return "exer/getExer";
	
}

@RequestMapping("deleteExer")
public String deleteExer(@ModelAttribute("exer") Exer exer , Model model) throws Exception {
	
	System.out.println("deleteExer");
	
	exerService.deleteExer(exer.getExerNo());
	
	Search search = new Search();
	
	search.setCurrentPage(1);
	search.setPageSize(1);
	search.setSearchCondition(0);
	search.setSearchKeyword("");
	
	  Map<String, Object> map = exerService.getExerList(search);
	  
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
	
	
	
	model.addAttribute("exer", exer);
	
	return "exer/getExerList";
}


@PostMapping("postAddExer")
public String postAddExer(@ModelAttribute("exer") Exer exer , Model model, @RequestParam("exerFile") MultipartFile exerFile ) throws Exception {

	
	if(!exerFile.getOriginalFilename().isEmpty()) {
		
		exerFile.transferTo(new File(FILE_SERVER_PATH, exerFile.getOriginalFilename()));
		
		model.addAttribute("msg", "File uploaded successfully");
		
		System.out.println("����� ��Ͼ��ε� ����~");
		
	}else {
		
		model.addAttribute("msg", "Please select a valid mediaFile..");
		
	}
	
	exer.setExerVideoName(exerFile.getOriginalFilename());
	
	exerService.addExer(exer);
	
	System.out.println("addExer");
	
	Search search = new Search();
	
	search.setCurrentPage(1);
	search.setPageSize(1);
	search.setSearchCondition(0);
	search.setSearchKeyword("");
	
	  Map<String, Object> map = exerService.getExerList(search);
	  
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
	
		
	
	model.addAttribute("exer", exer);
	
	//����Ʈ�� �ٷ� �ȶߴ� ������ �����, Ű���� �������� �������� �ʱ� ������ �ٽ� ���� ���༭ ������!!!
	return "exer/getExerList";
}


@GetMapping("getAddExer")
public String getAddExer(@ModelAttribute("exer") Exer exer, Model model) throws Exception {
	
	
	System.out.println("getAddExer");
	
	
	return "exer/addExer.html";	
	
}





@RequestMapping(value="searchExerPlace")
public String searchExerPlace() {
	
	
	System.out.println("searchExerPlace");

	
	return "exer/searchExerPlace.html";
	
	
}



@RequestMapping(value="exerIndex")
public String timer(Model model,HttpServletRequest request) {
	
	System.out.println("timer");
	
	
	User user =(User)request.getSession(true).getAttribute("user");
 
	
	model.addAttribute("user", user);
	
	return "exer/exerIndex.html";
	
	
}



@GetMapping("getRoutineList")
public String getRoutineList(Model model,HttpServletRequest request) throws Exception {
	
	
	System.out.println("getRoutineList");
	

	User user =(User)request.getSession(true).getAttribute("user");

	ExerServ serv = exerService.getUserService(user.getUserId());
	
	System.out.println(serv);

	Map<String,Object> map = new HashMap<String,Object>();
	
	
	map = exerService.getRoutineList(serv.getUserServiceNo());
	
	System.out.println(map);
	
	
	model.addAttribute("list", map.get("list"));
	
	return "exer/getRoutineList.html";

}



@GetMapping("getRoutine")
public String getRoutineInfoList(@ModelAttribute("routine") Routine routine, Model model) throws Exception {

	routine = exerService.getRoutine(routine.getRoutineNo());
	
	
	System.out.println("getRoutine");
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	
	map = exerService.getRoutineInfoList(routine.getRoutineNo());
		
	
	
	model.addAttribute("list", map.get("list"));
	model.addAttribute("routine", routine);
	
	
	return "exer/getRoutine.html";
	

}

@GetMapping("addDailyBurnning")
public String getAddDailyBurnning(Model model, @RequestParam("addDailyBurnning")int exerNo,HttpServletRequest request) throws Exception {

System.out.println("getAddDailyBurnning");

Exer exer = new Exer();

exer = exerService.getExer(exerNo);


System.out.println(exer);


User user =(User)request.getSession(true).getAttribute("user");


/*
 * System.out.println(user);
 * 
 * 
 * ExerServ serv = exerService.getUserService(user.getUserId());
 * 
 * 
 * System.out.println(serv);
 * 
 * Map<String,Object> map = new HashMap<String,Object>();
 * 
 * map = exerService.getRoutineList(serv.getUserServiceNo());
 */

//System.out.println(map);

//model.addAttribute("list",map.get("list"));
model.addAttribute("exer", exer);


return "exer/addDailyBurnning :: addDailyBurnning";


}


@PostMapping("addDailyBurnning")
public String postAddDailyBurnning() {

System.out.println("postAddDailyBurnning");





return "exer/getaddDailyBurnning.html";


}




@GetMapping(value="getDailyBurnning")
public String getDailyBurnning() {

System.out.println("getAddDailyBurnning");





return "exer/getDailyBurnning.html";


}





@GetMapping("addRoutineInfo")
public String getAddRoutineInfo(Model model, @RequestParam("routineInfo")int exerNo,HttpServletRequest request) throws Exception {
	
	
	System.out.println("getAddRoutineInfo");
	
	Exer exer = new Exer();
	
	exer = exerService.getExer(exerNo);
	
	
	System.out.println(exer);
	
	
	User user =(User)request.getSession(true).getAttribute("user");
	

	System.out.println(user);
	

	ExerServ serv = exerService.getUserService(user.getUserId());
	

	System.out.println(serv);
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	map = exerService.getRoutineList(serv.getUserServiceNo());

	//System.out.println(map);
	
	model.addAttribute("list",map.get("list"));
	model.addAttribute("exer", exer);
	
	
	return "exer/addRoutineInfo :: addRoutineInfo";
	

}

@PostMapping("addRoutineInfo")
public String postAddRoutineInfo(Model model, @RequestParam("exerNo")int exerNo,
		@RequestParam("anExerKcal")Double anExerKcal ,
		@RequestParam("routineNo")int routineNo, 
		@RequestParam("exerLv")String exerLv,
		@RequestParam("anExerTime")String anExerTime) throws Exception {
	


	
	System.out.println("postAddRoutineInfo");
	
	System.out.println("���Ӹ�~~~" + exerNo + anExerKcal + routineNo + exerLv + anExerTime);
	

	Routine routine = new Routine();
	
	Exer exer = new Exer();
	
	routine = exerService.getRoutine(routineNo);
	
	System.out.println("1"+routine);
	
	int hour = Integer.parseInt(routine.getRoutineTime().split(":")[0]) * 60;   // 60
	int min = Integer.parseInt(routine.getRoutineTime().split(":")[1]) + hour; // 90
	
	System.out.println(hour); // 60
	System.out.println(min);  // 90
	
	System.out.println("������" + min);  //01:30:00

	int routineTime = min + Integer.parseInt(anExerTime); // 150
	
	System.out.println(routineTime);
	
	

	String resultTime1;
	String resultTime2;
	
	
	int a = Integer.parseInt(anExerTime) / 60;
	
	int b =Integer.parseInt(anExerTime) % 60;
	
	
	if(b == 0) {
		
		resultTime1 = a + ":" + b + "0" + ":" + "00";
		
	} else {
	
	resultTime1 = a + ":" + b + ":" + "00";
	
	}
	
	
	
	int c = (routineTime) / 60;
	
	int d = (routineTime) % 60;
	
	
	if(d == 0) {
		
		 resultTime2 = c + ":" + d +"0"+ ":" + "00";
		
	}
	
	 resultTime2 = c + ":" + d + ":" + "00";
	
	System.out.println(resultTime2);
	
	
	routine.setExerNo(exerNo);
	
	
	exer.setExerNo(routine.getExerNo());
	
	routine.setExer(exer);
	routine.setExerLv(exerLv);
	routine.setAnExerKcal(anExerKcal);
	routine.setRoutineKcal(routine.getRoutineKcal() + anExerKcal);
	routine.setAnExerTime(resultTime1);
	routine.setRoutineTime(resultTime2);
	
	
	System.out.println("2"+routine);
	
	
	exerService.addRoutineInfo(routine);
	
	
	return "exer/getRoutineList";

	
}




@GetMapping("addRoutine")
public String addRoutine() {

	
	
	System.out.println("addRoutine");
	

	return "exer/getExerList.html";
	
}



@GetMapping("recommandExerList")
public String recommandExerList(Model model, HttpServletRequest request) throws Exception {
	
	System.out.println("recommandExerList");
	
	User user =(User)request.getSession().getAttribute("user");

	
	System.out.println(user.getUserId());
	Double sumIntakeKcal = exerService.sumIntakeKcal(user.getUserId());
	System.out.println(sumIntakeKcal);
	
	user = exerService.needDaliyIntakeKcal(user.getUserId());
	System.out.println(user);
	
	
	Double  dailyIntakeKcal	= 0.0;
	Double 	totaldailyIntakeKcal = 0.0;
	
	if(user.getGender().equals("male")) {
		
	  
	  dailyIntakeKcal	= 66 + (13.7 * user.getWeight() + 5 * user.getHeight() - 6.8 * user.getAge());
		
		
	} else {
		
	 dailyIntakeKcal = 655 + (9.6 * user.getWeight() + 1.8 * user.getHeight() - 4.7 * user.getAge());
		
		
	}
	
	System.out.println(dailyIntakeKcal);
	
	if(user.getServiceTrgt().equals("ü������")) {
		
		totaldailyIntakeKcal= dailyIntakeKcal * 1.55;
		
	} if (user.getServiceTrgt().equals("ü������")){
		
		totaldailyIntakeKcal= dailyIntakeKcal * 1.375;
		
	} else {
		
		totaldailyIntakeKcal = dailyIntakeKcal * 1.2;
		
	}
	
	System.out.println(totaldailyIntakeKcal);
	
	
	
	Double remainKcal = (totaldailyIntakeKcal - sumIntakeKcal) ;
	
	
	
	remainKcal = Math.round(remainKcal*100)/100.0;
	
	
	System.out.println(" �ܿ� Į�θ� �Դϴ�   " + remainKcal);
	
	
	if(remainKcal >= 0) {
		
		remainKcal = 0.0;
		
	}if(remainKcal < 0) {
		
		remainKcal = remainKcal * (-1);
		
	}
	
	List list = exerService.recommandExerList(Math.abs(remainKcal));
	
	
	model.addAttribute("overKcal", remainKcal);
	model.addAttribute("list", list);
	
	
	return "exer/recommandExerList.html";
	
	
}


}
