package kr.or.fineapple.community;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Group;
import kr.or.fineapple.domain.community.GroupUser;
import kr.or.fineapple.domain.community.MtmQna;
import kr.or.fineapple.domain.community.Report;
import kr.or.fineapple.service.community.CommunityService;

@Controller
@RequestMapping("/community/*")
public class CommunityController {
	
	public CommunityController() {
		System.out.println(getClass().getName() + "생성함");
	}
	
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	
	//////////////////////////////////////////////////////////////////////////////////
	
	
	//@RequestMapping(value = "getBoard", method = RequestMethod.GET)
	@GetMapping(value = "getBoard")
	public String getPostList(Model model) {
		
		List<Board> list = communityService.getPostList();
		
		model.addAttribute("list", list);
		
		
		return "community/getBoard.html";
		
	}
	
	@GetMapping(value = "getPost")
	public String getViewTest(@ModelAttribute("board") Board board, Model model, HttpServletRequest request) {
		
		User usersys = (User)request.getSession(true).getAttribute("user");
		
		System.out.println(usersys);
		
		board.setUser((User)request.getSession().getAttribute("user"));
		
		Map map =  communityService.getPost(board);
		
		System.out.println(map.get("board")+"Scope에 담기전 Board");
		
		model.addAttribute("map", map);
		 
		return "community/getPost.html";
	}
	
	
	
	
	@GetMapping(value = "addPostView")
	public String addPostView() {
		
		System.out.println("addPostView 컨트롤러 거침");
		
		return "community/addPost.html";
	}
	
	

	@PostMapping(value = "addPost")
	public String addPost(@ModelAttribute Board board, @ModelAttribute Group group, HttpServletRequest request) {
		
		board.setUser((User)request.getSession().getAttribute("user"));
			
		communityService.addPost(board);
		
		return "redirect:/community/getBoard";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////
	
	
	
	@PostMapping(value = "addGroupView")
	public String addGroupView() {
		
		System.out.println("addGroupView 컨트롤러 거침ㅁ");
		
		return "community/addGroupView :: reportPostView";
	}
	
	
	@RequestMapping(value = "getMyGroupList", method = RequestMethod.GET)
	public ModelAndView getMyGroupList(HttpServletRequest request) {
		
		GroupUser groupUser = new GroupUser();
		
		groupUser.setUser((User)request.getSession(true).getAttribute("user"));
		
		groupUser.setGroupStt(4);
		
		List<Group> list =   communityService.getGroupInterGroup(groupUser);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("community/getMyGroupList.html");
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	
	@GetMapping(value = "getUserSerach")
	public String getUserList() {
		
		
		
		return "community/getUserSerach.html";
	}
	

//	@GetMapping(value="getAlarmList")
//	public List getAlarmList() {
//
//		return communityService.getAlarmList();
//	}
	
	
//	@RequestMapping(value="reportPostView", method = RequestMethod.POST)
//	public String Test(@RequestBody String str) {
//		System.out.println(str);
//		System.out.println("ddddddddddddddddd");
//		return null;
//	}
	
	@PostMapping(value = "reportPostView")
	public String reportPostView(HttpServletRequest request, Model model, @RequestBody String str) {
			
		System.out.println(str);
		
		System.out.println(request.getParameter("TrgtNo"));
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str);
		
		Report report = new Report();
		
		report.setUser((User)request.getSession(true).getAttribute("user"));
		
		User reportedUser = new User();
		
		reportedUser.setUserId(jsonObject.get("userId").toString());
		
		report.setReportedUser(reportedUser);
		
		report.setReportCate(jsonObject.get("reportCate").toString());
		
		report.setTrgtNo(Integer.valueOf((String)jsonObject.get("TrgtNo")));
		

		
		System.out.println("============================================");
		
		System.out.println("referer:"+request.getHeader("referer"));

		System.out.println("user-agent:"+request.getHeader("user-agent"));

		System.out.println("host:"+request.getHeader("host"));

		System.out.println("WL-Proxy-Client-IP:"+request.getHeader("WL-Proxy-Client-IP"));

		System.out.println("Proxy-Client-IP:"+request.getHeader("Proxy-Client-IP"));

		System.out.println("X-Forwarded-For:"+request.getHeader("X-Forwarded-For"));

		System.out.println("HTTP_CLIENT_IP:"+request.getHeader("HTTP_CLIENT_IP"));

		System.out.println("HTTP_X_FORWARDED_FOR:"+request.getHeader("HTTP_X_FORWARDED_FOR"));

		System.out.println("getContextPath:"+request.getContextPath());

		System.out.println("getProtocol:"+request.getProtocol());

		System.out.println("getQueryString:"+request.getQueryString());

		System.out.println("getRemoteAddr:"+request.getRemoteAddr());

		System.out.println("getRequestURI:"+request.getRequestURI());

		System.out.println("getServerName:"+request.getServerName());

		System.out.println("getServletPath:"+request.getServletPath());

		System.out.println("getSession:"+request.getSession().getId());
		
		Enumeration<String> enumm = request.getHeaderNames();
	    while (enumm.hasMoreElements()) {
	        String name = (String) enumm.nextElement();
	        System.out.println(name);
	  
	        
	    }
	    
	    
	    System.out.println(request.getHeader("content-type"));



	  //request

	    Enumeration en = request.getParameterNames(); //json인 경우에는 안뜬다.
	       while(en.hasMoreElements()){
	        String param = (String)en.nextElement();
	        String value = request.getParameter(param);
	        System.out.println(param+"-"+value +"request");
	       }

	     

	    //session

	    Enumeration en1 = request.getSession(true).getAttributeNames();
	    while(en1.hasMoreElements()){
	     String param = (String)en1.nextElement();
	     System.out.println(param+"-"+request.getSession(true).getAttribute(param) +"session");
	    }
		
		

		System.out.println(report);
		
		
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		
		model.addAttribute("time", time);
		
		model.addAttribute("report", report);
	    
	    
		
		return "community/addReportView :: reportPostView";
		
	}
	
	
	
	
	
	@RequestMapping(value = "addBattleView", method = RequestMethod.POST)
	public String addBattleView(Model model) {
		
		
		System.out.println("addBattleView 거침");
		
		return "community/addBattleView :: addBattleView";
	}

	@GetMapping("getBattleList")
	public String getBattleList(){
		return "community/getBattleList.html";
	}

	@GetMapping("getBattleView")
	public String getBattleView(){
		return "community/getBattleView.html";
	}

	@PostMapping(value = "getUserDetail")
	public String getUserDetail(Model model, HttpServletRequest request) {
		
		Search search = new Search();
		
		//search.setSearchCondition(Integer.parseInt(request.getParameter("searchCondition")));
		search.setSearchKeyword(request.getParameter("userId"));
		
		System.out.println(search);
		
		User user =  communityService.getUserSearch(search);
		
		model.addAttribute("user", user);

		
		return "community/getUserDetail.html";
	}
	
	
	
	
	
	@PostMapping(value = "addGroupToUserInter")
	public String addGroupToUserInter(HttpServletRequest request, Model model, @RequestBody String str) {
		
		
		
		
		User user = new User();
		
		user = (User)request.getSession(true).getAttribute("user");
		
		GroupUser groupUser = new GroupUser();
		
		groupUser.setUser(user);
		
		groupUser.setCaptainStt(1);
		
		groupUser.setGroupStt(4);
		
		List<Group> list = communityService.getGroupToUserInter(groupUser);
		
		for (Group group : list) {
			System.out.println(group);
		}
		
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str);
		
		User intetUser = new User();
		
		intetUser.setUserId(jsonObject.get("userId").toString());
		
		System.out.println(intetUser);
		
		model.addAttribute("list", list);
		model.addAttribute("user", intetUser);
		return "community/addGroupToUserInter :: addGroupToUserInter";
	}
	
	
	
	@GetMapping(value = "getGroupToUserInter")
	public String getGroupToUserInter(HttpServletRequest request, Model model) {
		
		User user = (User)request.getSession(true).getAttribute("user");
		
		GroupUser groupUser = new GroupUser();
		
		groupUser.setUser(user);
		
		groupUser.setGroupStt(1);
		
		Group group = new Group();
		
		List<Group> list =  communityService.getGroupInterGroup(groupUser);
		
		model.addAttribute("list", list);
		
		return "community/getGroupToUserInter.html";
	}
	
	@RequestMapping(value="faq/result")
	public ModelAndView faqList() throws Exception{
		List<MtmQna> list = communityService.getFaqList(4);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("community/faq.html");
		mav.addObject("list",list);
		
		return mav;
	}
	
	@RequestMapping(value="faq")
	public String faq()throws Exception{
		return "community/faq.html";
	}
	

	
	
	
	@GetMapping(value = "getReportList")
	public String getReportList(){
		
		
		
		return "community/getReportList.html";
	}
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

