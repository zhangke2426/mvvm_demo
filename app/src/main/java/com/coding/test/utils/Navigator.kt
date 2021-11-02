package com.coding.test.utils

import android.content.Context
import android.content.Intent
import com.coding.test.ui.WebViewActivity

/**
 * @Author zhangke
 * @Date   on 2021/11/2
 */

object Navigator {

    fun toBlogDetailActivity(context: Context,path: String) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(IntentExtraDef.PATH,path)
        context.startActivity(intent)
    }

}