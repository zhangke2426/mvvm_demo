package com.coding.test.model.remote
import com.coding.test.model.bean.Blog
import retrofit2.http.GET

/**
 * @Author zhangke
 * @Date   on 2021/11/1
 */
interface BlogService {

    @GET("/blog/posts.json")
    suspend fun getBlogList(): List<Blog>

}