package kr.or.fineapple.persistence;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.service.diary.DiaryService;

//@SpringBootTest
public class DiaryMapperTest {
	
	//@Autowired
	//@Qualifier("diaryServiceImpl")
	private DiaryService diaryService;
	
	//@Test
	public void contextLoads() {

//		String userId = "hc@gmail.com";
//		LocalDate eventDate = LocalDate.now();
//		String eventTitle = "���̻�å";
//		String eventCntnt = "������ ��糿���� �ٸð��ðž�!!!!";
		
		
		//addUserEvent Test
		
		/*
		 * UserEvent userEvent = new UserEvent(); userEvent.setUserId(userId);
		 * userEvent.setEventDate(eventDate); userEvent.setKeyEventStt(0);
		 * userEvent.setEventTitle(eventTitle); userEvent.setEventCntnt(eventCntnt);
		 * 
		 * diaryService.addUserEvent(userEvent);
		 */
		
		//getUserEvent Test
		
//		  UserEvent returnUserEvent = diaryService.getUserEvent(5);
//		  System.out.println(returnUserEvent);
		 
		
		//getUserEventList Test
		/*
		 * Map map = diaryService.getUserEventList(userId, eventDate);
		 * System.out.println(map);
		 */
		
		//updateUserEvent Test
//		UserEvent userEvent = diaryService.getUserEvent(6);
//		System.out.println(userEvent);
//		
//		userEvent.setEventTitle("���̻�å");
//		userEvent.setEventCntnt("������ ��糿���� �ٸð��ðž�!!!!");
//		diaryService.updateUserEvent(userEvent);
		
		//updateKeyUSerEventStt
		
//		diaryService.updateKeyUserEventStt(2);
		
		//deleteUserEvent
//		diaryService.deleteUserEvent(5);
		
		//getUserBodyInfoList Test
//		LocalDate startDate = LocalDate.of(2021, 12, 21);
//		LocalDate endDate = LocalDate.of(2021, 12, 27);
//		Map map = diaryService.getUserBodyInfoList(userId, startDate, endDate);
//		System.out.println(map);
		
		//updateUserBodyInfo Test
//		UserBodyInfo userBodyInfo = new UserBodyInfo();
//		userBodyInfo.setUserId(userId);
//		userBodyInfo.setDate(LocalDate.of(2021, 12, 26));
//		userBodyInfo.setWeight(65);
//		userBodyInfo.setHeight(174);
//		diaryService.updateUserBodyInfo(userBodyInfo);
		
	}

}