package com.gaurav.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class NewsVM(private val newsRepo: NewsRepo) : ViewModel() {
     fun getHeadlines()
            = liveData { emit(newsRepo.getHeadLines().value) }
}