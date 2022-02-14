# standalone-wiremock
Standalone wiremock application: user session api

### prerequisites

* install java
* install maven

### build

```
mnv clean install
```

### run

```
java -jar target/standalone-mock.jar
```

### docker
```
docker build -t standalone-mock .
docker run --rm -p 8080:8080 standalone-mock
```


### test
```
curl http://localhost:8080/api/users/session/player
curl http://localhost:8080/api/users/session/admin
curl http://localhost:8080/api/users/session/wrongId
curl http://localhost:8080/api/users/session/1
curl http://localhost:8080/api/users/session/2
curl http://localhost:8080/api/users/session/3 and so on
```