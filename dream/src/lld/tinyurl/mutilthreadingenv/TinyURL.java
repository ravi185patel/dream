package lld.tinyurl.mutilthreadingenv;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class MultiThreadTinyURL {
    public Map<String,String> tinyUrlToUrlMap;
    public Map<String,String> urlToTinyUrlMap;
    Random random;

    public static String chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567899";

    public MultiThreadTinyURL() {
        tinyUrlToUrlMap = new HashMap<>(10);
        urlToTinyUrlMap = new HashMap<>(10);
        random = new Random();
    }

    //    https://example.com/search?q=dp+problems&sort=asc&page=2
//    https://tinyurl.com/xyz789
    public synchronized String setTinyUrl(String url){
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

public class TinyURL{
    public static void main(String[] args) {
        MultiThreadTinyURL tinyURL = new MultiThreadTinyURL();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String url1 = "https://example.com/search?q=dp+problems&sort=asc&page=2";
                String tUrl = tinyURL.setTinyUrl(url1);
                System.out.println(tUrl);
                System.out.println(tinyURL.getTinyUrl(tUrl));
            }
        };

        Runnable runnable1 =  new Runnable() {
            @Override
            public void run() {
                String url1 = "https://example.com/search?q=dp+problems&sort=asc&page=2";
                String tUrl = tinyURL.setTinyUrl(url1);
                System.out.println(tUrl);
                System.out.println(tinyURL.getTinyUrl(tUrl));
            }
        };

        Thread t = new Thread(runnable);
        Thread t1 = new Thread(runnable1);
        t.start();
        t1.start();
    }

}
