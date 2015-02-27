package threadDownLoad;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadService
{
    /**
     * 锁
     */
    private static Object block = new Object();
    
    private static int maxThread = 100;
    
    private int threadIndex = 0;
    
    /**
     * 分发多线程任务
     * //TODO 添加方法功能描述
     * @param list
     */
    public void batchQuery(List list)
    {
        if (list.size() > 0)
        {
            //创建线程池
            final ExecutorService executorService = Executors.newFixedThreadPool(10);
            while (list.size() > 0)
            {
                ThreadRunnable run = new ThreadRunnable();
                startQueryThread(run, executorService);
            }
            executorService.shutdown();
            try
            {
                //等待线程池所有线程执行完后，继续下一步操作
                while (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS))
                    ;
            }
            catch (InterruptedException e)
            {
                
            }
        }
    }
    /**
     * 判断是否可以启动线程，不能就等待
     * //TODO 添加方法功能描述
     * @param run
     * @param executorService
     */
    public void startQueryThread(ThreadRunnable run, ExecutorService executorService)
    {
        while (true)
        {
            synchronized (block)
            {
                if (threadIndex < maxThread)
                {
                    threadIndex++;
                    executorService.execute(run);
                    return;
                }
                else
                {
                    try
                    {
                        block.wait();
                    }
                    catch (InterruptedException e)
                    {
                        
                    }
                }
                
            }
        }
    }
    
    /**
     * 释放线程
     * //TODO 添加方法功能描述
     */
    public void releaseBatcheThread()
    {
        synchronized (block)
        {
            threadIndex--;
            block.notifyAll();
        }
    }
    
    class ThreadRunnable implements Runnable
    {
        
        public void run()
        {
            // TODO Auto-generated method stub
            
        }
        
    }
    
}
