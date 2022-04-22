package com.msk.itunes.ui.SearchScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.itunes.Data.MediaTypeDataClass
import com.msk.itunes.Repository.SearchRepository
import com.msk.itunes.Util.Constants
import com.msk.itunes.ui.SearchScreen.component.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor ( private val repository: SearchRepository):ViewModel() {


    private val _MediaData: MutableStateFlow<List<MediaTypeDataClass>> = MutableStateFlow(mutableListOf())
    val MediaData: MutableStateFlow<List<MediaTypeDataClass>> =_MediaData

    private val _SearchStates:MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val SearchState:StateFlow<SearchState> =_SearchStates




    var searchquery=""
        private set
    fun OnEvent(SearchEvent: SearchEvent) {
        when (SearchEvent) {
            is SearchEvent.Searchquery->{
                onsearch(SearchEvent.query)
            }

        }
    }



    private fun onsearch(query: String) {
        _SearchStates.value=SearchState.value.copy(isLoading = true)
        searchquery=query
        _MediaData.value= listOf()
        viewModelScope.launch(Dispatchers.IO) {
            Constants.MediaType.forEach {type->
                async {
                    repository.Search(query = query, offset = 0, type = type, limit = 10).onEach { Results ->

                        _SearchStates.value = SearchState.value.copy(isLoading = false)
                        Results.onSuccess {
                            _MediaData.value=MediaData.value+ listOf(MediaTypeDataClass(type,it.results))

                        }
                    }.launchIn(this)
                }.await()

            }
        }
    }
}

