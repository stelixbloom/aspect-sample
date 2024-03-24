# コンテナ起動（k8s）

## Redis 起動

```
kubectl apply -f redis.yaml -n redis
```

## Spring Boot Application 起動

```
kubectl apply -f spring-boot-app.yaml -n redis
```

