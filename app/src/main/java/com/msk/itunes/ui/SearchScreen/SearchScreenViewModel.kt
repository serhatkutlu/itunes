package com.msk.itunes.ui.SearchScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.itunes.Repository.SearchRepository
import com.msk.itunes.Responce.Data.SearcResponce.Result
import com.msk.itunes.Responce.Data.WrapperTypeData
import com.msk.itunes.ui.SearchScreen.component.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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
        pageJob = viewModelScope.launch {
            repository.Search(searchquery, currentPage).onEach {

                it.onSuccess {
                    var newdata:WrapperTypeData
                    WrapperData.value.let {data->
                         newdata=WrapperTypeData(track = (data.track +it.track).toMutableList(), audiobook =(data.audiobook +it.audiobook).toMutableList() )

                    }
                    //Log.d("dadas",WrapperData.value.audiobook.size.toString())
                    _WrapperData.value=newdata
                    currentPage+=25
                }

            }.launchIn(this)


        }
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