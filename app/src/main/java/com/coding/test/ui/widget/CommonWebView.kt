package com.coding.test.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView

import android.webkit.WebSettings
import android.webkit.WebViewClient


/**
 * @Author zhangke
 * @Date   on 2021/11/2
 */

class CommonWebView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr){
    private var mClient: InnerWebViewClient? = null

    init {
        mClient = InnerWebViewClient()
        webViewClient = mClient!!
        val webSettings = settings
        webSettings.setSupportZoom(true)
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.domStorageEnabled = true
        webSettings.loadsImagesAutomatically = true
        webSettings.builtInZoomControls = false
        webSettings.useWideViewPort = true //自动适配大小
        webSettings.loadWithOverviewMode = true
        webSettings.setAppCacheEnabled(true)
        webSettings.allowFileAccess = true
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        val appCacheDir = this.context.applicationContext.getDir("cache", Context.MODE_PRIVATE).path
        webSettings.setAppCachePath(appCacheDir)
    }

    private inner class InnerWebViewClient : WebViewClient(){
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            url?.let {
                Log.d("UrlLoading", it)
            }
            return super.shouldOverrideUrlLoading(view, request)
        }
    }
    companion object {
        private val TAG = CommonWebView::class.java.simpleName
    }
}