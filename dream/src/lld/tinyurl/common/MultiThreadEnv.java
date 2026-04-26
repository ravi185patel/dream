package lld.tinyurl.common;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MultiThreadEnv {
    private ExecutorService executorService= Executors.newSingleThreadExecutor();
    public void run(List<Runnable> task){
        for(Runnable runnable:task){
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
}
