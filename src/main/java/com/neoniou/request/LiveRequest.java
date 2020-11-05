package com.neoniou.request;

import cn.hutool.http.HttpRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Neo.Zzj
 * @date 2020/11/5
 */
@Slf4j
public class LiveRequest {

    private static final String ROOM_INIT = "http://api.live.bilibili.com/room/v1/Room/room_init?id=";

    public static boolean isLive(String roomId) {
        String resBody = HttpRequest.get(ROOM_INIT + roomId).execute().body();
        System.out.println(resBody);

        return true;
    }
}
