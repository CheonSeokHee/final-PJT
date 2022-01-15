package kr.or.fineapple.mapper;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.FavMeal;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.TotalRecord;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.common.ViewDuration;

@Mapper
@Repository
public interface DietMapper {

       int insertDietService(DietServ diet);
       
       int updateDietServiceNo(DietServ diet);
       
       DietServ getDietService(String userId);
       
       int insertIntakeRecord(IntakeRecord record);
       
       int updateBodyInfo(DietServ diet);
       
       int updateServiceTrgt(DietServ diet);
       
       List<Food> getFoodList(Search search);
       
       int updateDietService(DietServ diet);
       
       int insertFavMeal(FavMeal favMeal);
       
       int updateFavMealName(FavMeal favMeal);
       
       int deleteFavMeal(int favMealNo);
       
       List<FavMeal> getFavMealList(int dietServiceNo);
       
       int insertFavMealItem(FavMeal favMeal);
       
       List<FavMeal> getFavMealItemList(int favMealNo);
       
       FavMeal getFavMealItem(int favMealInfoNo);
       
       int deleteFavMealItem(int favMealinfoNo);
       
       int updateFavMealItem(FavMeal favMeal);
       
       List<IntakeRecord> getIntakeRecordList(int dietServiceNo);
       
       int updateIntakeRecord(IntakeRecord record);
       
       int addFood(Food food);
       
       int updateFood(Food food);
       
       int deleteFood(String foodCd);
       
       Food getFood(String foodCd);
       
       void deleteIntakeRecord(int IntakeRecordNo);
       
       List<IntakeRecord> FavIntake(String userId);     
       ////���̾ ������ �ϸ��ϰ� �ۼ�: Ư�� ������ ���� �Ĵ� ���� ��ȸ
       List<IntakeRecord> getIntakeRecordListForDiary(Map map);
       
       ////���̾ ������ �ϸ��ϰ� �ۼ�: Ư�� ������ ����Һ� �Ϸ� �� ��������
       TotalRecord getTotalDietRecord(ViewDuration viewDuration);
}

