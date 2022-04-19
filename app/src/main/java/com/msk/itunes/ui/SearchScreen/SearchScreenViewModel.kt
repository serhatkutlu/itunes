package com.msk.itunes.ui.SearchScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.itunes.Repository.SearchRepository
import com.msk.itunes.Responce.Data.SearcResponce.Result
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


    private val _MapData: MutableStateFlow<MutableMap<String, MutableList<Result>>?> =
        MutableStateFlow(null)
    val MapData: StateFlow<MutableMap<String, MutableList<Result>>?> = _MapData

     //var searchquery: MutableStateFlow<String> = MutableStateFlow("")
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
        if (pageJob?.isActive ?: false) {
            return
        }
        pageJob = viewModelScope.launch {
            repository.Search(searchquery, currentPage).onEach {

                it.onSuccess {
                    _MapData.value = it
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
            LoadNewPage()

        }
    }
}