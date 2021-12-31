package kr.or.fineapple.diet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import groovyjarjarantlr4.v4.runtime.misc.FlexibleHashMap.Entry;
import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.FavMeal;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.service.diet.DietService;
import kr.or.fineapple.service.user.UserService;

@Controller
@RequestMapping("/diet/*")
public class DietController {

	@Autowired
	@Qualifier("dietServiceImpl")
	private DietService dietService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	
	@GetMapping("addDietService")
	public String addDietServiceget(HttpServletRequest request,Model model) throws Exception {
		
		System.out.println("get:addDietService");
		
		if((User)request.getSession(true).getAttribute("user")!=null) {
			User user =(User)request.getSession(true).getAttribute("user");
			System.out.println(user);
		
			DietServ serv = dietService.getDietService(user.getUserId());
			System.out.println(serv);
			if(serv!=null) {
					if(serv.getUserServiceNo()!=0) {
			
						model.addAttribute("user",user);
						model.addAttribute("dietServ",serv);
						return "diet/getDietService.html";
						}else {
							return "diet/addDietService.html";}
			}else {
				model.addAttribute("user",user);
				return "diet/addDietService.html";}
			
		}else {
			

			return "user/login"; }

		
	}
		
		
	
	

	@PostMapping("addDietService")
	public String addDietService(@ModelAttribute("DietServ")DietServ serv,
								HttpServletRequest request,
								Model model) throws Exception{
		System.out.println("post:addDietService");
		System.out.println(serv);
		
		User user =(User)request.getSession(true).getAttribute("user");
		String userId = user.getUserId();
		serv.setUserId(userId);
		
		if(user.getDietServiceNo()==0) {
		dietService.addDietService(serv);
		}else {
			dietService.updateDietService(serv);
		}
				
		serv = dietService.getDietService(userId);
		model.addAttribute("dietServ",serv);
		model.addAttribute("user",user);
		
		

		return "diet/getDietService.html";
	}
	
	@GetMapping("getDietService")
	public String getDietService(Model model, HttpServletRequest request) {
		
		
		
		return "diet/getDietService.html";
	}
	
	@GetMapping("updateDietService")
	public String updateDietService(Model model, HttpServletRequest request) throws Exception {
		System.out.println("get:updateDietService");
		
		User user =(User)request.getSession(true).getAttribute("user");
		System.out.println(user);
	
		DietServ serv = dietService.getDietService(user.getUserId());
		
		
		model.addAttribute("user",user);
		model.addAttribute("dietServ",serv);
		
		return "diet/updateDietService.html";
			
		
	}
	
	

	@GetMapping("getFoodList")
	public String getFoodList(Model model, @ModelAttribute("search") Search search,HttpServletRequest request) throws Exception {
			
		Search search1 = new Search();
		search1.setCurrentPage(1);
		search1.setPageSize(30);
		search1.setSearchCondition(0);
		search1.setSearchKeyword(search.searchKeyword);

		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Map<String, Object> map4 = new HashMap<String, Object>();
		List list = new ArrayList();
		List<Food> list2 = new ArrayList<Food>();
		list=dietService.getFoodAPIlist(search);
		
		list2=dietService.getFoodList(search1);
		
		list.addAll(list2);
		
		
//		JSONArray array = new JSONArray();
//		JSONArray array2 = new JSONArray();
//		
//		list2=dietService.getFoodList(search1);
//		
//		   for (Food userFood : list2) {
//		        array2.add(userFood);
//		    }
//		array=dietService.getFoodAPIlist(search);
//
//		array.addAll(array2);
//		
		
		model.addAttribute("list", list);
		model.addAttribute("search", search);
		

		
		
		

		return "diet/getFoodList.html";
	}

	@GetMapping("getFood")
	public String getFood(@RequestParam("foodCd")String foodCd, Model model) throws Exception {
		
		Food food = new Food();
		food = dietService.getFood(foodCd);
		System.out.println(foodCd);
		model.addAttribute("food",food);
		return "diet/getFood.html";
		}

	
	
	@GetMapping("getPurchaseFoodList")
	public String PurchasaeFoodList(Model model,@ModelAttribute("search") Search search) throws Exception {
		System.out.println("post:getPurchaseFoodList");

		search.setCurrentPage(1);
		search.setPageSize(30);
		search.setSearchCondition(0);
		if(search.searchKeyword=="") {
			search.setSearchKeyword("샐러드");
		}

		List list = new ArrayList();
		String result = dietService.shoppingAPI(search.searchKeyword);
		  JSONParser parser = new JSONParser();
          JSONObject obj = (JSONObject)parser.parse(result);
          System.out.println(obj);
          JSONParser parser2 = new JSONParser();
          Object obj2 = parser2.parse(obj.get("items").toString());
          JSONArray array2 = (JSONArray)obj2;
          for(int i=0; i<array2.size(); i++) {
          System.out.println(((JSONObject)array2.get(i)).get("image"));
          Food food = new Food();
         if(((JSONObject)array2.get(i)).get("category1").toString().equals("식품")) {
          food.setFoodImg(((JSONObject)array2.get(i)).get("image").toString());
         food.setPrice(Integer.parseInt(((JSONObject)array2.get(i)).get("lprice").toString()));
         food.setFoodName(((JSONObject)array2.get(i)).get("title").toString());
         food.setPurchaseConnLink(((JSONObject)array2.get(i)).get("link").toString());
         food.setMakerName(((JSONObject)array2.get(i)).get("brand").toString());
         food.setStoreName(((JSONObject)array2.get(i)).get("maker").toString());
         
          list.add(food);
          }
          }
          
          model.addAttribute("list",list);
          model.addAttribute("search",search);
		
		

		return "diet/getPurchaseFoodList.html";

	
}
	@GetMapping("getFavMealList")
	public String getFavMealList(Model model,HttpServletRequest request)	throws Exception {
			System.out.println("getFavMealList");
			
			Map<String,Object> map = new HashMap<String,Object>();
			
			User user =(User)request.getSession(true).getAttribute("user");
			DietServ serv = dietService.getDietService(user.getUserId());
			map = dietService.getFavMealList(serv.getUserServiceNo());
			System.out.println(map);
			model.addAttribute("list",map.get("list"));
		return "diet/getFavMealList.html";
	}
		
@GetMapping("getAddDaily")
public String getAddDaily(Model model)throws Exception{
	
	
	
	
	return "diet/getAddDailyIntakeMeal.html";
}
	

@GetMapping("modal")
public String modal(Model model,HttpServletRequest request,@RequestParam("checkarray")String foodCd) throws Exception {
	
	String hong = "byung";
	Map<String,Object> map = new HashMap<String,Object>();
	
	User user =(User)request.getSession(true).getAttribute("user");
	DietServ serv = dietService.getDietService(user.getUserId());
	map = dietService.getFavMealList(serv.getUserServiceNo());
	System.out.println(map);
	model.addAttribute("list",map.get("list"));
	System.out.println(foodCd);
	model.addAttribute("foodCd",foodCd);
	
	return "diet/getFavMealList :: hong";
	
	
}





@GetMapping("addFavMealItem")
public String getaddFavMeal(Model model, @RequestParam("checkarray")String foodCd,HttpServletRequest request) throws Exception {
		
	System.out.println("오긴온다");
	
	Food food = new Food();
		
	food = dietService.getFood(foodCd);
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	User user =(User)request.getSession(true).getAttribute("user");
	DietServ serv = dietService.getDietService(user.getUserId());
	map = dietService.getFavMealList(serv.getUserServiceNo());
	System.out.println(map);
	
	model.addAttribute("list",map.get("list"));
	model.addAttribute("food", food);

	return "diet/addFavMealItem :: addFavMealItem";
}


@PostMapping("addFavMealItem")
public String postaddFavMeal(Model model, @RequestParam("favMealNo")int favMealNo,
								@RequestParam("userFoodIntake")double userFoodIntake,
								@RequestParam("foodCd")String foodCd) throws Exception {
	
	System.out.println("addFav진입");
	System.out.println(favMealNo+"+"+userFoodIntake+"+"+foodCd);
	
	double Intake = 0;
	Food food = new Food();
	if(!foodCd.substring(0).equals("U")) {
		food = dietService.getFood(foodCd);
	
	}
	
	FavMeal fav = new FavMeal();
	Intake= (userFoodIntake/food.getServingSize());
	food.setFoodKcal(food.getFoodKcal()*Intake);
	food.setFoodCarb(food.getFoodCarb()*Intake);
	food.setFoodProtein(food.getFoodProtein()*Intake);
	food.setFoodFat(food.getFoodFat()*Intake);
	
	
	
	fav.setIntake(food.getServingSize()*Intake);
	fav.setFood(food);
	fav.setFavMealNo(favMealNo);
	
	dietService.addFavMealItem(fav);
	return null;
}


	

}



