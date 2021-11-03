# CodingTest

##  注意事项

> 最低支持api19

    minSdk 19
    targetSdk 31
    
> 开发环境

    AndroidStudio 2020.3.1
    Gradle 7.0.2
## 简介
整体项目采用mvvm模式, 采用的开源库包括：
* Retrofit
* OKHttp
* coil
* hilt
* BaseRecyclerViewAdapterHelper

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

## 3、三方库选取
>图片使用Coil 与Glide对比

***实现语言***

  Glide 全盘使用 Java 语言来实现，对于 Java 和 Kotlin 语言的友好程度差不多
  
  Coil 全盘使用 Kotlin 语言来实现，为 ImageView 声明了多个用于加载图片的扩展函数，对 Kotlin 语言的友好程度会更高很多
  
***网络请求***
  Glide 默认是使用 HttpURLConnection，但也提供了更换网络请求实现途径的入口
  
  Coil 默认是使用 OkHttp，但也提供了更换网络请求实现途径的入口
 
***生命周期监听*** 
  Glide 通过向 Activity 或者 Fragment 注入一个无 UI 界面的 Fragment 来实现监听
  
  Coil 直接通过 Lifecycle 来实现监听
  
***内存缓存***   
  Glide 的内存缓存分为 ActiveResources 和 MemoryCache 两级
  
  Coil 的内存缓存分为 WeakMemoryCache 和 StrongMemoryCache 两级，本质上和 Glide 一样
  
***磁盘缓存***  
  Glide 在加载到图片后通过 DiskLruCache 来进行磁盘缓存，且提供了是否缓存、是否缓存原始图片、是否缓存转换过后的图片等多个选择
  
  Coil 通过 OkHttp 的网络请求缓存机制来实现磁盘缓存，且磁盘缓存只对通过网络请求加载到的原始图片生效，不缓存其它来源的图片和转换过后的图片
  
***线程框架***    
  Glide 使用原生的 ThreadPoolExecutor 来完成后台任务，通过 Handler 来实现线程切换
  
  Coil 使用 Coroutines 来完成后台任务及线程切换



