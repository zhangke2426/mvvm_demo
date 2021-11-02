package com.coding.test.ui

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.ProgressBar

import androidx.appcompat.app.AppCompatActivity

import com.coding.test.R
import com.coding.test.base.inflate

import com.coding.test.databinding.ActivityWebBinding
import com.coding.test.ui.BridgeWebChromeClient.Companion.myTag
import com.coding.test.utils.IntentExtraDef
import java.util.*

/**
 * @Author zhangke
 * @Date   on 2021/11/2
 */

class WebViewActivity : AppCompatActivity() {
    private val binding: ActivityWebBinding by inflate()
    lateinit var progressBar: ProgressBar
    lateinit var handler: Handler
    var mWebView: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        var url = intent.getStringExtra(IntentExtraDef.PATH)
        progressBar = binding.webViewProgressbar
        mWebView =  binding.webView
        url?.let {
            mWebView?.loadUrl(it)
        }

        handler = InnerHandler()
        binding.webView.webChromeClient = mBridgeWebChromeClient

    }

    override fun onBackPressed() {
        if (mWebView?.canGoBack() == true) {
            mWebView?.goBack()
        } else {
            super.onBackPressed()
        }
    }

    class InnerHandler : Handler() {

    }

    private var mBridgeWebChromeClient: BridgeWebChromeClient = object : BridgeWebChromeClient() {

        override fun onReceivedTitle(view: WebView, title: String) {
            Log.d(myTag,"title $title")
        }

        override fun onProgressChanged(view: WebView, newProgress: Int) {

            if (newProgress >= 100) {
                handler.removeCallbacks(progressTask)
                progressBar.visibility = View.GONE
            } else {
                if (newProgress > progressBar.progress) {
                    progressBar?.progress = newProgress
                }
                handler.removeCallbacks(progressTask)
                handler.postDelayed(progressTask, INCREASE_DURATION.toLong())
            }
            Log.d(myTag, "newProgress = $newProgress");
            super.onProgressChanged(view, newProgress)
        }
    }
    private var random: Random? = null
    private val progressTask = Runnable {
        progressTaskImpl()
    }

    private fun progressTaskImpl() {
        if (progressBar != null) {
            val progress = progressBar?.progress
            if (random == null) {
                random = Random()
            }
            var current = progress + 3 + random?.nextInt(10)!!
            if (current > FAKE_MAX_PROGRESS) {
                current = FAKE_MAX_PROGRESS
            } else {
                handler?.postDelayed(progressTask, INCREASE_DURATION.toLong())
            }
            progressBar?.progress = current
        }
    }

    companion object {

        private const val FAKE_MAX_PROGRESS = 95
        private const val INCREASE_DURATION = 500

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(myTag, "onDestroy")
        handler.removeCallbacks(progressTask)
    }
}