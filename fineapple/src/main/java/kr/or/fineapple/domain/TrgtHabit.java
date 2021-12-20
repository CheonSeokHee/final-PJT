package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrgtHabit {

	////ȸ������
	String userId;
	
	////��ǥ�������� ���� ����
	//ȸ���� ��ǥ�������񽺹�ȣ(�� ȸ���� ������ ��ȣ ���� ����)
	int trgtHabitServiceNo;
	//��ǥ����ī�װ� ��ȣ:�̸�
	int trgtHabitCateNo;
	String trgtHabitCateName;
	//��ǥ���� ��������, �ʱ�ȭ ����
	LocalDate trgtHabitStartDate;
	LocalDate trgtHabitInitDate;
	//��ȸ����
	LocalDate viewDate;
	//�� ��ǥ������ �����ϼ�(��ȸ����-���������� ��)
	int trgtHabitSuccDayCount;
}
