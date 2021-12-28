package kr.or.fineapple.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.Diary;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;

@Mapper
@Repository
public interface DiaryMapper {
	
	////���̾(Ķ��������) ��ȸ
	Diary getDiary(Map map);
	
	////����� �̺�Ʈ �߰�(1�� �ִ� 5������ ���)
	void addUserEvent(UserEvent userEvent);
	
	////�ش����ڿ� ��ϵ� ����� �̺�Ʈ�� ���� ��ȸ(service���� ����)
	int getUserEventCount(Map map);
	
	////����� �̺�Ʈ ��ȸ
	UserEvent getUserEvent(int userEventNo);
	
	////����� �̺�Ʈ ��� ��ȸ
	List<UserEvent> getUserEventList(Map map);
	
	////����� �̺�Ʈ ����
	void updateUserEvent(UserEvent userEvent);
	
	////��ǥ ����� �̺�Ʈ ������ ���� ��ǥ�̺�Ʈ ��� ��� ����(service���� ����)
	void updatePreKeyUserEventStt(int userEventNo);
	
	////��ǥ ����� �̺�Ʈ ����
	void updateKeyUserEventStt(int userEventNo);
	
	////����� �̺�Ʈ ����
	void deleteUserEvent(int userEventNo);
	
	////����� ��ü ��ȭ ���� ��ȸ
	List<UserBodyInfo> getUserBodyInfoList(Map map);
	
	////����� ��ü ��ȭ ���� ��� �� ����(����Ʈ�� �ֽ� ��ġ �����̹Ƿ�)
	void updateUserBodyInfo(UserBodyInfo userBodyInfo);
	
}
