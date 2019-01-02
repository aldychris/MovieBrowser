package presentation.movielist

import data.repository.MovieBrowserRepository
import kotlinx.coroutines.launch
import presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

class MovieListPresenter(
    private val uiContext: CoroutineContext,
    private val view: MovieListView,
    private val repository: MovieBrowserRepository
): BasePresenter(uiContext, view) {

    fun getMovieList(title: String) {

        if(title.trim().length < 4) {
            view.showError(Throwable("Validation error !!!"))
            return
        }

        view.showProgressIndicator(true)

        launch(coroutineContext) {
            val data = repository.getMovieList(title)
            view.getMovieSuccsess(data)
        }.invokeOnCompletion {
            view.showProgressIndicator(false)
        }

    }

}


