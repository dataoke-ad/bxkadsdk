## 一、项目集成（以Androidstudio为例）
1、在project的build.gradle中添加  maven{url'https://jitpack.io'}
   ```groovy
allprojects {
	repositories {
		google()
		jcenter()
		maven{url'https://jitpack.io'}
	}
}
   ```
2、在app的build.gradle中添加依赖
 ```groovy
dependencies {
   implementation'com.github.dataoke-ad:bxkadsdk:v1.0.0'
}
   ```
   二、权限设置
   变现客SDK建议您添加下述权限，并建议在您的隐私协议中向开发者声明变现客SDK会获取下述权限并应用于组件使用。
 ```groovy
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
   ```
   三、SDK初始化设置
   开发者需要在Application的onCreate()方法中调用以下代码来初始化变现客sdk。
 ```groovy
Bxk.init("your key","your appSecret");
   ```
   四、通用接口定义
   组件调用setListener方法，传入接口实例，loadSuccess()为加载成功时回调，loadFail(String code, String msg)为加载失败时回调，msg为错误原因，其中code为错误码，详细错误码信息见SDK错误码表
   五、开屏广告组件
   1、介绍：该组件分为广告信息和商品信息，开发者不用区分类型，可直接布局使用。
   2、 使用：
   ①在页面的xml中布局OpenScreenView，宽高默认为match_parent，示例如下：
 ```groovy
<com.dataoke.bxkadsdklib.ui.openscreen.OpenScreenView
    android:id="@+id/open_screen_view"
    android:layout_width="match_parent"
    android:layout_height="parent"/>
   ```
   ②调用initViewData(this,”你的控件id”)
   ```java
 open_screen_view.initViewData(this,"你的控件id");
   ```
   3、控件监听：
   ```java
   open_screen_view.setListener(new IOpenScreenViewListener() {
    @Override
    public void click(String url){}

    @Override
    public void loadSuccess() {}

    @Override
    public void loadFail(String code, String msg) {}
});
   ```
   其中click(String url)为用户点击广告时的回调，url为网页链接，请开发者自行实现webview加载。（注意：url可能为空，请开发者自行判断）
   六、信息流组件
   1、介绍：该组件分为广告信息和商品列表信息，广告信息为一张图片的广告信息，商品列表为横向recyclerview列表，开发者不用区分类型。
   2、使用：
①在页面的xml中布局InfoFlowView，宽默认为match_parent，高度默认为wrap_content，示例如下：
```java
<com.dataoke.bxkadsdklib.ui.infoflow.InfoFlowView
    android:id="@+id/info_flow_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
````
 ②调用initViewData(this,”你的控件id”)
   ```java
info_flow_view.initViewData(this,"你的控件id");
   ```
 3、控件监听：
   ```java
 info_flow_view.setListener(new InfoFlowViewListener() {
    @Override
    public void clickAd(String url) {}

    @Override
    public void clickGoodsItem(int position, String url) {}

    @Override
    public void loadSuccess() {}

    @Override
    public void loadFail(String code, String msg) {}
});
   ```
①其中clickAd(String url)为点击广告时回调，url为网页链接，可空；
②clickGoodsItem(int position, String url)为点击商品列表item时回调，url为网页链接，可空，position为点击的位置。

七、优惠券搜索器组件
1、使用：
①在页面的xml中布局SearchView，宽度默认为match_parent，高度默认为wrap_content
```java
<com.dataoke.bxkadsdklib.ui.infoflow.SearchView
    android:id="@+id/search_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
````
②调用initViewData(this,”你的控件id”)
   ```java
search_view.initViewData(this,"你的控件id");
   ```
 3、控件监听：
   ```java
search_view.setListener(new ISearchViewListener() {
    @Override
    public void searchSuccess(String url) {}

    @Override
    public void searchEmpty() {}

    @Override
    public void loadSuccess() {}

    @Override
    public void loadFail(String code, String msg) {}
});
   ```
   search(String url)为用户点击搜索按钮后回调，url为搜索成功后的网页链接地址。searchEmpty()为未输入内容时点击按钮的回调，开发者自行实现为空时的逻辑
   
八、弹窗组件
1、使用：
①在页面的xml中布局MyDialogView，宽高默认为match_parent
```java
<com.dataoke.bxkadsdklib.ui.infoflow.MyDialogView
    android:id="@+id/my_dialog_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
````
 ②调用initViewData(this,”你的控件id”)
   ```java
my_dialog_view.initViewData(this,"你的控件id");
   ```
 3、控件监听：
   ```java
my_dialog_view.setListener(new IMyDialogListener() {
    @Override
    public void clickImg(String link) {}

    @Override
    public void clickCloseDialog() {}

    @Override
    public void loadSuccess() {}

    @Override
    public void loadFail(String code, String msg) {}
});
   ```
①clickImg(String link)为用户点击弹窗图片时回调，link可为空
②clickCloseDialog()为用户点击关闭弹窗时回调，开发者可在此处理弹窗关闭的后续操作。

九、浮窗组
1、使用：
①在页面的xml中布局FloatView，宽高建议为90dp,120dp
```java
<com.dataoke.bxkadsdklib.ui.infoflow.FloatView
    android:id="@+id/float_view"
    android:layout_width="90dp"
    android:layout_height="120dp"/>
````
 ②调用initViewData(this,”你的控件id”)
   ```java
float_view.initViewData(this,"你的控件id");
   ```
 3、控件监听：
   ```java
float_view.setListener(new IFloatViewListener() {
    @Override
    public void clickImg(String link) {}

    @Override
    public void closeFloatView() {}

    @Override
    public void loadSuccess() {}

    @Override
    public void loadFail(String code, String msg) {}
});
   ```
①clickImg(String link)为点击浮窗图片时回调，link为网页链接，可空
②closeFloatView()为关闭浮窗时回调，开发者可在此处理浮窗关闭的后续操作。

十、错误码
   ###注意：错误码分为SDK错误码和接口错误码，其中SDK错误码为loadFail(String code,String msg)回调中的code值，接口错误码为msg中一并返回。
| 错误码 | 说明 | 对应的接口错误码 | 如何排查
| ------ | ------ | ------ | ------ |
| -1 | 接口访问失败 | 88004001 | 请带上认证信息|
| -1 | 接口访问失败 | 88004002 | appkey不能为空|
| -1 | 接口访问失败 | 88004003 | 客户端信息不能为空|
| -1 | 接口访问失败 | 88004004 | appkey信息错误|
| -1 | 接口访问失败 | 88004005 | sign不能为空|
| -1 | 接口访问失败 | 88004006 | 签名错误|
| -1 | 接口访问失败 | 88004007 | 认证失败|
| -2 | 接口数据为空 | 无 | 请检查配置|
| -3 | 数据适配异常 | 无 | 请联系客服|