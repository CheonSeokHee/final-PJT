package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
public class TotalRecord {
	//�׻� ����ؼ� �������� ������ ����ڰ� ��� ������ ���� �� ����(badge�����ο� ���� ���� ������ ����)
	Double totalIntakeKcal;
	Double totalIntakeCarb;
	Double totalIntakeProtein;
	Double totalIntakeFat;
}
