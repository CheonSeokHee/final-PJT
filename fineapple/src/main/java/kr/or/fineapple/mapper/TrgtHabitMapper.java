package kr.or.fineapple.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.TrgtHabit;

@Mapper
@Repository
public interface TrgtHabitMapper {

	////��ǥ ���� ���� ����
	void addTrgtHabit(TrgtHabit trgtHabit);
	
	////��ǥ ���� ���� ���� Ȯ��
	TrgtHabit getTrgtHabit(Map map);
	
	////��������� ��ǥ������ ��ȸ
	String getUserHabitName(String userId);
	
	////��ǥ �ʱ�ȭ
	void endTrgtHabit(int trgtHabitServiceNo);
	
	////���� ���뷮 ��� ����
	void addWtrIntake(String userId);

	////���� ���뷮 ��ȸ
	Double getWtrIntake(Map map);
	
	////���� ���뷮 ��� ����
	void updateWtrIntake(Map map);
}
