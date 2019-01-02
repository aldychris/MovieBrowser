package presentation.movielist

import data.viewmodel.SearchViewModel
import presentation.BaseView

interface MovieListView : BaseView {
    fun getMovieSuccsess(searchViewModel: SearchViewModel)
}