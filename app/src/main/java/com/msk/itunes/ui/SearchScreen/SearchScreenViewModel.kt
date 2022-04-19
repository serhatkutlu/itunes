package com.msk.itunes.ui.SearchScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.itunes.Api.itunesApi
import com.msk.itunes.Repository.SearchRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class SearchScreenViewModel @Inject constructor ( private val repository: SearchRepository):ViewModel() {

    fun get() {
        viewModelScope.launch() {
            val a=repository.Search("aleyna").collect{
                it.onSuccess {
                    it.forEach { t, u ->
                        Log.d("hatalar",t)
                    }

                }
            }

        }
    }
}