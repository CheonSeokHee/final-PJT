package kr.or.fineapple.domain;

import java.time.LocalDate;

public class MonthlyPaper {

	////ȸ������
	String userId;
	
	////�Ⱓ
	LocalDate startingDate;
	LocalDate endingDate;
	
	////��������(Weekly�� ����)
	UserBodyInfo userBodyInfo;
	UserEvent userEvent;
	Badge badge;
	TrgtHabit trgtHabit;
	
	////Monthly�� �߰��� �����Ǵ� ����
	//�ֺ� ����� ���� �Ҹ� Į�θ�(����Ʈ�����)
	double dailyAverageIntakeKcalPerWeek;
	double dailyAverageBurnningKcalPerWeek;
	//���� �Ҹ� Į�θ��� ���� ���� ���� �� ���� �Ҹ� Į�θ�(�߰� ������ �ʵ� �ʿ��Ҽ�����..)
	double theHighestIntakeKcalWeek;
	double theHighestBurnningKcalWeek;
	//�� �� ���� ���� �Ҹ� Į�θ��� ���� ���� ���� �Ҹ� Į�θ�
	double theHighestIntakeKcalDay;
	double theHighestBurnningKcalDay;
}
