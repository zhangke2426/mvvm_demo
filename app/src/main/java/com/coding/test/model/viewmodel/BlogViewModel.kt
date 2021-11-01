package com.coding.test.model.viewmodel

import androidx.lifecycle.MutableLiveData
import com.coding.test.model.bean.Blog
import com.coding.test.model.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author zhangke
 * @Date   on 2021/11/1
 */

@HiltViewModel
class BlogViewModel @Inject constructor(private val repository: BlogRepository) :
    BaseViewModel() {

    private val blogLiveData = MutableLiveData<List<Blog>>()

    fun getPhotos() {
        launch({
            blogLiveData.value =  repository.getBlogList()
        }, {
            it.printStackTrace()
        }, {

        })
    }

}