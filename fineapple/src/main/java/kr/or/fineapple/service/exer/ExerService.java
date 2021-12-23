package kr.or.fineapple.service.exer;

import java.util.Map;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.common.Search;

public interface ExerService {
	
	public void addUserService(ExerService service)throws Exception;
	
	public void getUserService(ExerService service)throws Exception;
	
	public void addDailyBurnning(BurnningRecord record)throws Exception;
	
	public void updateServiceTrgt(ExerService service)throws Exception;
    
	public void updateBodyInfo(ExerService serivce)throws Exception;
	
	public Map<String ,Object> getExerList(Search search)throws Exception;
	
	
	public void addExer(Exer exer)throws Exception;
	
	public Exer getExer(int exerNo)throws Exception;
	
	public int postUpdateExer(Exer exer)throws Exception;
	
	public int deleteExer(int exerNo)throws Exception;
	
	
	public void updateUserService(ExerService serivce)throws Exception;
	
	
}