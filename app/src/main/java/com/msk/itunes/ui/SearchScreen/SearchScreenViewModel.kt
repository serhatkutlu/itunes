package com.msk.itunes.ui.SearchScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.itunes.Repository.SearchRepository
import com.msk.itunes.Responce.Data.WrapperTypeData
import com.msk.itunes.ui.SearchScreen.component.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor ( private val repository: SearchRepository):ViewModel() {


    private val _WrapperData: MutableStateFlow<WrapperTypeData> =
        MutableStateFlow(WrapperTypeData())
    val WrapperData: StateFlow<WrapperTypeData> = _WrapperData

    private val _SearchStates:MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val SearchState:StateFlow<SearchState> =_SearchStates


    private var searchquery=""

    private var currentPage = 0

    private var pageJob: Job? = null
    private var searchJob: Job? = null


    fun OnEvent(SearchEvent: SearchEvent) {
        when (SearchEvent) {
            is SearchEvent.LoadPage -> {
                LoadNewPage()
            }
            is SearchEvent.Searchquery->{
                onsearch(SearchEvent.query)
            }
        }
    }

    private fun LoadNewPage() {
        if (pageJob?.isActive ?: false||searchquery.isBlank()) {
            return
        }
        _SearchStates.value=SearchState.value.copy(isLoading = true)
        pageJob = viewModelScope.launch {
            repository.Search(searchquery, currentPage).onEach {

                it.onSuccess {
                    OnSucces(it)
                }
                it.onFailure {
                    Log.d("hata",it.localizedMessage)
                    _SearchStates.value=SearchState.value.copy(isLoading = false)
                }

            }.launchIn(this)


        }
    }

    private fun OnSucces(it: WrapperTypeData) {
        var newdata:WrapperTypeData
        WrapperData.value.let {data->
            newdata=WrapperTypeData(track = (data.track +it.track).toMutableList(), audiobook =(data.audiobook +it.audiobook).toMutableList() )

        }
        if (it.audiobook.isEmpty()) _SearchStates.value=SearchState.value.copy(audiobookEndReached = true)
        if (it.track.isEmpty()) _SearchStates.value=SearchState.value.copy(trackEndReached = true)

        _WrapperData.value=newdata
        _SearchStates.value=SearchState.value.copy(isLoading = false)
        currentPage+=50
    }

    private fun onsearch(query: String) {
        searchquery= query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            pageJob = null
            currentPage=0
            _WrapperData.value=WrapperTypeData()
            LoadNewPage()

        }
    }
}