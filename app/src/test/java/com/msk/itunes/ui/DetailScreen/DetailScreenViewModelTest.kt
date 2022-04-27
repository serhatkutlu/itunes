package com.msk.itunes.ui.DetailScreen

import com.google.common.truth.Truth
import com.msk.itunes.CoroutineTestRule
import com.msk.itunes.Data.Entity.SavedId
import com.msk.itunes.Repository.DetailRepository.FakeDetailRepositoryImp
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

class DetailScreenViewModelTest {


    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    lateinit var viewModel: DetailScreenViewModel

    lateinit var repository: FakeDetailRepositoryImp

    @Before
    fun setup() {
        repository = FakeDetailRepositoryImp()
        viewModel = DetailScreenViewModel(repository)
    }


    @Test
    fun`onsearch func  test,return  id`() = runBlocking{
    val id=10
        viewModel.OnEvent(DetailScreenEvent.LoadDetail(id))

        delay(1000)
        val search=viewModel.result.value

        Truth.assertThat(search?.trackId ).isEqualTo(id)
    }



    @ExperimentalCoroutinesApi
    @Test
    fun`favorite id delete,save and insert Test`() = coroutineTestRule.runBlockingTest{

        //saves to database on first click
        val savedId=SavedId(id = 2, type = "all", imageUrl = "", name = "name")
        viewModel.OnEvent(DetailScreenEvent.ClickFavorite(savedId))

        viewModel.OnEvent(DetailScreenEvent.CheckFavorite(savedId.id))

        val idfavorite1=viewModel.idFavorite.value

        Truth.assertThat(idfavorite1 ).isEqualTo(true)
        //delete to database on second click

        viewModel.OnEvent(DetailScreenEvent.ClickFavorite(savedId))

        viewModel.OnEvent(DetailScreenEvent.CheckFavorite(savedId.id))

        val idfavorite2=viewModel.idFavorite.value
        Truth.assertThat(idfavorite2 ).isEqualTo(false)
    }


}