package com.neoniou.util;

import com.neoniou.pojo.RoomInfo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author Neo.Zzj
 * @date 2020/11/5
 */
@Slf4j
@Getter
public class ConfigUtil {

    private static final String ROOM_ID = "roomId";

    private static final String SPLIT_SYMBOL = ";";

    private static final String ROOM_PREFIX = "https://live.bilibili.com/";

    public static int scanInternalTime;

    /**
     * Read config.properties
     *
     * @return List<RoomInfo>
     */
    public static List<RoomInfo> readConfig() {
        List<RoomInfo> roomInfos = new ArrayList<>();

        Properties props = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");

        try {
            props.load(is);

            try {
                scanInternalTime = Integer.parseInt(props.getProperty("scanInternalTime"));
                scanInternalTime = Math.max(scanInternalTime, 15);
            } catch (Exception e) {
                scanInternalTime = 15;
            }

            for (String id : props.getProperty(ROOM_ID).split(SPLIT_SYMBOL)) {
                RoomInfo roomInfo = new RoomInfo();
                roomInfo.setRoomId(id);
                roomInfo.setLiveUrl(ROOM_PREFIX + id);
                roomInfos.add(roomInfo);
            }
        } catch (Exception e) {
            log.error("Fail to read config.properties. Error info: ", e);
        }

        return roomInfos;
    }
}
