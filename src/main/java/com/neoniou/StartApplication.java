package com.neoniou;

import com.neoniou.pojo.RoomInfo;
import com.neoniou.util.ConfigUtil;
import com.neoniou.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author Neo.Zzj
 * @date 2020/11/5
 */
@Slf4j
public class StartApplication {

    public static void main(String[] args) {
        // Initialization
        List<RoomInfo> roomInfos = ConfigUtil.readConfig();
        ExecutorService threadPool = ThreadUtil.createThreadPool(roomInfos.size());

        // Start thread
        for (int i = 0; i < roomInfos.size(); i++) {
            threadPool.execute(() -> {

            });
        }
    }
}
