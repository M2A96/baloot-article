package com.example.ataei.ui.home.list

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import arrow.core.Either.Left
import arrow.core.Either.Right
import com.example.ataei.ui.base.BaseViewModel
import com.example.ataei.ui.custom.MagicalImageView
import com.example.data.model.Error
import com.example.data.model.game.Game
import com.example.data.repository.GameRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeListViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : BaseViewModel() {

    private val _games = MutableLiveData<List<GameItem>>(emptyList())
    val games: LiveData<List<GameItem>>
        get() = _games


    init {
        getGames()
    }


    private fun getGames() {
        viewModelScope.launch {
            when (val result = gameRepository.getGames()) {
                is Right -> _games.value = mapToGameItems(result.b)
                is Left -> showError(result.a)
            }
        }
    }

    private fun mapToGameItems(games: List<Game>): List<GameItem> {
        return games.map { game ->
            GameItem(
                game.name,
                game.preview,
                game.business.name
            )
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
