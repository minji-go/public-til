## Why Docker?
- 컨테이너의 효용
https://www.44bits.io/ko/post/why-should-i-use-docker-container

## Command Lines
- docker ps : 현재 구동중인 컨테이너 확인
- docker images : 이미지 확인
- docker exec -it ${ID} : 컨테이너 접속
<br>
- docker stop ${ID} : 컨테이너 STOP
- docker rm ${ID} : 컨테이너 삭제
- docker rmi ${ID} : 이미지 삭제
- docker stop $(docker ps -a -q) : 전체 컨테이너 STOP
- docker rm $(docker ps -a -q) : 전체 컨테이너 삭제
<br>
- docker-compose build 
- docker-compose up -d
- docker-compose logs -f : 로그 확인
