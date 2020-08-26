package com.gaurav.newsapp.core.di

import co.zoozle.android.zoozle.data.networks.OkHttpBuilder
import com.gaurav.newsapp.networks.ApiInterface
import com.gaurav.newsapp.networks.MoshiBuilder
import com.gaurav.newsapp.ui.NewsRepo
import com.gaurav.newsapp.ui.NewsVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModules = module {
    single { ApiInterface.create(get(), get()) }
    single { MoshiBuilder.create() }
    single { OkHttpBuilder.create() }
}

val vmModules = module {
    viewModel { NewsVM(get()) }
}
val repoModules = module {
    single { NewsRepo(get()) }
}

