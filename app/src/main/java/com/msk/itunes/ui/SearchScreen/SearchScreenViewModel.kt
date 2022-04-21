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


    private val _MediaData: MutableStateFlow<MutableList<MediaTypeDataClass>> = MutableStateFlow(mutableListOf())
    val MediaData: MutableStateFlow<MutableList<MediaTypeDataClass>> =_MediaData

    private val _SearchStates:MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val SearchState:StateFlow<SearchState> =_SearchStates



    fun OnEvent(SearchEvent: SearchEvent) {
        when (SearchEvent) {
            is SearchEvent.Searchquery->{
                onsearch(SearchEvent.query)
            }
        }
    }



    private fun onsearch(query: String) {
        _SearchStates.value=SearchState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val list = mutableListOf<MediaTypeDataClass>()
            Constants.MediaType.forEach {type->
                async {
                    repository.Search(query = query, offset = 0, type = type).onEach { Results ->

                        _SearchStates.value = SearchState.value.copy(isLoading = false)
                        Results.onSuccess {
                            list.add(MediaTypeDataClass(type, it.results))
                        }
                    }.launchIn(this)
                }.await()

            }

            _MediaData.value = list

        }
    }
}

/*private fun LoadNewPage() {
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
   }*/