package com.neoniou.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.neoniou.constant.LiveStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Neo.Zzj
 * @date 2020/11/5
 */
@Slf4j
public class LiveRequest {

    private static final String ROOM_INIT = "http://api.live.bilibili.com/room/v1/Room/room_init?id=";

    private static final String PLAY_URL = "http://api.live.bilibili.com/room/v1/Room/playUrl?platform=web&cid=";

    private static final String DATA = "data";

    private static final String LIVE_STATUS = "live_status";

    private static final String D_URL = "durl";

    private static final String URL = "url";

    private static final String CODE = "code";

    private static final String ZERO = "0";

    public int isLive(String roomId) {
        try {
            log.info("[{}]Scanning room.", roomId);
            String resBody = HttpRequest.get(ROOM_INIT + roomId)
                    .execute()
                    .body();

            JSONObject body = JSONUtil.parseObj(resBody);
            if (!ZERO.equals(body.get(CODE).toString())) {
                return LiveStatus.NOT_EXIST;
            }

            JSONObject data = JSONUtil.parseObj(body.get(DATA));
            return Integer.parseInt(data.get(LIVE_STATUS).toString());
        } catch (Exception e) {
            log.error("[{}]Scanning room error: ", roomId, e);
            return LiveStatus.NOT_ON_LIVE;
        }
    }

    public String getLiveUrl(String roomId) {
        try {
            String resBody = HttpRequest.get(PLAY_URL + roomId)
                    .execute()
                    .body();
            JSONArray urls = JSONUtil.parseArray(JSONUtil.parseObj(JSONUtil.parseObj(resBody).get(DATA)).get(D_URL));

            return JSONUtil.parseObj(urls.get(0)).get(URL).toString();
        } catch (Exception e) {
            log.error("[{}]Get live url error: ", roomId, e);
            return null;
        }
    }
}
