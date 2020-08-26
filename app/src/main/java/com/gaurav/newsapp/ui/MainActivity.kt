package com.gaurav.newsapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.zoozle.android.zoozle.custom.ui.SimpleAdapter
import com.bumptech.glide.Glide
import com.gaurav.newsapp.R
import com.gaurav.newsapp.core.data.Resource
import com.gaurav.newsapp.networks.Article
import com.gaurav.newsapp.utils.extensions.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rv_article_item.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val newsVM by viewModel<NewsVM>()
    val simpleAdapter by lazy {
        SimpleAdapter(ArrayList<Article>(),
            { R.layout.rv_article_item },
            { view -> ArticleVH(view) },
            { holder, item ->
                holder.bind(item)
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {

        with(rvHeadLines) {
            adapter = simpleAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }


        newsVM.getHeadlines().observe(this, androidx.lifecycle.Observer {
            when (it) {
                is Resource.Success -> {
                    Timber.d("initViews :${it?.data} ")
                    it.data?.articles?.let {
                        simpleAdapter.updateList(ArrayList(it))
                    }
                }
                is Resource.Error -> {
                    Timber.d("initViews :${it.errorBody} ")
                }
            }
        })
    }

    inner class ArticleVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Article) {
            with(itemView)
            {
                Glide.with(this@MainActivity).load(item.urlToImage).into(ivArticleImage)
                tvHeadline.text = item.title
                setOnClickListener {
                    startActivity<DetailActivity>(ARTICLE to item)
                }

            }
        }
    }


    companion object {
        const val ARTICLE = "article"
    }


}