package com.example.ataei.ui.home.articles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import arrow.core.Either.Left
import arrow.core.Either.Right
import com.example.ataei.ui.base.BaseViewModel
import com.example.data.model.Error
import com.example.data.repository.NewsRepository
import com.example.data.source.remote.model.ArticleDto
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
    private val gameRepository: NewsRepository
) : BaseViewModel() {

    private val _articles = MutableLiveData<List<ArticleDto>>(emptyList())
    val articles: LiveData<List<ArticleDto>>
        get() = _articles

    private var pageNumber = 1


    init {
        getArticles()
    }


    private fun getArticles() {
        viewModelScope.launch {
            when (val result = gameRepository.getArticles(pageNumber++)) {
                is Right -> _articles.value = result.b
                is Left -> showError(result.a)
            }
        }
    }

    private fun showError(error: Error) {
        Log.d(TAG, "showError() called  with: error = [$error]")
    }

    fun onItemClicked(gameItem: GameItem) {
        Log.d(TAG, "onItemClicked() called  with: gamesItem = $gameItem")
    }

    companion object {
        private const val TAG = "HomeListViewModel"
    }
}
