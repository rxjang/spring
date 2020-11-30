# Safetia 과제 제출
## 구현 환경 ##
- JDK 1.8
- spring 3.2
- MySQL 8.0
- MyBatis 3.5
- Maven 2.9
- Junit 4
- Lombok

####
- 테이브 생성 쿼리문은 sql/table.sql에 있습니다. 
- CRUD쿼리 문은 src/main/resources/config/Sus_01Mapper.xml, Sus_02Mapper.xml에 있습니다.
- 상품 정보 삭제는 데이터의 축적을 위해 delete 쿼리문을 사용 하는 대신 각 테이블에 deleted 컬럼을 만들어서 활용했습니다. (0일 시 존재하는 데이터, 1일 시 삭제된 데이터)
- Junit test를 사용하여 구혀 기능의 작동 여부를 확인 했습니다. (src/test/java/task/service/SusServiceTest.java)
