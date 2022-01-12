package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserServ {

	////ȸ������
	int userServiceNo;
	String userId;
	
	//��ǥ ü��(from Users ���̺�)
	Double trgtWeight;
	//��ǥ ü�����
	Double trgtBodyFat;
	//��ǥ ������
	Double trgtBodyMuscle;
	
	////�Ĵ� ���� ��ǥ ����
	//���� ��ǥ ���� ����
	Double dailyTrgtIntakeKcal;
	//��ǥ �޼���(ȭ�� ���÷��̿�)
	int intakeKcalInPercentage;
	
	////� ���� ��ǥ ����
	//���� ��ǥ �Ҹ� ����
	Double dailyTrgtBurnningKcal;
	//��ǥ �޼���(ȭ�� ���÷��̿�)
	int burnningKcalInPercentage;

	////�Ĵ� ���� ��ǥ ����
	//�� ��ǥ
	Double dailyTrgtIntakeCarb;
	int intakeCarbInPercentage;
	Double dailyTrgtIntakeProtein;
	int intakeProteinInPercentage;
	Double dailyTrgtIntakeFat;
	int intakeFatInPercentage;
	
}
