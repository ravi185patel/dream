package lld.tinyurl.multipleinstance;

public class TinyUrlService {

    public RedisInstance redisInstance;

    public TinyUrlGenerator tinyUrlGenerator;

    public TinyUrlService(RedisInstance redisInstance,TinyUrlGenerator tinyUrlGenerator){
        this.redisInstance= redisInstance;
        this.tinyUrlGenerator = tinyUrlGenerator;
    }

    public String setTinyUrl(String url){
        boolean isUrlExists=redisInstance.isUrlExists(url);
        if(isUrlExists){
            return redisInstance.getUrl(url);
        }
        String tinyUrl = tinyUrlGenerator.generateTinyUrl(url);
        redisInstance.setTinyUrl(url,tinyUrl);
        return tinyUrl;
    }

    public String getUrl(String tinyUrl){
        return redisInstance.getUrl(tinyUrl);
    }

    public String getTinyUrl(String url){
        return redisInstance.getTinyUrl(url);
    }

    public boolean isUrlExists(String url){
        return redisInstance.isUrlExists(url);
    }
}
