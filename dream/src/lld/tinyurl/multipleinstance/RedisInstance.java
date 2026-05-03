package lld.tinyurl.multipleinstance;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RedisInstance {
    public Map<String,String> tinyUrlToUrlMap;
    public Map<String,String> urlToTinyUrlMap;

    public RedisInstance() {
        tinyUrlToUrlMap = new ConcurrentHashMap<>();
        urlToTinyUrlMap = new ConcurrentHashMap<>();
    }

    public synchronized String setTinyUrl(String url, String tinyUrl){
        if(urlToTinyUrlMap.containsKey(url)){
            return urlToTinyUrlMap.get(url);
        }
        urlToTinyUrlMap.put(url,tinyUrl);
        tinyUrlToUrlMap.put(tinyUrl,url);
        return tinyUrl;
    }

    public synchronized void removeTinyUrl(String url){
        String tinyUrl = urlToTinyUrlMap.get(url);
        tinyUrlToUrlMap.remove(tinyUrl);
        urlToTinyUrlMap.remove(url);
    }

    public synchronized boolean isUrlExists(String url){
        return urlToTinyUrlMap.containsKey(url);
    }

    public synchronized String getUrl(String tinyUrl){
        return tinyUrlToUrlMap.get(tinyUrl);
    }

    public String getTinyUrl(String tinyUrl){
        return urlToTinyUrlMap.get(tinyUrl);
    }
}
