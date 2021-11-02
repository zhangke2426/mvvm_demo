package com.coding.test.ui.widget

import android.content.Context
import android.widget.FrameLayout
import androidx.core.widget.NestedScrollView
import com.coding.test.R

/**
 * @Author zhangke
 * @Date   on 2021/11/2
 */

class StateEmptyView(context: Context) :  FrameLayout(context) {

   init {
       inflate(context, R.layout.empty_view, this)
   }
}