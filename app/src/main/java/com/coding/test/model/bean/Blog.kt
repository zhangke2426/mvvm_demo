package com.coding.test.model.bean

/**
 * @Author zhangke
 * @Date   on 2021/11/1
 */

data class Blog(
    val frontmatter: Frontmatter?
)

data class Frontmatter(
    val banner: Banner?,
    val categories: List<String>,
    val date: String,
    val language: String,
    val path: String,
    val tags: List<String>,
    val title: String
)

data class Banner(
    val childImageSharp: ChildImageSharp?
)

data class ChildImageSharp(
    val fixed: Fixed?
)

data class Fixed(
    val src: String?
)


