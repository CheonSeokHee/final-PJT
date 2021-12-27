package kr.or.fineapple.service.diary.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.Diary;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.mapper.DiaryMapper;
import kr.or.fineapple.service.diary.DiaryService;

@Service
public class DiaryServiceImpl implements DiaryService {
	
	@Autowired
	private DiaryMapper diaryMapper;

	@Override
	public Diary getDiary(String userId, LocalDate startDate, LocalDate endDate) {
		return null;
	}

	@Override
	public void addUserEvent(UserEvent userEvent) {
		////�ش����ڿ� ��ϵ� ����� �̺�Ʈ ���� ��ȸ
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userEvent.getUserId());
		map.put("eventDate", userEvent.getEventDate());
		////����� �̺�Ʈ�� 4�� ������ ��쿡�� ��� ����(�Ϸ� 5�� ��� ����)
		if(diaryMapper.getUserEventCount(map) <= 4) {
			diaryMapper.addUserEvent(userEvent);			
		}
	}

	@Override
	public UserEvent getUserEvent(int userEventNo) {
		return diaryMapper.getUserEvent(userEventNo);
	}

	@Override
	public Map<String, Object> getUserEventList(String userId, LocalDate eventDate) {
		////������̺�Ʈ ��� ��ȸ�� ���� parameter
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("eventDate", eventDate);
		List<UserEvent> list = diaryMapper.getUserEventList(paramMap);
		
		////��ȸ ����� map���� return
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		
		return map;
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
	public Map<String, Object> getUserBodyInfoList(String userId, LocalDate startDate, LocalDate endDate) {
		////����� ��ü ��ȭ ���� ��ȸ�� ���� parameter
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		List<UserBodyInfo> list = diaryMapper.getUserBodyInfoList(paramMap);
		
		////��ȸ ����� map���� return
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		
		return map;
	}

	@Override
	public void updateUserBodyInfo(UserBodyInfo userBodyInfo) {
		diaryMapper.updateUserBodyInfo(userBodyInfo);
	}

}
