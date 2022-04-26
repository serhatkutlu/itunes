package com.msk.itunes.ui.DetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.itunes.Repository.DetailRepository.DetailRepository
import com.msk.itunes.Repository.SearchRepository.SearchRepository
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val repository: DetailRepository):ViewModel() {

    private val _result: MutableStateFlow<Result?> = MutableStateFlow(null)
     val result:StateFlow<Result?> =_result

    private val _idFavorite:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val idFavorite:StateFlow<Boolean> = _idFavorite


    fun OnEvent(event: DetailScreenEvent){
        when(event){
            is DetailScreenEvent.LoadDetail-> onsearch(event.id)
            is DetailScreenEvent.CheckFavorite-> CheckSavedId(event.id)
            is DetailScreenEvent.ClickFavorite-> ClickFavorite(event.id)
        }
    }

    private fun onsearch(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
             repository.SearchId(id).onEach {
                 it.onSuccess {
                     _result.emit(it.results.firstOrNull())
                 }
             }.launchIn(this)
        }
    }

    private fun CheckSavedId(id:Int){
        viewModelScope.launch {
            repository.Checkid(id).onEach{
                _idFavorite.value = it
            }.launchIn(this)

        }
    }
    private fun ClickFavorite(id:Int){
        viewModelScope.launch {
            if (idFavorite.value){
                repository.deleteFavoriteID(id)
                _idFavorite.value=false
            }
            else{
                repository.saveid(id)
                _idFavorite.value=true
            }
        }

    }
}