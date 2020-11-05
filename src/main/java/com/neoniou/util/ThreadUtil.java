package com.neoniou.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Neo.Zzj
 * @date 2020/11/5
 */
@Slf4j
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

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            log.error("Thread sleep error: ", e);
        }
    }
}
