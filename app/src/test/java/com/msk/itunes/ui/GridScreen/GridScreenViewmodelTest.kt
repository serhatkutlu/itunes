package com.msk.itunes.ui.GridScreen

import com.google.common.truth.Truth
import com.msk.itunes.CoroutineTestRule
import com.msk.itunes.Repository.SearchRepository.FakeSearchRepositoryImp
import com.msk.itunes.ui.SearchScreen.SearchEvent
import com.msk.itunes.ui.SearchScreen.SearchScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GridScreenViewmodelTest{

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    lateinit var viewModel: GridScreenViewmodel

    lateinit var repository: FakeSearchRepositoryImp

    @Before
    fun setup(){
        repository= FakeSearchRepositoryImp()
        viewModel= GridScreenViewmodel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun`Grid Screen viewmodel paginatio test,return  page`() =coroutineTestRule.runBlockingTest {

        repeat(3){
            viewModel.OnEvent(GridScreenEvent.LoadNewPage("a","all"))
        }
        val newpage=viewModel.currentPage
        Truth.assertThat(newpage ).isEqualTo(3*25)
    }
}