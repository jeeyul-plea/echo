# Base Image 지정
FROM openjdk:17
# 호스트 파일을 도커 컨테이너의 파일 시스템으로 복사
COPY ./build/libs/test.jar app.jar
# 환경 변수 설정
ENV TZ=Asia/Seoul
# 이미지가 실행 될 때 항상 실행되어야 하는 커맨드 정의
ENTRYPOINT ["java", "-jar", "app.jar"]