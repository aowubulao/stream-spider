package com.neoniou.util;

import java.util.concurrent.*;

/**
 * @author Neo.Zzj
 * @date 2020/11/5
 */
public class ThreadUtil {

    public static ExecutorService createThreadPool(int coreSize) {
        return new ThreadPoolExecutor(coreSize,
                coreSize,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
