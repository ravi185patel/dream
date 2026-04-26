package lld.tinyurl.multipleinstance;

public class TinyUrlController {

    private LoadBalancer loadBalancer;

    public TinyUrlController(LoadBalancer loadBalancer){
        this.loadBalancer = loadBalancer;
    }

    public String setTinyUrl(String origin,String url){
        TinyUrlService tinyUrlService = loadBalancer.getService(origin);
        if(tinyUrlService.isUrlExists(url)){
            return tinyUrlService.getTinyUrl(url);
        }
        return tinyUrlService.setTinyUrl(url);
    }

    public String getUrl(String origin,String url){
        TinyUrlService tinyUrlService = loadBalancer.getService(origin);
        return tinyUrlService.getUrl(url);
    }

    public String getTinyUrl(String origin,String tinyUrl){
        TinyUrlService tinyUrlService = loadBalancer.getService(origin);
        return tinyUrlService.getTinyUrl(tinyUrl);
    }
}
