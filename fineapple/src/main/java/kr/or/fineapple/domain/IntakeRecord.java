package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class IntakeRecord {

	
	String userId;
	int intakeRecordNo;
	double foodKcal;
	double foodCarb;	
	double foodProtein;
	double foodFat;
	double userFoodIntake;
	double userWtrIntake;
	double remainKcal;
	String meal;
	LocalDate date;
	Food food;

	public IntakeRecord() {
		// TODO Auto-generated constructor stub
	}

}