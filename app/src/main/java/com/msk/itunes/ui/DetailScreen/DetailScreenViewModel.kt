package com.msk.itunes.ui.DetailScreen

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.itunes.Data.MediaTypeDataClass
import com.msk.itunes.Repository.DetailScreenEvent
import com.msk.itunes.Repository.SearchRepository
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import com.msk.itunes.Util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val repository: SearchRepository):ViewModel() {

    private val _result: MutableStateFlow<Result?> = MutableStateFlow(null)
     val result:StateFlow<Result?> =_result




    fun OnEvent(event:DetailScreenEvent){
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