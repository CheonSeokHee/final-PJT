package kr.or.fineapple.community;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.community.BlackList;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.domain.community.Group;
import kr.or.fineapple.domain.community.GroupUser;
import kr.or.fineapple.domain.community.Report;
import kr.or.fineapple.service.community.CommunityService;





@RestController
@RequestMapping("/community/json/*")
public class CommunityRestController {
	
	
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	
	
	
	
	@RequestMapping(value = "updatePostLike", method = RequestMethod.POST)
	public Board updatePostLike(@RequestBody Board board, HttpServletRequest request) {
		

		board.setUser((User)request.getSession(true).getAttribute("user")); 
		System.out.println("=======================================================");
		System.out.println(board);
		
		board = communityService.updatePostLike(board);
		
		System.out.println("=======================================================");
		System.out.println(board);
		
		return board;
	}
	

	@RequestMapping(value = "addCmnt", method = RequestMethod.POST)
	public Map addCmnt(@RequestBody String postNoStr, HttpServletRequest request, Model model) throws JsonMappingException, JsonProcessingException {
		
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(postNoStr);
		
		Board board = new Board();
		board.setPostNo(Integer.parseInt(jsonObject.get("postNo").toString()));
		
		Cmnt cmnt = new Cmnt();
		
		cmnt.setCmntContent(jsonObject.get("cmntContent").toString());
		
		
		cmnt.setBoard(board);
		
		cmnt.setUser((User)request.getSession(true).getAttribute("user"));
		System.out.println("===============================");
		System.out.println(cmnt);
		
		Map map = communityService.addCmnt(cmnt);
		
		System.out.println("==============================");
		
		return map;
	}
		
		

	
	@PostMapping(value = "addReport")
	public void addReport(@RequestBody String str, HttpServletRequest request) {
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str);
		
		String reportCate = jsonObject.get("reportCate").toString();
		
		String TrgtNo = jsonObject.get("TrgtNo").toString();
		
		String time = jsonObject.get("time").toString();
		
		String reportedUserId = jsonObject.get("reportedUser").toString();
		
		String reportcontent = jsonObject.get("reportcontent").toString();
		
		System.out.println(reportCate + "+" + TrgtNo + "+" + time + "+" + reportedUserId + "+" + reportcontent);
		
		User user = new User();
		
		User reportedUser = new User();
		
		user = (User)request.getSession(true).getAttribute("user");
		
		reportedUser.setUserId(reportedUserId);
		
		Report report = new Report();
		
		report.setUser(user);
		
		report.setReportedUser(reportedUser);
		
		report.setReportCate(reportCate);
		
		report.setReportCntnt(reportcontent);
		
		report.setTrgtNo(Integer.parseInt(TrgtNo));
		
		report.setReportStt(1);
		
		
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy�� MM�� dd�� HH�� mm��");
		 
		 
		//report.setReportDate(LocalDate.parse(time, formatter));
		
		
		System.out.println(report);
		
		communityService.addReport(report);

	}
	
	
	
	@PostMapping(value = "checkGroupName")
	public String checkGroupName(@RequestBody String groupName) {
		JSONObject jsonObject = (JSONObject)JSONValue.parse(groupName);
		
		groupName = jsonObject.get("groupName").toString();
		
		Group group = communityService.checkGroupName(groupName);
		
		System.out.println(group + "oooooooooooooooooooooooooooo");
		
		if (group != null) {
			
			return "0";
			
		}
		
		if (group == null) {
			
			return "1";
			
		}
		
		return "3";
	}
	
	
	@PostMapping(value = "addGroup")
	public void addGroup(@RequestBody Group group, HttpServletRequest request) {
		
		
		User user = (User)request.getSession(true).getAttribute("user");
		
		GroupUser groupUser = new GroupUser();
		
		groupUser.setUser(user);
		
		groupUser.setCaptainStt(1);
		
		groupUser.setGroupStt(4);
		
		communityService.addGroup(group, groupUser);
		
		
		System.out.println(group);
		
		
		
		
		
	}
	
	
	@PostMapping(value = "getUserSerach")
	public List getUserSerach(@RequestBody Search search) {
		
		System.out.println("getUserSerach" + "��ħ");
		
		System.out.println(search);
		
		//System.out.println(getClass().getEnclosingMethod().getName() + "��ħ"); //==> �̰� �ּ�Ǯ�� null�ַ� �߻�
		
		List<User> list = communityService.getUserSearchList(search);
		
			
		for (User user : list) {
			System.out.println(user);
		}
		
		return communityService.getUserSearchList(search);
	}
	
	
	
	@PostMapping(value = "addGroupToUserInter")
	public void AddGroupToUserInter(@RequestBody String str) {
		
		System.out.println(str + "�ӱ⹫��~");
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str);
		
		GroupUser groupUser = new GroupUser();
		
		Group group = new Group();
		
		group.setGroupNo(Integer.parseInt(jsonObject.get("groupNo").toString()));
		
		groupUser.setGroup(group);
		
		User interUser = new User();
		
		interUser.setUserId(jsonObject.get("interUserId").toString());
		
		groupUser.setUser(interUser);
		
		groupUser.setGroupStt(1);
		
		System.out.println(groupUser);
		
		communityService.addGroupToUserInter(groupUser);
		
		
		
		System.out.println("�� ��Ҷ�~" + group);
		
		
	}
	
	@PostMapping(value = "AcceptOrRejectGroupInter")
	public void AcceptOrRejectGroupInter(@RequestBody String str, HttpServletRequest request) {
		
		System.out.println(str);
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str); //{"interStt":1,"groupUserNoData":"1"}
		
		int intetStt = Integer.parseInt(jsonObject.get("interStt").toString());
		
		int groupNo = Integer.parseInt(jsonObject.get("groupUserNoData").toString());
		
		User user = new User();
		
		user = (User)request.getSession(true).getAttribute("user");
		
		HashMap map = new HashMap();
		map.put("groupNo", groupNo);
		map.put("captainStt", 2);
		map.put("groupStt", 4);
		map.put("user", user);
		
		communityService.delGroupUserInter(map, intetStt);
		

		
	}
	
	@PostMapping(value = "addBlackList")
	public void addBlackList(@RequestBody String str) {
		
		System.out.println(str);
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str);
		
		BlackList blackList = new BlackList();
		
		User blakcUser = new User();
		
		blakcUser.setUserId(jsonObject.get("blackUserId").toString());
		
		blackList.setBlackUser(blakcUser);
		
		blackList.setAddBlackWhy(jsonObject.get("blackcontent").toString());
		
		
		System.out.println(blackList);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	

	
}
