package lld.tinyurl.multipleinstance;

import lld.tinyurl.simple.TinyURL;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

public class TinyUrlGenerator {
    Random random;
    public static String chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567899";
    public static String host = "tinyurl.com";

    public TinyUrlGenerator() {
        random = new Random();
    }

    public String generateTinyUrl(String urlStr){
        try {
            URL url = new URL(urlStr);
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
        }catch (Exception e){
            System.out.println(e);
        }
        return "no";
    }
}
