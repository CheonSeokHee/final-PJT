package kr.or.fineapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.ExerServ;
import kr.or.fineapple.domain.Routine;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;


@Mapper
@Repository
public interface ExerMapper {

	   //����� Ȱ��ȭ CRUD
	   int addUserService(ExerServ service);
	   
	   ExerServ getUserService(String userId);
	   
	   
	   int updateUserService(ExerServ service);
	   
       
       int updateServiceTrgt(ExerServ service);
       
       
       int updateBodyInfo(ExerServ service);

       
       
       
       //���� ��� CRUD
       int addDailyBurnning(BurnningRecord record);
       
       List<BurnningRecord> getBurnningRecordList(int userServiceNo);
       
       int updateBurnningRecord(BurnningRecord record);
       
       Double sumBurnningKcal(int userServiceNo);
       
       void deleteBurnningRecord(int burnningRecordNo);
       
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
       
       int updateRoutineName(Routine routine);
       
       int deleteRoutine(int routineNo);
       
       List<Routine> getRoutineList(int exerServiceNo);
       
       
       
       //��ƾ�� ����� � 
       int addRoutineInfo(Routine routine);
       
       Routine getRoutineInfo(int routineInfoNo);
       
       int deleteRoutineInfo(int routineInfoNo);
       
       int updateRoutineInfo(Routine routine);
       
       List<Routine> getRoutineInfoList(int routineNo);
       
       
       
       //��õ�����Ʈ
       List<Exer>recommandExerList(Double overKcal);
       
       Double sumIntakeKcal(String userId);
   
       User needDaliyIntakeKcal(String userId);
       

   }
