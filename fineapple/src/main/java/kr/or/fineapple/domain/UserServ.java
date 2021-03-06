package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserServ {

	////회원정보
	int userServiceNo;
	String userId;
	
	//목표 체중(from Users 테이블)
	Double trgtWeight;
	//목표 체지방률
	Double trgtBodyFat;
	//목표 근육량
	Double trgtBodyMuscle;
	
	////식단 서비스 목표 정보
	//일일 목표 섭취 정보
	Double dailyTrgtIntakeKcal;
	
	////운동 서비스 목표 정보
	//일일 목표 소모 정보
	Double dailyTrgtBurnningKcal;

	////식단 서비스 목표 정보
	//상세 목표
	Double dailyTrgtIntakeCarb;
	Double dailyTrgtIntakeProtein;
	Double dailyTrgtIntakeFat;

}
