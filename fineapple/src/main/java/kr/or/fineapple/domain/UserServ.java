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
	
	////� ���� ��ǥ ����
	//���� ��ǥ �Ҹ� ����
	Double dailyTrgtBurnningKcal;

	////�Ĵ� ���� ��ǥ ����
	//�� ��ǥ
	Double dailyTrgtIntakeCarb;
	Double dailyTrgtIntakeProtein;
	Double dailyTrgtIntakeFat;
}
