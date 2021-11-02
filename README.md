# CodingTest

##  注意事项

> 最低支持api19

    minSdk 19
    targetSdk 31
    
> 开发环境

    AndroidStudio 2020.3.1
    Gradle 7.0.2
## 简介

- 整体项目采用mvvm模式

## 使用
1. 启用viewinding
在主工程app的build.gradle的android {}中加入：
```
 viewBinding {
     enabled = true
 }
```
2. 配置config.gradle,统一常用三方库版本
```gradle
apply from: "config.gradle"
```
netWorkDependencies = [] 是网络库配置，可自行修改
imageDependencies = [] 是图片库配置，可自行修改

3. 配置AndroidManifest
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
```
4.配置Application：
AppKtApplication 


