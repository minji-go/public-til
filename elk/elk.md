#Setting
1. AWS EC2 Ubuntu 생성
    ```markdown
    - 보안그룹 : 5601, 5044, 9200 포트
    - 타겟 그룹: HTTP 5601, 9200
    - 로드밸런서: 각각 새로운 타겟그룹으로 forward
    ```
2. Ubuntu에 ElasticSearch, Logstash 설치
    ```bash
    $ git clone https://github.com/deviantony/docker-elk.git

    $ docker-compose up -d
    ```

3. ElasticSearch 접속 확인
    ```markdown
    # https:// ${IP}:9200
    - user : elastic
    - password : changeme
    ```

4. Logstash와 MySQL 연동
    - logstash/logstash.conf
    ```
    ```


#Reference
- https://shanepark.tistory.com/241