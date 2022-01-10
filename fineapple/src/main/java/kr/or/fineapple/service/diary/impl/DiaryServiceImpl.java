package kr.or.fineapple.service.diary.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.domain.common.ViewDuration;
import kr.or.fineapple.mapper.DiaryMapper;
import kr.or.fineapple.service.diary.DiaryService;

@Service
public class DiaryServiceImpl implements DiaryService {
	
	@Autowired
	private DiaryMapper diaryMapper;

	@Override
	public void addUserEvent(UserEvent userEvent) {
		////�ش����ڿ� ��ϵ� ����� �̺�Ʈ ���� ��ȸ
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userEvent.getUserId());
		map.put("eventDate", userEvent.getEventDate());
		////����� �̺�Ʈ�� 4�� ������ ��쿡�� ��� ����(�Ϸ� 5�� ��� ����)
		int eventCount = diaryMapper.getUserEventCount(map);
		if(eventCount <= 4) {
			//ù��° �̺�Ʈ�� ��� ��ǥ�̺�Ʈ�� ���
			if(eventCount == 0) {
				userEvent.setKeyEventStt(0);
				diaryMapper.addUserEvent(userEvent);
			} else {
			//1�� �̻��� ����� �̺�Ʈ�� ������ ��� �Ϲ� �̺�Ʈ�� ���	
			userEvent.setKeyEventStt(1);
			diaryMapper.addUserEvent(userEvent);
			}
		}
	}

	@Override
	public UserEvent getUserEvent(int userEventNo) {
		return diaryMapper.getUserEvent(userEventNo);
	}

	@Override
	public List<Object> getUserEventList(ViewDuration viewDuration) {
		////������̺�Ʈ ��� ��ȸ�� ���� parameter
		//viewDuration �� userId, date
		List<Object> list = diaryMapper.getUserEventList(viewDuration);
	
		return list;
	}

	@Override
	public void updateUserEvent(UserEvent userEvent) {
		diaryMapper.updateUserEvent(userEvent);
	}

	@Override
	public void updateKeyUserEventStt(int userEventNo) {
		////�ش����ڿ� ����� ������̺�Ʈ���� ��ǥ�̺�Ʈ ��� ���¸� ��� ����
		diaryMapper.updatePreKeyUserEventStt(userEventNo);
		
		////����ڰ� ������ �̺�Ʈ�� ��ǥ�̺�Ʈ�� ����
		diaryMapper.updateKeyUserEventStt(userEventNo);
	}

	@Override
	public void deleteUserEvent(int userEventNo) {
		////���� �̺�Ʈ ����
		diaryMapper.deleteUserEvent(userEventNo);
	}

	@Override
	public List<Object> getUserBodyInfoList(ViewDuration viewDuration) {
		////����� ��ü ��ȭ ���� ��ȸ�� ���� parameter
		//viewDuration �� userId, startDate, endDate
		List<Object> list = diaryMapper.getUserBodyInfoList(viewDuration);
		
		return list;
	}

	@Override
	public void updateUserBodyInfo(UserBodyInfo userBodyInfo) {
		diaryMapper.updateUserBodyInfo(userBodyInfo);
	}

	@Override
	public List<Object> getBadgeList(ViewDuration viewDuration) {
		////�Ⱓ �� ���� ��� ��ȸ�� ���� parameter
		//viewDuration �� userId, startDate, endDate
		List<Object> list = diaryMapper.getBadgeList(viewDuration);
		
		return list;
	}

	@Override
	public Badge getBadgeTotalCount(ViewDuration viewDuration) {
		////�Ⱓ �� ȹ���� ���� ���� ���� ��ȸ�� ���� parameter
		//viewDuration �� userId, startDate, endDate
		return diaryMapper.getBadgeTotalCount(viewDuration);
	}

	@Override
	public List<Object> getKeyEventTitleList(ViewDuration viewDuration) {
		////�Ⱓ �� ��ǥ�̺�Ʈ ���� ��� ��ȸ�� ���� parameter
		//viewDuration �� userId, startDate, endDate
		List<Object> list = diaryMapper.getKeyEventTitleList(viewDuration);

		return list;
	}
}
