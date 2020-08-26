package com.gaurav.newsapp.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.gaurav.khalilabadapp.core.ui.BaseActivity
import com.gaurav.newsapp.R
import com.gaurav.newsapp.networks.Article
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {
   private val article by lazy {
        intent?.getParcelableExtra<Article>(MainActivity.ARTICLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initViews()
    }

    private fun initViews() {
        article?.let {
            with(it) {
                urlToImage?.let { Glide.with(this@DetailActivity).load(it).into(ivArticleImage) }
                title?.let { tvHeadline.text = it }
                content?.let { tvContent.text = it}
            }
        }
    }
}