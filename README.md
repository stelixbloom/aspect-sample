# サンプルAOP
実行時のgreeting値とステータス情報をRedisに格納する。

## 実行手順

①Docker Build

```
docker build -t sample-aspect .
```

②redisとspringアプリケーションのapply（ → k8sディレクトリ README.md 参照）

[k8s/README.md](k8s/README.md)

③springアプリケーションPodへログイン

```
kubectl exec -it spring-boot-app -n redis
```

④curl実行

```
curl http://localhost:8080/001
```

## 補足

- springアプリケーションのログの監視
```
kubectl logs -f spring-boot-app -n redis
```

- RedisPodへのログイン（Pod名要確認）
```
kubectl exec -it redis-deployment-7f897d4cbc-skh79 -n redis -- bash
```

- RedisPodへのログイン後、Redis接続
```
redis-cli
```

- Redis接続後、キー検索
```
scan 0
```
