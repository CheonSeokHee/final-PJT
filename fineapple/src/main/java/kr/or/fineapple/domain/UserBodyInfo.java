package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserBodyInfo {

	////ȸ������
	String userId;

	////����
	LocalDate date;
	
	////ȸ���� ü��, ü���淮, ��ݱٷ�
	double weight;
	double bodyFat;
	double bodyMuscle;
}
