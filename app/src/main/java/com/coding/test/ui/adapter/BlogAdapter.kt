package com.coding.test.ui.adapter

import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.coding.test.R
import com.coding.test.model.bean.Blog
import com.coding.test.utils.Constant.BASE_URL

/**
 * @Author zhangke
 * @Date   on 2021/11/2
 */

class BlogAdapter : BaseQuickAdapter<Blog, BaseViewHolder>(R.layout.item_blog) {

    override fun convert(holder: BaseViewHolder, item: Blog) {
        item.frontmatter?.let {data->
            holder.setText(R.id.title,data.title)
            holder.setText(R.id.date,data.date)
            data.banner?.childImageSharp?.fixed?.src.let {src->
                holder.getView<ImageView>(R.id.banner).load(BASE_URL+src)
            }

        }

    }

}