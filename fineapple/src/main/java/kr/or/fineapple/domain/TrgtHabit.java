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
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTrgtHabitServiceNo() {
		return trgtHabitServiceNo;
	}
	public void setTrgtHabitServiceNo(int trgtHabitServiceNo) {
		this.trgtHabitServiceNo = trgtHabitServiceNo;
	}
	public int getTrgtHabitCateNo() {
		return trgtHabitCateNo;
	}
	public void setTrgtHabitCateNo(int trgtHabitCateNo) {
		this.trgtHabitCateNo = trgtHabitCateNo;
	}
	public String getTrgtHabitCateName() {
		return trgtHabitCateName;
	}
	public void setTrgtHabitCateName(String trgtHabitCateName) {
		this.trgtHabitCateName = trgtHabitCateName;
	}
	public LocalDate getTrgtHabitStartDate() {
		return trgtHabitStartDate;
	}
	public void setTrgtHabitStartDate(LocalDate trgtHabitStartDate) {
		this.trgtHabitStartDate = trgtHabitStartDate;
	}
	public LocalDate getTrgtHabitInitDate() {
		return trgtHabitInitDate;
	}
	public void setTrgtHabitInitDate(LocalDate trgtHabitInitDate) {
		this.trgtHabitInitDate = trgtHabitInitDate;
	}
	public LocalDate getViewDate() {
		return viewDate;
	}
	public void setViewDate(LocalDate viewDate) {
		this.viewDate = viewDate;
	}
	public int getTrgtHabitSuccDayCount() {
		return trgtHabitSuccDayCount;
	}
	public void setTrgtHabitSuccDayCount(int trgtHabitSuccDayCount) {
		this.trgtHabitSuccDayCount = trgtHabitSuccDayCount;
	}
	
	
	
}
