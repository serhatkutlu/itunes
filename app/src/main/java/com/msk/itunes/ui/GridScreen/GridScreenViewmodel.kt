package com.msk.itunes.ui.GridScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.itunes.Repository.SearchRepository.SearchRepository
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
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
class GridScreenViewmodel @Inject constructor(private val repository: SearchRepository):ViewModel() {

    private val _DataState:MutableStateFlow<List<Result>> = MutableStateFlow(mutableListOf())
    val DataState:StateFlow<List<Result>> =_DataState

    private val _SearchStates:MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val SearchState:StateFlow<SearchState> =_SearchStates


    private var searchquery=""
    private var type=""

    private var currentPage = 0

    private var pageJob: Job? = null


    fun OnEvent(  gridScreenEvent: GridScreenEvent) {
        when(gridScreenEvent){
            is GridScreenEvent.LoadNewPage->{
                if (searchquery.isBlank()||type.isBlank()){
                    searchquery=gridScreenEvent.searchQuery
                    type=gridScreenEvent.type
                }
                LoadNewPage()
            }
        }
    }

private fun LoadNewPage() {
       if (pageJob?.isActive ?: false||searchquery.isBlank()) {
           return
       }
       _SearchStates.value=SearchState.value.copy(isLoading = true)
       pageJob = viewModelScope.launch {
           repository.Search(searchquery, currentPage,type,25).onEach {

               it.onSuccess {
                   if (it.results.isNullOrEmpty()) _SearchStates.value=SearchState.value.copy(endReached = true)
                    Log.d("hata",type+"______"+it.results.size)
                   _DataState.emit(DataState.value+it.results)
                   _SearchStates.value=SearchState.value.copy(isLoading = false)
                   currentPage+=25
               }
               it.onFailure {
                   _SearchStates.value=SearchState.value.copy(isLoading = false)
               }

           }.launchIn(this)


       }
   }



}