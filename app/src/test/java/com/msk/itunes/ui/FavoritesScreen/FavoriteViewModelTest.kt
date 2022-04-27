package com.msk.itunes.ui.FavoritesScreen

import com.google.common.truth.Truth
import com.msk.itunes.CoroutineTestRule
import com.msk.itunes.Repository.DetailRepository.FakeDetailRepositoryImp
import com.msk.itunes.Repository.FavoritesRepository.FakeFavoritesRepositoryImp
import com.msk.itunes.ui.DetailScreen.DetailScreenViewModel
import com.msk.itunes.ui.FavoritesScreen.component.FavoriteEvent
import com.msk.itunes.ui.SearchScreen.SearchEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteViewModelTest{
@get:Rule
val coroutineTestRule = CoroutineTestRule()

lateinit var viewModel: FavoriteViewModel

lateinit var repository: FakeFavoritesRepositoryImp

@Before
fun setup() {

    repository = FakeFavoritesRepositoryImp()
    viewModel = FavoriteViewModel(repository)
}

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun`Search query func  test,return  id`() = coroutineTestRule.runBlockingTest{

        viewModel.OnEvent(FavoriteEvent.LoadNewPage)
        val search=viewModel.FavoriteData.value.first()

        Truth.assertThat(search.id).isEqualTo(8)
    }
}
