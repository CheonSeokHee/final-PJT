package kr.or.fineapple.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.Achievement;
import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.domain.UserServ;
import kr.or.fineapple.domain.common.ViewDuration;

//@Mapper
//@Repository
public interface BatchMapper {

	////��ġ���α׷� ����: ���� ������ ����� ����Ʈ�� userBodyInfo ����
	void addUserBodyInfo(String userId);
	
	////��ġ���α׷� ����: ���� ���� ��� insert
	void addBadge(Badge defaultBadge);
	
	////���̾ ����� �ϸ��ϰ� �ۼ�
	public List<User> getAllUsers();
}
