package com.coding.test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.coding.test.R
import com.coding.test.model.viewmodel.BlogViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author zhangke
 * @Date   on 2021/11/1
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val blogViewModel by viewModels<BlogViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        blogViewModel.getPhotos()
    }

}