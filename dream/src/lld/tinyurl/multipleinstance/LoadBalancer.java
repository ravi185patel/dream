package lld.tinyurl.multipleinstance;

import java.util.HashMap;
import java.util.Map;

public class LoadBalancer {
    Map<String,TinyUrlService> serviceMap;
    Map<String,RedisInstance> redisInstanceMap;

    public LoadBalancer() {
        serviceMap = new HashMap<>();
        redisInstanceMap = new HashMap<>();
    }

    public TinyUrlService getService(String origin){
        return serviceMap.get(origin);
    }

    public RedisInstance getRedis(String origin){
        return redisInstanceMap.get(origin);
    }

    public void registerTinyUrlService(String origin,TinyUrlService tinyUrlService){
        serviceMap.put(origin,tinyUrlService);
    }

    public void registerRedisInstance(String origin,RedisInstance redisInstance){
        redisInstanceMap.put(origin,redisInstance);
    }
}
