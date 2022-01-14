package kr.or.fineapple.diary;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.Achievement;
import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.ExerServ;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.TotalRecord;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.UserServ;
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
		
		////����ڿ��� �������� ù ȭ�鿡 �ʿ��� ����(��ǥ ����� �̺�Ʈ, ����, ���� ����, ���� ��ǥ �� ����)��ȸ ���� parameter ����
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
		UserServ userServ = diaryService.getUserServiceDetails(userId);

		System.out.println(keyEventTitleList);
		System.out.println(badgeList);
		System.out.println(trgtHabitList);
		System.out.println(userServ);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("keyEventTitleList", keyEventTitleList);
		mav.addObject("badgeList", badgeList);
		mav.addObject("userServ", userServ);
		mav.addObject("trgtHabitList", trgtHabitList);
		mav.addObject("NavName1", "���̾");
		mav.addObject("NavName2", "���̾ ��ȸ");
		mav.setViewName("diary/getDiary.html");
		
		return mav;
	}
	
	@RequestMapping(value="getUserDailyLog")
	public ModelAndView getUserDailyLog(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date, HttpSession session) throws Exception{
		
		System.out.println("/diary/getUserDailyLog : GET");
		System.out.println(date);
		
		//viewDuration �� userId, date ����
		String userId = ((User)session.getAttribute("user")).getUserId();
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setDate(date);
		
		////�������� mav ����
		ModelAndView mav = new ModelAndView();
		mav.addObject("todayDate", date);
		
		////�ش� ���� ���� �������� ��ǥ ���� ���� ���� ���� ��ȸ
		//parameter : viewDuration �� userId, date
		List<Object> trgtHabitList = trgtHabitService.getTrgtHabitList(viewDuration);
		////�ش� ������ ��� ����� �̺�Ʈ ��ȸ
		//parameter : viewDuration �� userId, date
		List<Object> userEventList = diaryService.getUserEventList(viewDuration);
		
		////getIntakeRecordList
		//�Ĵܼ��񽺸� �̿��ϴ��� ���� Ȯ�� �� �̿��Ѵٸ� ���� �Ĵ� ��� ��ȸ
		DietServ diet = dietService.getDietService(userId);
		if(diet != null) {
			//�ش� ��¥ �� ���� ���� �Ļ� ������ ���
			List<IntakeRecord> intakeRecordListAll = dietService.getIntakeRecordListForDiary(date, diet.getUserServiceNo());
			List<IntakeRecord> breakfastIntakeRecordList = new ArrayList<IntakeRecord>();
			List<IntakeRecord> lunchIntakeRecordList = new ArrayList<IntakeRecord>();
			List<IntakeRecord> dinnerIntakeRecordList = new ArrayList<IntakeRecord>();
			List<IntakeRecord> snackIntakeRecordList = new ArrayList<IntakeRecord>();
			List<IntakeRecord> supperIntakeRecordList = new ArrayList<IntakeRecord>();
			for (IntakeRecord intakeRecord : intakeRecordListAll) {
				if(intakeRecord.getMeal().equals("��ħ")) {
					breakfastIntakeRecordList.add(intakeRecord);
				}else if(intakeRecord.getMeal().equals("����")) {
					lunchIntakeRecordList.add(intakeRecord);
				}else if(intakeRecord.getMeal().equals("����")) {
					dinnerIntakeRecordList.add(intakeRecord);
				}else if(intakeRecord.getMeal().equals("����")) {
					snackIntakeRecordList.add(intakeRecord);
				}else if(intakeRecord.getMeal().equals("�߽�")) {
					supperIntakeRecordList.add(intakeRecord);
				}
			}
			mav.addObject("breakfast", breakfastIntakeRecordList);
			mav.addObject("lunch", lunchIntakeRecordList);
			mav.addObject("dinner", dinnerIntakeRecordList);
			mav.addObject("snack", snackIntakeRecordList);
			mav.addObject("supper", supperIntakeRecordList);
			
			if(breakfastIntakeRecordList != null) {
				double totalIntakeKcal = 0.0;	//�ش� ������ �� ���� Į�θ�
				double totalIntakeCarb = 0.0;	//�ش� ������ �� ���� ź��ȭ��
				double totalIntakeProtein = 0.0;	//�ش� ������ �� ���� ����
				double totalIntakeFat = 0.0;	//�ش� ������ �� ���� ����
				Map<String, Object> bfTotal = new HashMap<>();
				for(IntakeRecord item : breakfastIntakeRecordList) {
					totalIntakeKcal += item.getFoodKcal();
					totalIntakeCarb += item.getFoodCarb();
					totalIntakeProtein += item.getFoodProtein();
					totalIntakeFat += item.getFoodFat();
				}
				bfTotal.put("totalIntakeKcal", totalIntakeKcal);
				bfTotal.put("totalIntakeCarb", totalIntakeCarb);
				bfTotal.put("totalIntakeProtein", totalIntakeProtein);
				bfTotal.put("totalIntakeFat", totalIntakeFat);
				mav.addObject("bfTotal", bfTotal);
			}
			if(lunchIntakeRecordList != null) {
				double totalIntakeKcal = 0.0;	//�ش� ������ �� ���� Į�θ�
				double totalIntakeCarb = 0.0;	//�ش� ������ �� ���� ź��ȭ��
				double totalIntakeProtein = 0.0;	//�ش� ������ �� ���� ����
				double totalIntakeFat = 0.0;	//�ش� ������ �� ���� ����
				Map<String, Object> lunchTotal = new HashMap<>();
				for(IntakeRecord item : lunchIntakeRecordList) {
					totalIntakeKcal += item.getFoodKcal();
					totalIntakeCarb += item.getFoodCarb();
					totalIntakeProtein += item.getFoodProtein();
					totalIntakeFat += item.getFoodFat();
				}
				lunchTotal.put("totalIntakeKcal", totalIntakeKcal);
				lunchTotal.put("totalIntakeCarb", totalIntakeCarb);
				lunchTotal.put("totalIntakeProtein", totalIntakeProtein);
				lunchTotal.put("totalIntakeFat", totalIntakeFat);
				mav.addObject("lunchTotal", lunchTotal);
			}
			if(dinnerIntakeRecordList != null) {
				double totalIntakeKcal = 0.0;	//�ش� ������ �� ���� Į�θ�
				double totalIntakeCarb = 0.0;	//�ش� ������ �� ���� ź��ȭ��
				double totalIntakeProtein = 0.0;	//�ش� ������ �� ���� ����
				double totalIntakeFat = 0.0;	//�ش� ������ �� ���� ����
				Map<String, Object> dinnerTotal = new HashMap<>();
				for(IntakeRecord item : dinnerIntakeRecordList) {
					totalIntakeKcal += item.getFoodKcal();
					totalIntakeCarb += item.getFoodCarb();
					totalIntakeProtein += item.getFoodProtein();
					totalIntakeFat += item.getFoodFat();
				}
				dinnerTotal.put("totalIntakeKcal", totalIntakeKcal);
				dinnerTotal.put("totalIntakeCarb", totalIntakeCarb);
				dinnerTotal.put("totalIntakeProtein", totalIntakeProtein);
				dinnerTotal.put("totalIntakeFat", totalIntakeFat);
				mav.addObject("dinnerTotal", dinnerTotal);
			}
			if(snackIntakeRecordList != null) {
				double totalIntakeKcal = 0.0;	//�ش� ������ �� ���� Į�θ�
				double totalIntakeCarb = 0.0;	//�ش� ������ �� ���� ź��ȭ��
				double totalIntakeProtein = 0.0;	//�ش� ������ �� ���� ����
				double totalIntakeFat = 0.0;	//�ش� ������ �� ���� ����
				Map<String, Object> sncakTotal = new HashMap<>();
				for(IntakeRecord item : snackIntakeRecordList) {
					totalIntakeKcal += item.getFoodKcal();
					totalIntakeCarb += item.getFoodCarb();
					totalIntakeProtein += item.getFoodProtein();
					totalIntakeFat += item.getFoodFat();
				}
				sncakTotal.put("totalIntakeKcal", totalIntakeKcal);
				sncakTotal.put("totalIntakeCarb", totalIntakeCarb);
				sncakTotal.put("totalIntakeProtein", totalIntakeProtein);
				sncakTotal.put("totalIntakeFat", totalIntakeFat);
				mav.addObject("sncakTotal", sncakTotal);
			}
			if(supperIntakeRecordList != null) {
				double totalIntakeKcal = 0.0;	//�ش� ������ �� ���� Į�θ�
				double totalIntakeCarb = 0.0;	//�ش� ������ �� ���� ź��ȭ��
				double totalIntakeProtein = 0.0;	//�ش� ������ �� ���� ����
				double totalIntakeFat = 0.0;	//�ش� ������ �� ���� ����
				Map<String, Object> supperTotal = new HashMap<>();
				for(IntakeRecord item : supperIntakeRecordList) {
					totalIntakeKcal += item.getFoodKcal();
					totalIntakeCarb += item.getFoodCarb();
					totalIntakeProtein += item.getFoodProtein();
					totalIntakeFat += item.getFoodFat();
				}
				supperTotal.put("totalIntakeKcal", totalIntakeKcal);
				supperTotal.put("totalIntakeCarb", totalIntakeCarb);
				supperTotal.put("totalIntakeProtein", totalIntakeProtein);
				supperTotal.put("totalIntakeFat", totalIntakeFat);
				mav.addObject("supperTotal", supperTotal);
			}
	
			System.out.println(breakfastIntakeRecordList);
			System.out.println(lunchIntakeRecordList);
			System.out.println(dinnerIntakeRecordList);
			System.out.println(snackIntakeRecordList);
			System.out.println(supperIntakeRecordList);
		}

		////getBurnningRecordList
		//����񽺸� �̿��ϴ��� ���� Ȯ�� �� �̿��Ѵٸ� ���� ��� ��� ��ȸ
		ExerServ exer = exerService.getUserService(userId);
		if(exer != null) {
			List burnningRecordList = exerService.getBurnningRecordListForDiary(date, exer.getUserServiceNo());
			mav.addObject("burnningRecordList", burnningRecordList);
			System.out.println(burnningRecordList);
		}
		
		////diet �Ǵ� exer ���񽺸� �̿��ϴ� ���
		//1. ���� ���̺��� �ش� �� �� ���� Į�θ��� �Ҹ� Į�θ� ��ȸ
		//2. ȸ���� �Ĵ� ����/� ���� ��ǥ ���� ��ȸ
		//3. ȸ���� ���� �̿� ��ǥ�޼��� ���� �� ��ȸ
		if(diet != null || exer != null) {
			viewDuration.setStartDate(date);
			viewDuration.setEndDate(date);
			
			//1. ���� ���̺��� �ش� �� �� ���� Į�θ��� �Ҹ� Į�θ� ��ȸ
			List dailyRecordList = diaryService.getBadgeList(viewDuration);
			Badge dailyRecord = (Badge) dailyRecordList.get(0);	//������ �ϳ��� ���ڵ常 ��ȸ�ǹǷ�(�Ϸ� 1��)
			TotalRecord totalRecord = dietService.getTotalDietRecord(viewDuration); //�Ϸ� �Ⱓ �� �� ���� ����� ���� ��ȸ
			
			//2. ȸ���� �Ĵ� ����/� ���� ��ǥ ���� ��ȸ
			UserServ userServ = diaryService.getUserServiceDetails(userId);
			
			//3. ȸ���� ���� �̿� ��ǥ�޼��� ���� �� ��ȸ
			Achievement achievement = new Achievement();
			
			if(diet != null) {
				achievement = diaryService.getDietAchievement(viewDuration);
				System.out.println(achievement);
			}
			if(exer !=null) {
				Integer burnningKcalInPercentage = diaryService.getExerAchievement(viewDuration);
				System.out.println(burnningKcalInPercentage);
				achievement.setBurnningKcalInPercentage(burnningKcalInPercentage);
				System.out.println(achievement);
			}
			
			mav.addObject("userServ", userServ);
			mav.addObject("dailyRecord", dailyRecord);
			mav.addObject("totalRecord", totalRecord);
			mav.addObject("achievement", achievement);
			
			System.out.println(achievement);
			System.out.println(dailyRecord);
			System.out.println(userServ);
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
		mav.addObject("NavName1", "���̾");
		mav.addObject("NavName2", "���̾ ��ȸ");
		mav.addObject("NavName3", "�Ϻ� �� ���� ��ȸ");
		mav.addObject("user", session.getAttribute("user"));
		mav.setViewName("diary/getUserDailyLog.html");
		
		System.out.println(date);
		System.out.println(trgtHabitList);
		System.out.println(userEventList);
		System.out.println(badgeCount);

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
		
		mav.addObject("userBodyInfo", list);
		mav.addObject("NavName1", "���̾");
		mav.addObject("NavName2", "��ü ��ȭ ���� ��ȸ");
		mav.setViewName("diary/getUserBodyInfo.html");
		System.out.println(list);
		return mav;
	}

}
