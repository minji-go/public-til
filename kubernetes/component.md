## 쿠버네티스 컴포넌트
- master 컴포넌트
    - etcd : worker node에 대한 컨테이너 기반의 상태정보를 저장한 key-value 타입의 저장소
    - kube-apiserver : k8s API를 사용하도록 요청을 받고 ,요청이 유효한지 검사
    - kube-scheduler : pod를 실행할 node를 선택
    -kube-controller-manager : pod를 관찰하며 개수를 보장
- worker node 컴포넌트
    - kubelet : =대문, 모든 node에서 실행되는 k8s 에이전트, 데몬 형태로 동작
    - kube-proxy : k8s network 동작을 관리, iptables rule을 구성
    - 컨테이너 런타임 : 컨테이너를 실행하는 엔진, docker, containerd, runc



## namespace
- base namespace : default
- namespace 생성
    - CLI
    - `$ kubectl create namespace blue`
    - yaml
    - `$ kubectl create namespace green --dry-run -o yaml > green-ns.yaml`
    - `$ vim green-ns.yaml`
    - `$ kubectl create -f green-ns.yaml`

- namespace에 pod 생성
    - `$ kubectl create -f nginx.yaml`
    - `$ kubectl create -f nginx.yaml -n blue`

- namespace 조회
    - `$ kubectl getnamespaces`
    - `$  kubectl get pods --all-namespaces`
    - `$ kubectl get pod --namespace default`
    - `$ kubectl get pod -n default`

- base namespace 변경
    - `$ kubectl config current-context`
    - `$ kubectl config set-context blue@kubernetes --cluster=kubernetes --user=kubernetes-admin --namespace=blue`
    - `$ kubectl config view`
    - `$ kubectl config use-context blue@kubernetes`

- namespace 삭제
    - `$ kubectl delete namespace blue`