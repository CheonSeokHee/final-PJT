package kr.or.fineapple.domain;

import java.time.LocalDate;

public class WeeklyPaper {

	////ȸ������
	String userId;
	
	////�Ⱓ
	LocalDate startingDate;
	LocalDate endingDate;
	
	////��������(Monthly���� ����)
	UserBodyInfo userBodyInfo;
	UserEvent userEvent;
	Badge badge;
	TrgtHabit trgtHabit;
}
