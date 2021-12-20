package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Badge {

	////ȸ������
	String userId;
	////����(������ ȸ�� ���� ����)
	LocalDate date;
	////�ش� �� ȹ���� ���� ����
	int dietBadgeCount;
	int exerBadgeCount;
	int wtrBadgeCount;
	int bttlBadgeCount;
	////���� ȹ����� ���
	double dailyIntakeKcal;
	double dailyBurnningKcal;
	double dailyWtrIntake;
	
}
