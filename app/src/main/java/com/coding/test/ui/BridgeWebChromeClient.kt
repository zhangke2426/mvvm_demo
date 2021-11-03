package com.coding.test.ui

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView


open class BridgeWebChromeClient : WebChromeClient() {

    override fun onConsoleMessage(
        consoleMessage: ConsoleMessage): Boolean {
        Log.d(myTag, "consoleMessage = "
            + consoleMessage.message()
            + ", "
            + consoleMessage.sourceId()
            + "; "
            + consoleMessage.lineNumber()
            + "; "
            + consoleMessage.messageLevel())
        return true
    }

    companion object {
        val myTag = BridgeWebChromeClient::class.java.simpleName
    }
}
