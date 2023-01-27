## 보안그룹 (Security Group)
##### 💡보안그룹
```
- NACL과 함께 가상의 방화벽의 역할을 하는 서비스

- 인스턴스 단위(정확히는 ENI단위)
    - 하나의 인스턴스에 하나 이상의 SG설정 가능
    - 설정된 인스턴스는 설정한 모든 SG의 룰을 적용 받음

- Port 허용만 가능 (Deny는 불가능)
    - 기본적으로 모든 포트 비활성화
    - 선택적으로 트래픽이 지나갈 수 있는 Port와 Source를 설정 가능

- Stateful
    - Inbound로 들어온 트래픽이 별 다른 Outbound설정 없이 나갈 수 있음
```

##### 💡보안그룹 Source (트래픽의 근원)
```
- IP Range(CIDR)
- 접두사 목록
- 다른 보안그룹
```

```
✔️ CIDR
``` 

```
✔️ 접두사 목록
- 하나 이상의 CIDR 블록의 집합
- 보안 그룹 혹은 Rout Table에서 많은 대상을 참조하기 위해 사용
- 종류
    - 고객 관리형 : 직접 IP주소를 생성/수정/삭제할 수 있으며 다른 계정과도 공유 가능
    - AWS 관리형 :  S3, CloudFront등 AWS 서비스를 위한 IP목록으로 수정/삭제 불가능
- IPv4, IPv6 둘다 사용 가능, 단 한 접두사 목록에 두 가지 타입을 동시에 사용 불가능
- 생성 시점에 최대 엔트리 숫자를 지정(이후 변경 가능)
```

###### 💡보안그룹 Limit
```
- 리전 당 2,500개 
- 인바운드 규칙 60개, 아웃바운드 규칙 60개(IPv4, IPv6 구분) 
- ENI당 최대 보안 그룹은 5개, 최대 16개 까지 증가 가능
- 한 인스턴스당 최대 적용 가능 규칙은 1,000개
```
---
## NACL (Network Access Control List)
##### 💡NACL 
```
- 가상의 방화벽
- 서브넷 단위
- Port의 허용과 Deny가능
    - 외부 공격을 받는 상황 등 특정 아이피를 블록하고 싶을 때 사용
- Stateless
```
##### 💡NACL 규칙
```
- 규칙 번호 : 규칙에 부여되는 고유숫자이며, 규칙이 평가되는 순서
    - 낮은 번호부터 
    - AWS 추천은 100단위 증가
- 유형 : 미리 지정된 프로토콜. 선택시 AWS에서 잘 알려진 포트(Well Known Port)이 규칙에 지정됨
- 프로토콜: 통신 프로토콜 (ex. TCP, UDP, SMP...)
- 포트 범위 : 허용 혹은 거부할 포트 범위
- 소스 : IP 주소의 CIDR 블록
- 허용/거부 : 허용 혹은 거부 여부
```

---
## Reference
- 📽️Youtube_ [쉽게 설명하는 AWS 기초 강좌 17: 보안그룹과 NACL](https://www.youtube.com/watch?v=IJgAIbxxJCE&list=PLfth0bK2MgIan-SzGpHIbfnCnjj583K2m&index=18)
- 📽️Youtube_ [보안그룹 총 정리](https://www.youtube.com/watch?v=0hXYfi55_Ww&t=0s)
- 📽️Youtube_ [NACL 총 정리](https://www.youtube.com/watch?v=mZf8eBE1bOk&t=0s)
