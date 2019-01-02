package data.repository

import data.api.MovieApiService
import data.viewmodel.SearchViewModel

interface MovieBrowserRepository {
    suspend fun getMovieList(title: String): SearchViewModel
}

class MovieBrowserRepositoryImpl(private val apiService: MovieApiService) : MovieBrowserRepository {
    override suspend fun getMovieList(title: String): SearchViewModel {
        val res = apiService.searchByTitle(title)
        return SearchViewModel.create(res)
    }
}