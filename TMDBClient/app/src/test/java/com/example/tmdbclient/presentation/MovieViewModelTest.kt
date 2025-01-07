package com.example.tmdbclient.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.model.Movie
import com.example.tmdbclient.domain.GetMoviesUseCase
import com.example.tmdbclient.domain.UpdateMoviesUseCase
import com.example.tmdbclient.getOrAwaitValue
import com.example.tmdbclient.repository.FakeMovieRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {
    @get: Rule
    var instantTaskExecuteRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        val fakeMovieRepository = FakeMovieRepository()
        val getMoviesUseCase = GetMoviesUseCase(fakeMovieRepository)
        val updateMoviesUseCase = UpdateMoviesUseCase(fakeMovieRepository)
        viewModel = MovieViewModel(getMoviesUseCase, updateMoviesUseCase)
    }

    @Test
    fun getMoviesTest() {
        val expectedMovies = listOf(
            Movie(1, "overview1", "posterPath1", "date1", "title1"),
            Movie(2, "overview2", "posterPath2", "date2", "title2")
        )
        val movies = viewModel.getMovies().getOrAwaitValue()
        assertThat(movies).isEqualTo(expectedMovies)
    }

    @Test
    fun updateMoviesTest() {
        val expectedMovies = listOf(
            Movie(3, "overview3", "posterPath3", "date3", "title3"),
            Movie(4, "overview4", "posterPath4", "date4", "title4")
        )
        val movies = viewModel.updateMovies().getOrAwaitValue()
        assertThat(movies).isEqualTo(expectedMovies)
    }
}