package kr.or.fineapple.trgtHabit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Timer;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

@Controller
@RequestMapping("/trgtHabit/*")
public class TrgtHabitController {
	
	@Autowired
	@Qualifier("trgtHabitServiceImpl")
	private TrgtHabitService trgtHabitService;
	
	public TrgtHabitController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="getTrgtHabit", method=RequestMethod.GET)
	public ModelAndView getTrgtHabit(HttpSession session) {
	
		System.out.println("/trgtHabit/getTrgtHabit : GET");
		
		////����ڿ��� �������� ���� ù ȭ�鿡 �ʿ��� ���� ��ȸ(��ǥ �� Ŀ��, ���� ���м��뷮 ����)
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDate date = LocalDate.now();
		int trgtCateNo = 1;
		
		////��ȸ�� ���� service ȣ��
		TrgtHabit trgtHabit = trgtHabitService.getTrgtHabit(userId, date, trgtCateNo);
		double userWtrIntake = trgtHabitService.getWtrIntake(userId, date);
		
		////������ ModelAndView, Timer ����
		ModelAndView mav = new ModelAndView();
		Timer time = new Timer();
		
		//����� null�϶�(��ǥ ���� �������� �Ǽ��� ������)
		if(trgtHabit != null) {		
			////�����ϼ� ��� ���� ���� Logic
			//�������ڿ� ���������� ��
			LocalDateTime trgtHabitStartDate = trgtHabit.getTrgtHabitStartDate().atStartOfDay();
			int trgtHabitSuccDayCount = (int)Duration.between(trgtHabitStartDate, LocalDate.now().atStartOfDay()).toDays();
			//����� trgtHabit �����ο� ����
			trgtHabit.setTrgtHabitSuccDayCount(trgtHabitSuccDayCount);
			//time ����� ���� timer ������ ����
			time.setHour(LocalTime.now().getHour());
			time.setMin(LocalTime.now().getMinute());
			time.setSec(LocalTime.now().getSecond());
			mav.addObject("trgtHabit", trgtHabit);
		} else {
			//�� trgtHabit ��ü ����
			TrgtHabit emptyTrgtHabit = new TrgtHabit();
			emptyTrgtHabit.setTrgtHabitServiceNo(0);
			emptyTrgtHabit.setTrgtHabitCateNo(1);
			emptyTrgtHabit.setTrgtHabitSuccDayCount(0);
			//timer ������ ����
			time.setHour(0);
			time.setMin(0);
			time.setMin(0);
			mav.addObject("trgtHabit", emptyTrgtHabit);
		}
		mav.addObject("time", time);
		mav.addObject("userWtrIntake", userWtrIntake);
		mav.setViewName("trgtHabit/getTrgtHabit.html");

		return mav;
	}
	
	@RequestMapping(value="addTrgtHabit", method=RequestMethod.POST)
	public ModelAndView addTrgtHabit(@ModelAttribute TrgtHabit trgtHabit) {
		
		System.out.println("/trgtHabit/addTrgtHabit : POST");

		trgtHabitService.addTrgtHabit(trgtHabit.getUserId(), trgtHabit);
//		
		ModelAndView mav = new ModelAndView();
//		mav.addObject("trgtHabit", trgtHabit);
//		mav.addObject("date", LocalDate.now());
		mav.setViewName("redirect:/trgtHabit/json/getTrgtHabit");
		
		return mav;
	}
	

}
