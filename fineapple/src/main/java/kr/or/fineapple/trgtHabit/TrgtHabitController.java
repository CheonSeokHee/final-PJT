package kr.or.fineapple.trgtHabit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
		
		////����ڿ��� �������� ���� ù ȭ�鿡 �ʿ��� ���� ��ȸ(��ǥ �� Ŀ��, ���� ���м��뷮 ����, ��������� ��ǥ �������� ��� �̸�)
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDate date = LocalDate.now();
		int trgtCateNo = 1;	//Ŀ���� Category No = 1
		
		//��ȸ�� ���� service ȣ��
		TrgtHabit trgtHabit = trgtHabitService.getTrgtHabit(userId, date, trgtCateNo);
		double userWtrIntake = trgtHabitService.getWtrIntake(userId, date);
		String userHabitName = trgtHabitService.getUserHabitName(userId);
		
		////������ ModelAndView, Timer ����
		ModelAndView mav = new ModelAndView();
		
		//��������� ��ǥ �׸��� �����Ҷ�
		if(userHabitName != null) {
			mav.addObject("userHabitName", userHabitName);
		} else {
			mav.addObject("userHabitName", "���������");
		}
		
		//Ŀ�� ��ǥ ��ȸ ����� �����Ҷ�
		if(trgtHabit != null) {		
			////�����ϼ� ��� ���� ���� Logic
			//�������ڿ� ���������� ��
			LocalDateTime trgtHabitStartDate = trgtHabit.getTrgtHabitStartDate().atStartOfDay();
			int trgtHabitSuccDayCount = (int)Duration.between(trgtHabitStartDate, LocalDate.now().atStartOfDay()).toDays();
			//����� trgtHabit �����ο� ����
			trgtHabit.setTrgtHabitSuccDayCount(trgtHabitSuccDayCount+1);
			mav.addObject("trgtHabit", trgtHabit);
		} else {
			//�� trgtHabit ��ü ����
			TrgtHabit emptyTrgtHabit = new TrgtHabit();
			emptyTrgtHabit.setTrgtHabitCateNo(1);
			emptyTrgtHabit.setTrgtHabitSuccDayCount(0);
			mav.addObject("trgtHabit", emptyTrgtHabit);
		}
		
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
