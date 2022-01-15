package kr.or.fineapple.batch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.mapper.BatchMapper;

@Component
public class MidnightBatchJob {
	
	@Autowired
	private BatchMapper batchMapper;
	
	@Scheduled(cron="0 0 14 * * *") //���� ���� 2�� ������(���������� �����Ͱ� ���̴��� �׽�Ʈ���� Ȯ���� ���� �������� ���濹��)
	public void updateUserBodyInfoTask() {
		
		Badge defaultBadge = new Badge();
		List<User> allUserList = batchMapper.getAllUsers();
		
		for(User user : allUserList) {
			
			////UserBodyInfo ���̺� �ο� �߰�
			batchMapper.addUserBodyInfo(user.getUserId());
			System.out.println(user);
			////Badge ���̺� �ο� �߰�
			if(user.getDietServiceNo() != 0 && user.getExerServiceNo() != 0) {
				//�Ĵ�, � ���� ��� �̿�
				defaultBadge.setUserId(user.getUserId());
				defaultBadge.setDailyBurnningKcal(0.0);
				defaultBadge.setDailyIntakeKcal(0.0);
				batchMapper.addBadge(defaultBadge);
				System.out.println(defaultBadge);
			} else if(user.getDietServiceNo() == 0 && user.getExerServiceNo() != 0) {
				//�Ĵ� ���� �̿�
				defaultBadge.setUserId(user.getUserId());
				defaultBadge.setDailyIntakeKcal(0.0);
				batchMapper.addBadge(defaultBadge);
				System.out.println(defaultBadge);
			} else if(user.getDietServiceNo() != 0 && user.getExerServiceNo() == 0) {
				//� ���񽺸� �̿�
				defaultBadge.setUserId(user.getUserId());
				defaultBadge.setDailyBurnningKcal(0.0);
				batchMapper.addBadge(defaultBadge);
				System.out.println(defaultBadge);
			} else {
				//�Ĵ�, � ��� �̿����� ����
				defaultBadge.setUserId(user.getUserId());
				batchMapper.addBadge(defaultBadge);
				System.out.println(defaultBadge);
			}
		}
	}
	
}
