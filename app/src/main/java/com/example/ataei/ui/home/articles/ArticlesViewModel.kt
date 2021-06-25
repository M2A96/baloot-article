package com.example.ataei.ui.home.articles

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import arrow.core.Either.Left
import arrow.core.Either.Right
import com.example.ataei.ui.base.BaseViewModel
import com.example.ataei.ui.home.HomeNavigator
import com.example.data.model.Error
import com.example.data.repository.NewsRepository
import com.example.data.source.remote.model.ArticleDto
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
    private val articlesRepository: NewsRepository,
    private val homeNavigator: HomeNavigator

) : BaseViewModel() {

    private val _articles = MutableLiveData<List<ArticleItem>>(emptyList())
    val articles: LiveData<List<ArticleItem>>
        get() = _articles

    val loadingLiveData = MutableLiveData<Boolean>()


    private val _selectedArticle = MutableLiveData<ArticleItem>()
    val selectedArticle: LiveData<ArticleItem>
        get() = _selectedArticle

    lateinit var article: ArticleItem

    private var pageNumber = 1


    init {
        getArticles()
    }


    private fun getArticles(pageNumber: Int = 1) {
        viewModelScope.launch {
            loadingLiveData.value = true
            when (val result = articlesRepository.getArticles(pageNumber)) {
                is Right -> _articles.value = mapToArticleItem(result.b)
                is Left -> showError(result.a)
            }
            loadingLiveData.value = false
        }
    }


    fun loadMore() {
        getArticles(++pageNumber)
    }

    private fun showError(error: Error) {
        Log.d(TAG, "showError() called  with: error = [$error]")
    }

    fun onItemClicked(articleItem: ArticleItem) {
        _selectedArticle.value = articleItem
        fragmentAction {
            homeNavigator.navigateToArticleDetail(it)
        }
    }

    private fun mapToArticleItem(articleDtos: List<ArticleDto>): List<ArticleItem> {
        return articleDtos.map {
            ArticleItem(
                title = it.title,
                author = it.author,
                description = it.description,
                articleUrl = it.url,
                imageUrl = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content
            )
        }
    }

    companion object {
        private const val TAG = "HomeListViewModel"
    }
}
