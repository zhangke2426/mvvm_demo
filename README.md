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

## 1、准备工作
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

## 2、快速上手
>以MainActivity为例

1. ActivityMainBinding类是viewbinding框架自定生成的,activity_main.xml
保存activity_main.xml后viewbinding会生成一个ActivityMainBinding类。（如果没有生成，试着点击Build->Clean Project）

2. 通过by viewModels 创建ViewModel,添加依赖：
```gradle
implementation "androidx.activity:activity-ktx:1.1.0"

```
3. 创建ViewModel
```java
private val blogViewModel by viewModels<BlogViewModel>()
```
4. 继承BaseViewModel，BaseViewModel通过LiveData来处理常用UI刷新
```java
class BlogViewModel :BaseViewModel() {}
```

5. 数据绑定
在BlogViewModel中定义
```java
val blogLiveData = MutableLiveData<List<Blog>>()
```
在MainActivity中
```java
 blogViewModel.blogLiveData.observe(this){}
```
通过ViewModel中liveData.setValue完成数据刷新



