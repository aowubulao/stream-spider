package com.neoniou;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import com.neoniou.request.DownloadRequest;
import com.neoniou.request.LiveRequest;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Neo.Zzj
 * @date 2020/11/5
 */
public class RequestTest {

    @Test
    public void test1() {
        //System.out.println(System.getProperty("user.dir"));
        DownloadRequest downloadRequest = new DownloadRequest();
        downloadRequest.download("123", "273192874");

    }
}
