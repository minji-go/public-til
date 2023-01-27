## VPC (Virtual Private Cloud)
```
- 가상의 데이터센터
- 외부에 격리된 네트워크 컨테이너 구성 가능
    - 원하는 대로 사설망을 구축 가능 = 부여된 IP 대역을 분할하여 사용가능
    - 다른 AWS 서비스와 달리 외부 Public Internet에서 접근 불가능
- 리전 단위
- 사용 사례
    - EC2, RDS, Lambda 등의 AWS의 컴퓨팅 서비스 실행
    - 다양한 서브넷 구성
    - 보안 설정(IP Block, 인터넷에 노출되지 않는 EC2등 구성)
```
---
## VPC 구성요소
```
- 서브넷
- 인터넷 게이트웨이
- 라우트 테이블
- NACL/보안그룹
- NAT Instance/NAT Gateway
- Bastion Host
- VPC Endpoint
```
- 

##### 💡 서브넷(Subnet)
```
- VPC의 하위 단위로, VPC에 할당된 IP를 더 작은 단위로 분할한 개념
- 하나의 서브넷은 하나의 가용영역(AZ)안에 위치 ***
- CIDR block range로 IP 주소 지정
```
```
✔️ AWS 서브넷의 IP 갯수
- AWS의 사용 가능 IP숫자는 5개를 제외하고 계산
- 예: 10.0.0.0/24라면, 2^⁸-5 = 251개
```
```
✔️ 서브넷의 종류
- 퍼블릭 서브넷 : 외부에서 인터넷을 통해 연결할 수 있는 서브넷
    - 인터넷 게이트웨이(IGW)를 통해 외부의 인터넷과 연결되어 있음
    - 안에 위치한 인스턴스에 퍼블릭 IP 부여 가능
    - 웹서버, 어플리케이션 서버 등 유저에게 노출되어야 하는 인프라

- 프라이빗 서브넷 : 외부에서 인터넷을 통해 연결할 수 없는 서브넷
    - 외부 인터넷으로 경로가 없음
    - 퍼블릭 IP 부여 불가능
    - 데이터베이스, 로직 서버 등 외부에 노출 될 필요가 없는 인프라
```


##### 💡 라우트 테이블(Route Table)
```
- 트래픽이 어디로 가야 할지 알려주는 이정표
    - 가장 구체적인 Destination을 선택하여 Target에 보냄
- VPC 생성시 기본으로 하나 제공
```

##### 💡 인터넷 게이트웨이(Internet gateway)
```
- VPC가 외부의 인터넷과 통신할 수 있도록 경로를 만들어주는 리소스
- 기본적으로 확장성과 고가용성이 확보되어 있음
    - 트래픽 용량에 따라 확장하거나 프로비전할 필요가 없음
- IPv4, IPv6 지원
    -  IPv4의 경우 NAT 역할
- Route Table에서 경로 설정 후에 접근 가능
- 무료
```
##### 💡 NAT  Gateway / NAT Instance
```
- Private 인스턴스가 외부의 인터넷과 통신하기 위한 통로
- NAT Instance는 단일 EC2 인스턴스
- NAT Gateway는 AWS에서 제공하는 서비스 
    - 관리가 따로 필요하지 않음
    - NAT Instance보다 비용이 비쌈
- NAT Instance / Gateway는 모두 서브넷 단위
    - Public Subnet에 위치해야 함
```
##### 💡 Bastion Host (Jump Host)
```
- Private 인스턴스에 접근하기 위한 EC2 인스턴스
- Public Subnet에 위치해야 함
- 실전에서는 거의 사용하지 않음
```

---
## Reference
- 📽️Youtube_ [쉽게 설명하는 AWS 기초 강좌 16:VPC 와 Subnet](https://www.youtube.com/watch?v=WY2xoIClOFA)
- 📽️Youtube_ [쉽게 설명하는 AWS 기초 강좌 18: NAT Gateway & Bastion Host](https://www.youtube.com/watch?v=vwlLyoQM69o&list=PLfth0bK2MgIan-SzGpHIbfnCnjj583K2m&index=19)