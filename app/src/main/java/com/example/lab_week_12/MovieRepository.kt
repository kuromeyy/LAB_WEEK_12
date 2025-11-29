package com.example.lab_week_12

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lab_week_12.api.MovieService
import com.example.lab_week_12.model.Movie

class MovieRepository(private val movieService: MovieService) {

    private val apiKey = "a8fca104c32b480bbd358010a307b716"

    // LiveData list movie
    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = movieLiveData

    // LiveData error
    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String>
        get() = errorLiveData

    // Fetch movies dari API (COROUTINE)
    suspend fun fetchMovies() {
        try {
            val popularMovies = movieService.getPopularMovies(apiKey)
            movieLiveData.postValue(popularMovies.results)
        } catch (exception: Exception) {
            errorLiveData.postValue(
                "An error occurred: ${exception.message}"
            )
        }
    }
}
