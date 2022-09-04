## kubectl 명령어 기본구조

`kubectl [command] [TYPE] [NAME] [flags]`

- `[command]` : 자원(object)에 실행할 명령 (create, get, delete, edit..)
- `[TYPE]` : 자원의 타입(node, pod, service…)
- `[NAME]` : 자원의 이름 (원하는대로 지정)
- `[flags]` : 부가적으로 설정할 옵션 (—help, -o options)


- pod 생성
    - 하나 : `kubectl run webserver --image=nginx:1.14 --port 80`
    - 여럿 : `kubectl create deployment mainui --image=httpd --replicas=3`

- pod 조회
    - `kubectl get pods`
    - `kubectl get pods -o wide`

    - `kubectl get pod webserver`
    - `kubectl get pod webserver -o yaml`
    - `kubectl get pod webserver -o json`

    - `kubectl describe pod webserver`
    - `kubectl get deployments.apps`

- pod 실행
    - curl 10.44.0.1
    - `sudo apt-get update`
        `sudo apt install elinks`
        `elinks 10.44.0.1`

- pod 제거
    - `kubectl delete pods webserver`
    - `kubectl delete deployments.app mainui`

