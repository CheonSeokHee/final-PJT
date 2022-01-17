package kr.or.fineapple.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.mapper.DiaryMapper;
import kr.or.fineapple.mapper.UserMapper;

//@Component
public class MidnightBatchJob {
	
	//@Autowired
	private DiaryMapper diaryMapper;
	
	//@Autowired
	private UserMapper userMapper;
	
	//@Scheduled(cron="0 * * * * *")
	public void updateUserBodyInfoTask() {
		
		Badge defaultBadge = new Badge();
		List<User> allUserList = userMapper.getAllUsers();
		
		for(User user : allUserList) {
			
			////UserBodyInfo ���̺� �ο� �߰�
			//diaryMapper.addUserBodyInfo(user.getUserId());
			System.out.println(user);
			////Badge ���̺� �ο� �߰�
			if(user.getDietServiceNo() != 0 && user.getExerServiceNo() != 0) {
				//�Ĵ�, � ���� ��� �̿�
				defaultBadge.setUserId(user.getUserId());
				defaultBadge.setDailyBurnningKcal(0.0);
				defaultBadge.setDailyIntakeKcal(0.0);
				diaryMapper.addBadge(defaultBadge);
				System.out.println(defaultBadge);
			} else if(user.getDietServiceNo() == 0 && user.getExerServiceNo() != 0) {
				//�Ĵ� ���� �̿�
				defaultBadge.setUserId(user.getUserId());
				defaultBadge.setDailyIntakeKcal(0.0);
				diaryMapper.addBadge(defaultBadge);
				System.out.println(defaultBadge);
			} else if(user.getDietServiceNo() != 0 && user.getExerServiceNo() == 0) {
				//� ���񽺸� �̿�
				defaultBadge.setUserId(user.getUserId());
				defaultBadge.setDailyBurnningKcal(0.0);
				diaryMapper.addBadge(defaultBadge);
				System.out.println(defaultBadge);
			} else {
				//�Ĵ�, � ��� �̿����� ����
				defaultBadge.setUserId(user.getUserId());
				diaryMapper.addBadge(defaultBadge);
				System.out.println(defaultBadge);
			}
		}
	}
	
}
