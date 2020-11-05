package com.neoniou.request;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * @author Neo.Zzj
 * @date 2020/11/5
 */
@Slf4j
public class DownloadRequest {

    private String fileName;

    private static final String FILE_LOCATION = System.getProperty("user.dir");

    private void generateFileName  (String roomId) {
        StringBuilder sb = new StringBuilder();
        fileName = sb.append(FILE_LOCATION)
                .append(System.getProperty("file.separator"))
                .append(roomId)
                .append("-")
                .append(DateUtil.format(new Date(), "yyyyMMddHHmmss"))
                .append(".flv")
                .toString();
    }

    public void download(String liveUrl, String roomId) {
        generateFileName(roomId);

        InputStream is = null;
        FileOutputStream fos = null;
        try {
            new File(fileName);

            URL url = new URL(liveUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Connection", "Keep-Alive");

            is = connection.getInputStream();
            fos = new FileOutputStream(fileName);

            log.info("[{}]Room start to download", roomId);

            int byteRead;
            byte[] buffer = new byte[1204];
            while ((byteRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteRead);
            }
        } catch (Exception e) {
            log.error("[{}]Download error: ", roomId, e);
        } finally {
            try {
                assert fos != null;
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("[{}]Room ends", roomId);
        }
    }
}