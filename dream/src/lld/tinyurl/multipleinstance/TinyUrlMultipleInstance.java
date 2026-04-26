package lld.tinyurl.multipleinstance;

import lld.tinyurl.common.MultiThreadEnv;

import java.util.ArrayList;
import java.util.List;

public class TinyUrlMultipleInstance {
    public static void main(String[] args) {
        RedisInstance redisInstanceInd=new RedisInstance();
        RedisInstance redisInstanceUsa=new RedisInstance();
        RedisInstance redisInstanceUk=new RedisInstance();
        RedisInstance redisInstance=new RedisInstance();

        TinyUrlGenerator tinyUrlGenerator = new TinyUrlGenerator();

        TinyUrlService tinyUrlServiceInd=new TinyUrlService(redisInstance,tinyUrlGenerator);
        TinyUrlService tinyUrlServiceUsa=new TinyUrlService(redisInstance,tinyUrlGenerator);
        TinyUrlService tinyUrlServiceUk=new TinyUrlService(redisInstance,tinyUrlGenerator);

        LoadBalancer loadBalancer = new LoadBalancer();
        loadBalancer.registerTinyUrlService("IND",tinyUrlServiceInd);
        loadBalancer.registerTinyUrlService("USA",tinyUrlServiceUsa);
        loadBalancer.registerTinyUrlService("UK",tinyUrlServiceUk);


        TinyUrlController tinyUrlController = new TinyUrlController(loadBalancer);
//        String tinyUrlInd=tinyUrlController.setTinyUrl("IND","https://example.com/search?q=dp+problems&sort=asc&page=2");
//        String tinyUrlUsa=tinyUrlController.setTinyUrl("USA","https://example.com/search?q=dp+problems&sort=asc&page=2");
//        String tinyUrlUk=tinyUrlController.setTinyUrl("UK","https://example.com/search?q=dp+problems&sort=asc&page=2");
//
//        String url = tinyUrlController.getUrl("IND",tinyUrlInd);
//        System.out.println(tinyUrlInd+" = "+url);
//        url = tinyUrlController.getUrl("USA",tinyUrlUsa);
//        System.out.println(tinyUrlUsa+" = "+url);
//        url = tinyUrlController.getUrl("UK",tinyUrlUk);
//        System.out.println(tinyUrlUk+" = "+url);


        //Multiple instance logic

        MultiThreadEnv multiThreadEnv =new MultiThreadEnv();
        List<Runnable> runnableList= new ArrayList<>();
        Runnable runnableInd = ()->{
            String tUrl=tinyUrlController.setTinyUrl("IND","https://example.com/search?q=dp+problems&sort=asc&page=2");
            String oUrl = tinyUrlController.getUrl("IND",tUrl);
            System.out.println("Origin :IND => "+tUrl+" = "+oUrl);
        };

        Runnable runnableUSA = ()->{
            String tUrl=tinyUrlController.setTinyUrl("USA","https://example.com/search?q=dp+problems&sort=asc&page=2");
            String oUrl = tinyUrlController.getUrl("USA",tUrl);
            System.out.println("Origin :USA => "+tUrl+" = "+oUrl);
        };

        Runnable runnableUK = ()->{
            String tUrl=tinyUrlController.setTinyUrl("UK","https://example.com/search?q=dp+problems&sort=asc&page=2");
            String oUrl = tinyUrlController.getUrl("UK",tUrl);
            System.out.println("Origin :UK => "+tUrl+" = "+oUrl);
        };

        Thread threadInd=new Thread(runnableInd);
        Thread threadUSA=new Thread(runnableUSA);
        Thread threadUK=new Thread(runnableUK);

        threadInd.start();
        threadUSA.start();
        threadUK.start();

//        runnableList.add(runnableUK);
//        runnableList.add(runnableInd);
//        runnableList.add(runnableUSA);
//        multiThreadEnv.run(runnableList);
    }

}
