package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Achievement {
	
	////�Ĵ� ��ǥ �޼���
	int intakeKcalInPercentage;
	int intakeCarbInPercentage;
	int intakeProteinInPercentage;
	int intakeFatInPercentage;
	
	////� ��ǥ �޼���
	int burnningKcalInPercentage;
}
