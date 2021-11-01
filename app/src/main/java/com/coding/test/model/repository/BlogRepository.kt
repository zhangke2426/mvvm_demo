package com.coding.test.model.repository

import com.coding.test.model.remote.BlogService
import javax.inject.Inject

/**
 * @Author zhangke
 * @Date   on 2021/11/1
 */

class BlogRepository@Inject constructor( private val service: BlogService) {

    suspend fun getBlogList() = service.getBlogList()

}