package com.msk.itunes.ui.SearchScreen

import com.google.common.truth.Truth
import com.msk.itunes.Repository.SearchRepository.FakeSearchRepositoryImp
import com.msk.itunes.Util.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class SearchScreenViewModelTest{

    lateinit var viewModel: SearchScreenViewModel

    lateinit var repository:FakeSearchRepositoryImp

    @Before
    fun setup(){
        repository= FakeSearchRepositoryImp()
        viewModel= SearchScreenViewModel(repository)
    }

    @Test
    fun`Search query func  test,return  id`() = runBlocking{

        viewModel.OnEvent(SearchEvent.Searchquery("a"))
        delay(1000)
       val search=viewModel.MediaData.first()

        Truth.assertThat(search.first().result.first().trackId ).isEqualTo(5)
    }
    @Test
    fun`type test  test,return  id`() = runBlocking{

        viewModel.OnEvent(SearchEvent.Searchquery("a"))
        delay(1000)
       val search=viewModel.MediaData.first()
        Constants.MediaType.forEachIndexed{i,type->
            Truth.assertThat(search[i].mediaType ).isEqualTo(type)

        }
    }


}