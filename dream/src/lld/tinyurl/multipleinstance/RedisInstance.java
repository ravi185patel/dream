package lld.tinyurl.multipleinstance;

import java.util.HashMap;
import java.util.Map;

public class RedisInstance {
    public Map<String,String> tinyUrlToUrlMap;
    public Map<String,String> urlToTinyUrlMap;

    public RedisInstance() {
        tinyUrlToUrlMap = new HashMap<>();
        urlToTinyUrlMap = new HashMap<>();
    }

    public void setTinyUrl(String url, String tinyUrl){
        tinyUrlToUrlMap.put(tinyUrl,url);
        urlToTinyUrlMap.put(url,tinyUrl);
    }

    public void removeTinyUrl(String url){
        String tinyUrl = urlToTinyUrlMap.get(url);
        tinyUrlToUrlMap.remove(tinyUrl);
        urlToTinyUrlMap.remove(url);
    }

    public boolean isUrlExists(String url){
        return urlToTinyUrlMap.containsKey(url);
    }

    public String getUrl(String tinyUrl){
        return tinyUrlToUrlMap.get(tinyUrl);
    }

    public String getTinyUrl(String tinyUrl){
        return urlToTinyUrlMap.get(tinyUrl);
    }
}
