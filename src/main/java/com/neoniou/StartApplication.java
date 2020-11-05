package com.neoniou;

import com.neoniou.constant.LiveStatus;
import com.neoniou.pojo.RoomInfo;
import com.neoniou.request.DownloadRequest;
import com.neoniou.request.LiveRequest;
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
        for (RoomInfo roomInfo : roomInfos) {
            threadPool.execute(() -> {
                LiveRequest liveRequest = new LiveRequest();
                String roomId = roomInfo.getRoomId();

                while (true) {
                    int status = liveRequest.isLive(roomId);
                    if (status == LiveStatus.NOT_EXIST) {
                        log.error("[{}]Live room is not exist.", roomId);
                        return;
                    } else if (status == LiveStatus.NOT_ON_LIVE) {
                        ThreadUtil.sleep(ConfigUtil.scanInternalTime);
                    } else {
                        log.info("[{}]Room is on live.", roomId);
                        String liveUrl = liveRequest.getLiveUrl(roomId);
                        DownloadRequest dr = new DownloadRequest();
                        dr.download(liveUrl, roomId);
                        return;
                    }
                }
            });
        }
    }
}