package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Achievement {
	
	////�Ĵ� ��ǥ �޼���
	Integer intakeKcalInPercentage;
	Integer intakeCarbInPercentage;
	Integer intakeProteinInPercentage;
	Integer intakeFatInPercentage;
	
	////� ��ǥ �޼���
	Integer burnningKcalInPercentage;
}
