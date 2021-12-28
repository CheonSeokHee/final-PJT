package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserEvent {
	
	////ȸ������
	String userId;
	
	////������̺�Ʈ ���� ����
	//ȸ���� ������̺�Ʈ��ȣ(�� ȸ���� ������ ��ȣ ���� ����)
	int userEventNo;
	//ȸ���� �Ϸ翡 �ִ� 5���� ����� �̺�Ʈ ��� ����
	LocalDate eventDate;
	//��ǥ�̺�Ʈ ����(����Ʈ�� ȸ���� ó�� ����� �̺�Ʈ�� ��ǥ�̺�Ʈ�� ����)
	int keyEventStt;
	//������̺�Ʈ ����, ����
	String eventTitle;
	String eventCntnt;
}
