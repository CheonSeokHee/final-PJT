package kr.or.fineapple.trgtHabit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.domain.common.Timer;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

@RestController
@RequestMapping("/trgtHabit/*")
public class RestTrgtHabitController {
	
	@Autowired
	@Qualifier("trgtHabitServiceImpl")
	private TrgtHabitService trgtHabitService;
	
	public RestTrgtHabitController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value="json/getTrgtHabit", method=RequestMethod.POST)
	public TrgtHabit getTrgtHabit(@RequestBody TrgtHabit trgtHabit) {
	
		System.out.println("/trgtHabit/json/getTrgtHabit : POST");
		////��ȸ�� ���� service ȣ��
		TrgtHabit returnTrgtHabit = trgtHabitService.getTrgtHabit(trgtHabit.getUserId(), trgtHabit.getViewDate(), trgtHabit.getTrgtHabitCateNo());
	
		//����� null�϶�(��ǥ ���� �������� �Ǽ��� ������)
		if(returnTrgtHabit != null) {		
			////�����ϼ� ��� ���� ���� Logic
			//�������ڿ� ���������� ��
			LocalDateTime trgtHabitStartDate = returnTrgtHabit.getTrgtHabitStartDate().atStartOfDay();
			int trgtHabitSuccDayCount = (int)Duration.between(trgtHabitStartDate, LocalDate.now().atStartOfDay()).toDays();
			returnTrgtHabit.setTrgtHabitSuccDayCount(trgtHabitSuccDayCount);
		}
		return returnTrgtHabit;		
	}
	
	@RequestMapping(value="json/addTrgtHabit", method=RequestMethod.POST)
	public void addTrgtHabit(@RequestBody TrgtHabit trgtHabit) {
		  
		System.out.println("/trgtHabit/json/addTrgtHabit : POST");
		//���� ������ ���� service ȣ��	  
		trgtHabitService.addTrgtHabit(trgtHabit.getUserId(), trgtHabit);
		
	}
	 
	@RequestMapping(value="json/endTrgtHabit", method=RequestMethod.POST)
	public void endTrgtHabit(@RequestBody TrgtHabit trgthabit) {
		
		System.out.println("/trgtHabit/json/endTrgtHabit : POST");
		////��ǥ ���� �ϼ� �ʱ�ȭ�� ���� ���� ȣ��
		trgtHabitService.endTrgtHabit(trgthabit.getTrgtHabitServiceNo());
	}
	
	@RequestMapping(value="json/getCurrentTime")
	public Timer getCurrentTime() {
		
		System.out.println("/trgtHabit/json/getCurrentTime");
		
		Timer time = new Timer();
		time.setHour(LocalTime.now().getHour());
		time.setMin(LocalTime.now().getMinute());
		time.setSec(LocalTime.now().getSecond());
		
		System.out.println(time);
		return time;
	}
}
