# Bilibili 录播机

一个简易的录播机，因为网上大多数录播机都是带图形界面的，我需要一个跨平台的命令行的录播机，所以就自己写了一个。

目前正在施工阶段。

## 功能

- [x] 多房间录制
- [x] 自动检测上线，自动录制
- [ ] ...

## 使用

环境：JDK8或以上版本

#### 下载

[链接](https://github.com/aowubulao/stream-spider/releases)

#### 配置

在resources 目录下config.properties

```properties
#房间id，;分割以及结尾
roomId=1234567;9876543;
# 多少秒检测一次，最低15
scanInternalTime=20
```

#### **启动**

Windows下启动start.bat

Linux下启动start.sh，Linux下在nohup.txt中读取日志

#### 文件

视频文件将下载在程序打开的目录下，目前仅保存flv格式，如有需要请使用FFmpeg自行转码。