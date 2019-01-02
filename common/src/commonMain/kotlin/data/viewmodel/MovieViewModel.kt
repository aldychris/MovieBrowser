package data.viewmodel

import data.responses.MovieResponse

data class MovieViewModel (
    val title: String,
    val year: String,
    val type: String,
    val imgUrl: String
) {
    companion object {
        fun create(response: MovieResponse): MovieViewModel {
            return MovieViewModel(
                response.title,
                response.year,
                response.type,
                response.poster
            )
        }
    }
}