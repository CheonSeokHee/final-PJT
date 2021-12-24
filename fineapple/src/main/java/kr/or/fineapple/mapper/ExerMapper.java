package kr.or.fineapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.ExerService;
import kr.or.fineapple.domain.Routine;
import kr.or.fineapple.domain.common.Search;


@Mapper
@Repository
public interface ExerMapper {

		   
	   int addUserService(ExerService service);
	   
	   int getUserService(ExerService service);
	   
       int addDailyBurnning(BurnningRecord record);
       
       int updateServiceTrgt(ExerService service);
       
       int updateBodyInfo(ExerService service);
       
       
       //�CRUD
       int addExer(Exer exer);
       
       Exer getExer(int exerNo);
       
       List<Search> getExerList(Search search);
       
       int updateExer(Exer exer);
       
       int deleteExer(int exerNo);
       
       
       
       //��ƾCRUD
       int addRoutine(Routine routine);
      
       Routine getRoutine(int routineNo);
      
       int updateRoutine(Routine routine);
       
       int deleteRoutine(int routineNo);
       
       //List<> getRoutineList();
       
       
       //��ƾ�� ����� � 
       int addRoutineInfo(Routine routine);
       
       Routine getRoutineInfo(int routineInfoNo);
       
       int deleteRoutineInfo(int routineInfoNo);
       
       //��õ�����Ʈ
       List<Search>recommandExerList(Search search);
       
       
       int updateExerService(BurnningRecord record);
       

   }
