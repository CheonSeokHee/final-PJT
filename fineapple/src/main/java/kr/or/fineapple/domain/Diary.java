package kr.or.fineapple.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Diary {
	
	////ȸ������
	String userId;
	
	////�Ⱓ
	//�� �Ⱓ ����
	LocalDate startDate;
	LocalDate endDate;
	
	////���̾ ���� ����(�Ϻ�) : ����, ��ǥ������̺�Ʈ, �� ����Ҹ� Į�θ�
	//�Ϻ� ���� ���÷��̸� ���� ����
	LocalDate date;
	//�Ϻ� ȹ���� ���� ����
	int dietBadgeCount;
	int exerBadgeCount;
	int wtrBadgeCount;
	int bttlBadgeCount;
	//�Ϻ� ��ǥ ������̺�Ʈ ����
	String userEventTitle;
	//�Ϻ� �� ���� �Ҹ� Į�θ� 
	double dailyIntakeKcal;
	double dailyBurnningKcal;
	
	////���̾ ���� ����(�� ��ü) : ��ǥ���� ���� ��Ȳ
	//��ǥ����ī�װ� ��ȣ:�̸�
	int trgtHabitCateNo;
	String trgtHabitCateName;
	//��ǥ���� ��������, �ʱ�ȭ ����
	LocalDateTime trgtHabitStartDate;
	LocalDateTime trgtHabitInitDate;
}
