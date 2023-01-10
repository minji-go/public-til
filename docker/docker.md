## Why Docker?
- 컨테이너의 효용
https://www.44bits.io/ko/post/why-should-i-use-docker-container

## Command Lines
- docker ps : 현재 구동중인 컨테이너 확인
- docker images : 이미지 확인  
<br>

- docker run --name NAME -p 8080:80 IMAGE : 컨테이너 실행(host 포트 8080과 컨테이너 포트 80 연결)
- docker run --name NAME -v ~/Desktop/htdocs:/usr/local/apache2/htdocs IMAGE: 컨테이너 실행(host의 파일시스템과 컨테이너의 파일시스템 연동=host 파일수정시 바로반영)
- docker exec -it CONTAINER /bin/sh: 컨테이너 지속적 접속
- docker exec -it CONTAINER /bin/bash: bash shell로 컨테이너 지속적 접속
<br>

- docker stop CONTAINER : 컨테이너 STOP
- docker rm CONTAINER: 컨테이너 삭제 
- docker rmi CONTAINER : 이미지 삭제 
- docker stop $(docker ps -a -q) : 전체 컨테이너 STOP 
- docker rm $(docker ps -a -q) : 전체 컨테이너 삭제
- docker logs CONTAINER
<br>

- docker-compose build 
- docker-compose up -d 
- doxker-compose down
- docker-compose logs -f : 로그 지속적 확인