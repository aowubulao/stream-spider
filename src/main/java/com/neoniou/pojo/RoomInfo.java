package com.neoniou.pojo;

import lombok.Data;

/**
 * @author Neo.Zzj
 * @date 2020/11/5
 */
@Data
public class RoomInfo {

    private String roomId;

    private String liveUrl;

    private String streamUrl;

    private Boolean isLive;

}
