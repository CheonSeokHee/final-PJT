package kr.or.fineapple.diary;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.ExerServ;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.ViewDuration;
import kr.or.fineapple.service.diary.DiaryService;
import kr.or.fineapple.service.diet.DietService;
import kr.or.fineapple.service.exer.ExerService;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

@Controller
@RequestMapping("/diary/*")
public class DiaryController {
	
	@Autowired
	@Qualifier("diaryServiceImpl")
	private DiaryService diaryService;
	
	@Autowired
	@Qualifier("dietServiceImpl")
	private DietService dietService;
	
	@Autowired
	@Qualifier("ExerServiceImpl")
	private ExerService exerService;
	
	@Autowired
	@Qualifier("trgtHabitServiceImpl")
	private TrgtHabitService trgtHabitService;
	
	@RequestMapping(value="getDiary")
	public ModelAndView getDiary(HttpSession session) {
		
		System.out.println("/diary/getDiary : GET");
		
		////����ڿ��� �������� ù ȭ�鿡 �ʿ��� ����(��ǥ ����� �̺�Ʈ, ����, ���� ����)��ȸ ���� parameter ����
		//userId, ù ȭ���� ����� �������ڿ� �ش��ϴ� ���̹Ƿ� �̴� �Ⱓ
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDate startDate = YearMonth.now().atDay(1);
		LocalDate endDate = YearMonth.now().atEndOfMonth();
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setStartDate(startDate);
		viewDuration.setEndDate(endDate);
		
		List<Object> keyEventTitleList = diaryService.getKeyEventTitleList(viewDuration);
		List<Object> badgeList = diaryService.getBadgeList(viewDuration);
		List<Object> trgtHabitList = trgtHabitService.getTrgtHabitList(viewDuration);

		System.out.println(keyEventTitleList);
		System.out.println(badgeList);
		System.out.println(trgtHabitList);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("keyEventTitleList", keyEventTitleList);
		mav.addObject("badgeList", badgeList);
		mav.addObject("trgtHabitList", trgtHabitList);
		mav.setViewName("diary/getDiary.html");
		
		return mav;
	}
	
	@RequestMapping(value="getUserDailyLog")
	public ModelAndView getUserDailyLog(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date, HttpSession session) throws Exception{
		
		System.out.println("/diary/getUserDailyLog : GET");
		
		//viewDuration �� userId, date ����
		String userId = ((User)session.getAttribute("user")).getUserId();
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setDate(date);
		
		////�������� mav ����
		ModelAndView mav = new ModelAndView();
		mav.addObject("date", date);
		
		////�ش� ���� ���� �������� ��ǥ ���� ���� ���� ���� ��ȸ
		//parameter : viewDuration �� userId, date
		List<Object> trgtHabitList = trgtHabitService.getTrgtHabitList(viewDuration);
		////�ش� ������ ��� ����� �̺�Ʈ ��ȸ
		//parameter : viewDuration �� userId, date
		List<Object> userEventList = diaryService.getUserEventList(viewDuration);
		
		//getIntakeRecordList
		//�Ĵܼ��񽺸� �̿��ϴ��� ���� Ȯ�� �� �̿��Ѵٸ� ���� �Ĵ� ��� ��ȸ
		DietServ diet = dietService.getDietService(userId);
		if(diet != null) {
			List intakeRecordList = dietService.getIntakeRecordListForDiary(userId, diet.getUserServiceNo());
			mav.addObject("intakeRecordList", intakeRecordList);
		}

		//getBurnningRecordList
		//����񽺸� �̿��ϴ��� ���� Ȯ�� �� �̿��Ѵٸ� ���� ��� ��� ��ȸ
		ExerServ exer = exerService.getUserService(userId);
		if(exer != null) {
			List burnningRecordList = exerService.getBurnningRecordListForDiary(userId, exer.getUserServiceNo());
			mav.addObject("burnningRecordList", burnningRecordList);
		}
		
		////������ ȹ�� ���� ���� ��ȸ
		//parameter : viewDuration �� userId, startDate, endDate
		LocalDate startDate = viewDuration.getDate().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		LocalDate endDate = viewDuration.getDate().with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		viewDuration.setStartDate(startDate);
		viewDuration.setEndDate(endDate);
		Badge badgeCount = diaryService.getBadgeTotalCount(viewDuration);

		mav.addObject("trgtHabitList", trgtHabitList);
		mav.addObject("userEventList", userEventList);
		mav.addObject("badgeCount", badgeCount);
		mav.addObject("user", session.getAttribute("user"));
		mav.setViewName("diary/getUserDailyLog.html");

		return mav;
	}
	
	@RequestMapping(value="getUserBodyInfo")
	public ModelAndView getUserBodyInfo(HttpSession session) {
		
		System.out.println("diary/getUserBodyInfo");
		
		////����� ��ü ��ȭ ���� �Է� ��¸� ���� parameter
		//session �� userId�� ���� ���� ���� �ְ� ��� ��ȸ
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDateTime endDate = LocalDate.now().atStartOfDay();
		LocalDateTime startDate = endDate.minus(Duration.ofDays(6));
		//viewDuration �� userId, startDate, endDate, date ����
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setEndDate(LocalDate.now());
		viewDuration.setStartDate(startDate.toLocalDate());
		System.out.println(viewDuration.getStartDate());
		
		List<Object> list = diaryService.getUserBodyInfoList(viewDuration);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("diary/getUserBodyInfo.html");
		mav.addObject("userBodyInfo", list);
		System.out.println(list);
		return mav;
	}

}
