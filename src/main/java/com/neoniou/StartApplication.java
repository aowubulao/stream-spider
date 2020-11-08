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
        log.info("Program start.");

        // Initialization
        List<RoomInfo> roomInfos = ConfigUtil.readConfig();
        ExecutorService threadPool = ThreadUtil.createThreadPool(roomInfos.size());
        log.info("Load config successfully.");

        // Start thread
        for (RoomInfo roomInfo : roomInfos) {
            threadPool.execute(() -> {
                LiveRequest liveRequest = new LiveRequest();
                String roomId = roomInfo.getRoomId();

                log.info("[{}]Start monitoring.", roomId);

                while (true) {
                    // Judge the room is live or not
                    int status = liveRequest.isLive(roomId);

                    if (status == LiveStatus.NOT_EXIST) {
                        log.error("[{}]Live room is not exist.", roomId);
                        return;
                    } else if (status == LiveStatus.ON_LIVE) {
                        log.info("[{}]Room is on live.", roomId);

                        // Get the live url to download
                        String liveUrl = liveRequest.getLiveUrl(roomId);
                        // Get url error, retry
                        if (liveUrl == null) {
                            continue;
                        }

                        DownloadRequest dr = new DownloadRequest();
                        // Download error, retry
                        if (!dr.download(liveUrl, roomId)) {
                            continue;
                        }
                    }

                    ThreadUtil.sleep(ConfigUtil.scanInternalTime);
                }
            });
        }
    }
}