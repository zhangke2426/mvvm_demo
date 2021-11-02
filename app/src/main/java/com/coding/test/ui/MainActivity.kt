package com.coding.test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.coding.test.R
import com.coding.test.base.inflate
import com.coding.test.databinding.ActivityMainBinding
import com.coding.test.model.viewmodel.BlogViewModel
import com.coding.test.ui.adapter.BlogAdapter
import com.coding.test.ui.widget.StateEmptyView
import com.coding.test.ui.widget.StateErrorView
import com.coding.test.utils.Constant
import com.coding.test.utils.Navigator
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author zhangke
 * @Date   on 2021/11/1
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by inflate()
    private val blogViewModel by viewModels<BlogViewModel>()
    var blogAdapter = BlogAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        binding.apply {
            blogRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            blogRecyclerView.adapter = blogAdapter
            swipeLayout.isRefreshing = true
            swipeLayout.setOnRefreshListener { refresh() }
            refresh()
        }

        blogAdapter.setOnItemClickListener { adapter, view, position ->
            blogAdapter.getItem(position).frontmatter?.path?.let {
                Navigator.toBlogDetailActivity(this,Constant.BASE_URL+it)
            }
        }

        blogViewModel.blogLiveData.observe(this){
            if (it.isNullOrEmpty()){
                blogAdapter.setEmptyView(StateEmptyView(this))
            }else{
                blogAdapter.setList(it)
            }

        }

        blogViewModel.isLoading.observe(this) { isLoading ->
            binding.swipeLayout.isRefreshing = isLoading
        }

        blogViewModel.networkError.observe(this) { throwable: Throwable? ->
            blogAdapter.setEmptyView(StateErrorView(this){
                refresh()
            })
        }

    }

    private fun refresh() {
        blogViewModel.getPhotos()
    }
}