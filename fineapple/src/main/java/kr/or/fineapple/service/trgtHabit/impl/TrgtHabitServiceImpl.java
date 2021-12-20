package kr.or.fineapple.service.trgtHabit.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.domain.WtrIntake;
import kr.or.fineapple.mapper.TrgtHabitMapper;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

public class TrgtHabitServiceImpl implements TrgtHabitService {
	
	private TrgtHabitMapper trgtHabitMapper;

	@Override
	public void addTrgtHabit(String userId, TrgtHabit trgtHabit) {
		
		////trgtHabit�� userId ����
		trgtHabit.setUserId(userId);
		////trgtHabit �߰�
		trgtHabitMapper.addTrgtHabit(trgtHabit);
	}

	@Override
	public TrgtHabit getTrgtHabit(String userId, LocalDate viewDate, int trgtHabitCateNo) {
		
		///SELECT�� ���� WHERE ������ map�� �־� ����
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("viewDate", viewDate);
		map.put("trgtHabitCateNo", trgtHabitCateNo);
		
		return trgtHabitMapper.getTrgtHabit(map);
	}

	@Override
	public void endTrgtHabit(int trgtHabitServiceNo) {
		
		////���� ��ǥ �ϼ� �ʱ�ȭ
		trgtHabitMapper.endTrgtHabit(trgtHabitServiceNo);
	}

	@Override
	public double getWtrIntake(String userId, LocalDate date) {

		////�ش����� ��� ��ȸ
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("date", date);
		
		Double userWtrIntake = trgtHabitMapper.getWtrIntake(map);
		
		////�ش����� ����� ���� ��� ����
		if(userWtrIntake == null) {
			trgtHabitMapper.addWtrIntake(userId);
			userWtrIntake = 0.0;
		}
		return userWtrIntake;
	}

	@Override
	public double updateWtrIntake(double userWtrIntake) {
		// TODO Auto-generated method stub
		return 0;
	}

}
