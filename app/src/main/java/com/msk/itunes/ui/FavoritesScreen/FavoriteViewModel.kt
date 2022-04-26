package com.msk.itunes.ui.FavoritesScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.itunes.Data.Entity.SavedId
import com.msk.itunes.Data.Favoritedata
import com.msk.itunes.Repository.FavoritesRepository.FavoritesRepository
import com.msk.itunes.ui.FavoritesScreen.component.FavoriteEvent
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: FavoritesRepository):ViewModel() {

    private val _FavoriteData:MutableStateFlow<List<SavedId>> = MutableStateFlow(listOf())
    val FavoriteData:StateFlow<List<SavedId>> = _FavoriteData



    fun OnEvent( event: FavoriteEvent){
        when (event){
            is FavoriteEvent.LoadNewPage-> LoadPage()
        }

    }

    fun LoadPage(){
        viewModelScope.launch {
            repository.getFavoriteId().onEach {
                it.onSuccess {
                    Log.d("hatad",it.size.toString())

                    _FavoriteData.value=it
                }
            }.launchIn(this)
        }
    }


}