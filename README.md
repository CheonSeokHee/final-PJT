# final-PJT

## 시스템 개요
- 코로나19 팬데믹 이후 달리기와 등산 등 건강과 식단관리에 대한 사람들의 관심이 나날이 커지고 있다. 
팀 null 좋아해는 이와 같은 트렌드에 맞추어 사용자에게 자신의 신체 정보와 식단, 운동 정보를 모두 통합하여 조회할 수 있도록 함으로써 사용자가 일상 속에서 보다 쉽고 편리하게 자신의 건강을 관리할 수 있도록 도와줄 어플리케이션 개발을 기획하게 되었다. 

- FineApple은 식단관리, 운동관리, 습관관리, 다이어리, 커뮤니티 기능을 제공하는 라이프스타일 통합 관리 서비스 플랫폼으로 사용자들은 이 시스템을 통해 개인의 식단 목표, 
운동 목표, 습관 목표 달성 과정을 기록하고 확인할 수 있다. 식품의약안전처가 제공하는 식품 정보를 확인하고 이 정보를 내가 섭취한 음식으로 식단에 기록할 수 있다. 
뿐만 아니라 약 100개의 운동 정보를 확인하고 해당 운동을 수행한 강도와 시간에 따라 달라지는 사용자의 소모 칼로리를 매일 기록할 수 있다. 
사용자는 이렇게 자신이 기록한 정보들을 나의 다이어리에서 통합적으로 조회할 수 있다.

- FineApple은 사용자가 자신의 라이프스타일을 보다 편리하게 관리할 수 있도록 도움을 주는 생활밀착형 서비스를 제공하는 데 그 의의가 있다

- ![image](https://user-images.githubusercontent.com/92357194/169931055-4e89b3c2-9077-4192-b2f0-2a5e2a0cd1b6.png)



# 운동관리 서비스

- <운동 서비스 활성화>

  운동 서비스 활성화 시 사용자는 본인의 목표 소모 칼로리와 골격근량 목표 골격근량 입력

- <운동 리스트>

  100가지의 운동 데이터를 통해 사용자에게 운동 정보 제공, 소모 칼로리와 유튜브 링크를 통해 운동 영상 조회 가능

- <일일 운동량>

  사용자가 당일에 운동한 정보를 기록할 경우 신체정보를 바탕으로 계산된 적정 섭취 칼로리에 따라 소모 칼로리 정보를 제공
  섭취 칼로리를 초과했을 시 추천운동 리스트 제공
  목표 소모 칼로리 이상 운동 시 뱃지 획득 가능(일별 지급)

- <운동 장소 제안>

  전국의 공원 위치를 지도의 마커를 통해 제공
  사용자 현 위치에서 도보/자전거 이용 시 운동 소요 시간과 거리(직선 거리 기준) 계산 가능

- <운동하기>

  운동 별 세트 수, 운동 시간을 입력할 수 있는 타이머 제공 
  타이머를 이용해 운동 시 타이머 기록을 바탕으로 일일 운동 기록 가능


## 예상효과
 - 운동 관리 서비스
 
   운동 별 소모 칼로리 정보 조회, 운동 추천 기능과 운동 타이머 기능 제공을 통해 사용자가 혼자서도 체계적으로 운동할 수 있도록 도움을 줄 수 있음


# 기술적 고려사항
- 사용 아키텍쳐

-MVC 패턴을 적용한 2-Layered 아키텍처

- 구현 언어

-J2SE & JavaEE(JDK 1.8), JavaScript, SQL, SpEL(Spring Expression Language), HTML5, CSS

- 사용 프레임워크

-Java Spring Framework 5.3.14, 

-SpringBoot 2.6.2, 

-MyBatis 3.5.7, MyBatis-Springs 2.0.6, 

-Bootstrap Framework 5, PWA(Progressive Web App), Gradle 7.3.3

- 사용 라이브러리

-JUnit 4.13.2, 

-Lombok 1.18.20, 

-log4j 2.14.1, jQuery, Ajax, Thymeleaf 3.0.12, 

-DataTables 1.11.3, FullCalendar 5.10.1, Chart.js 3.7.0

- 서버

-WAS: AWS Elastic Beanstalk(SpringBoot 내장 Tomcat 이용)

-DB: AWS RDS(Oracle 10g)

-HTTPS: Korealssl

-Domain: HostingKr

- 사용 API

-카카오 로그인 API, 식약처 식품영양정보 API, 네이버 쇼핑 API, 카카오 Map API, GEO Location

![image](https://user-images.githubusercontent.com/92357194/169930990-4b71979f-b5df-48ed-9cd9-4b700a494701.png)


## 개발 이력

- 개발 기간

-분석, 설계: 4주
-개발: 4주
-문서 작성: 1주

- 적용 방법론

-객체 지향 방법론

- 사용툴
 
-한글2022, Eclipse 4.19.0, Oracle SQL Developer 21.4.1

- 형상관리

-GitHub

-개발 인원 5명

-Contents 기획, 모듈별 Full-Stack 개발(각 모듈 별 시스템 아키텍처 및 DB 설계, Web, App 개발)로 역할을 분류하여 아래와 같이 담당
