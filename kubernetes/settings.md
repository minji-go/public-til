직접 설치
- 참고 영상 : [https://www.youtube.com/watch?v=CKUv3...](https://www.youtube.com/watch?v=CKUv3oEI8Yo&t=363s)

1. Virtual Box 설치 
- [https://www.virtualbox.org/wiki/Downloads](https://www.virtualbox.org/wiki/Downloads)


2. Ubuntu 20.04 LTS 다운로드
- [https://ubuntu.com/download/desktop](https://ubuntu.com/download/desktop)

3. K8S의 master로 사용할 vm 생성 및 unbuntu:20.04 설치

4. ubuntu -install 진행
- CD를 선택해서 부팅

5. root 패스워드 설정
- `$ sudo passwd root`

6. 설치된 프로그램 업데이트 완료

7. ssh server 설치 및 서비스 동작
- `$ su -`
- `# apt-get update`
- `# apt-get install -y openssh-server curl vim tree`


8. virtualbox 구성 - 클립보드 공유 설정

9. 가상머신 부팅 방식을 GUI에서 text 로그인으로 변경

10. docker install

11. 가상머신 복제
- Master, Node1, Node2 시스템에 도커 설치

12. 각 시스템 snapshot을 만들어서 이후 이 시점으로 되돌리기 가능하도록 구성

13. 쿠버네티스 설치 : v1.18
