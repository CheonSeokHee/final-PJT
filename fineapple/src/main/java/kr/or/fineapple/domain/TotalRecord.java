package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TotalRecord {
	LocalDate theHighestIntakeDate;
	
	//�׻� ����ؼ� �������� ������ ����ڰ� ��� ������ ���� �� ����(badge�����ο� ���� ���� ������ ����)
	Double totalIntakeKcal;
	Double totalIntakeCarb;
	Double totalIntakeProtein;
	Double totalIntakeFat;
}
