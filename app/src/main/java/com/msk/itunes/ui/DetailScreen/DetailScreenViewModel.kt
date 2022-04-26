package com.msk.itunes.ui.DetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.itunes.Repository.DetailRepository.DetailRepository
import com.msk.itunes.Repository.SearchRepository.SearchRepository
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val repository: DetailRepository):ViewModel() {

    private val _result: MutableStateFlow<Result?> = MutableStateFlow(null)
     val result:StateFlow<Result?> =_result




    fun OnEvent(event: DetailScreenEvent){
        when(event){
            is DetailScreenEvent.LoadDetail-> onsearch(event.id)
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
}