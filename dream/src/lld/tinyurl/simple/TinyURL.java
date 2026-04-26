package lld.tinyurl.simple;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.random.RandomGenerator;

public class TinyURL {
    public Map<String,String> tinyUrlToUrlMap;
    public Map<String,String> urlToTinyUrlMap;
    Random random;

    public static String chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567899";

    public TinyURL() {
        tinyUrlToUrlMap = new HashMap<>(10);
        urlToTinyUrlMap = new HashMap<>(10);
        random = new Random();
    }

    //    https://example.com/search?q=dp+problems&sort=asc&page=2
//    https://tinyurl.com/xyz789
    public static void main(String[] args) {
        TinyURL tinyURL = new TinyURL();
        String url1 = "https://example.com/search?q=dp+problems&sort=asc&page=2";
        String tUrl = tinyURL.setTinyUrl(url1);
        System.out.println(tUrl);
        System.out.println(tinyURL.getTinyUrl(tUrl));

        tUrl = tinyURL.setTinyUrl(url1);
        System.out.println(tUrl);
        System.out.println(tinyURL.getTinyUrl(tUrl));

        url1 = "https://example.com/search?q=dp+problems&sort=asc&page=3";
        tUrl = tinyURL.setTinyUrl(url1);
        System.out.println(tUrl);
        System.out.println(tinyURL.getTinyUrl(tUrl));
    }

    public String setTinyUrl(String url){
        if(urlToTinyUrlMap.containsKey(url)){
            System.out.println("already exists:"+urlToTinyUrlMap.containsKey(url));
            return urlToTinyUrlMap.get(url);
        }
        String tinyUrl= generateTinyUrl(url);
        tinyUrlToUrlMap.put(tinyUrl,url);
        urlToTinyUrlMap.put(url,tinyUrl);
        return tinyUrl;
    }

    public String getTinyUrl(String tinyUrl){
        if(tinyUrlToUrlMap.containsKey(tinyUrl)) {
            return tinyUrlToUrlMap.get(tinyUrl);
        }else{
            throw new RuntimeException("Exception: no tiny url found");
        }
    }
    public String generateTinyUrl(String urlStr){
        try {
            URL url = new URL(urlStr);
            String host = "tinyurl.com";
            String newUrl = "";
            do {
                StringBuilder newPath=new StringBuilder("/");
                for (int i = 0; i < 6; i++) {
                    newPath.append(chars.charAt(random.nextInt(chars.length())));
                }
                URI uri = new URI(
                        url.getProtocol(),
                        host,
                        newPath.toString(),
                        null
                );
                return uri.toString();
            } while (tinyUrlToUrlMap.containsKey(newUrl));
        }catch (Exception e){
            System.out.println(e);
        }
        return "no";
    }
}
