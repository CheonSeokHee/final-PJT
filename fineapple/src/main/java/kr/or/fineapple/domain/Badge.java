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
	int badgeNo;
	LocalDate badgeDate;
	////�ش� �� ȹ���� ���� ����
	int dietBadgeCount;
	int exerBadgeCount;
	int wtrBadgeCount;
	int bttlBadgeCount;
	////���� ȹ����� ���
	Double dailyIntakeKcal;
	Double dailyBurnningKcal;
	double userWtrIntake;
}
