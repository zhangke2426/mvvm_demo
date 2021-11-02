package com.coding.test.ui.widget

import android.content.Context
import android.widget.FrameLayout
import androidx.core.widget.NestedScrollView
import com.coding.test.R

/**
 * @Author zhangke
 * @Date   on 2021/11/2
 */

class StateErrorView(context: Context,onclick: (() -> Unit)) : FrameLayout(context) {

   init {

       inflate(context, R.layout.error_view, this)
       setOnClickListener {
           onclick.invoke()
       }
   }
}