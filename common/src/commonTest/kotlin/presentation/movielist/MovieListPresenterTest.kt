
import data.repository.MovieBrowserRepository
import data.viewmodel.SearchViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import presentation.movielist.MovieListPresenter
import presentation.movielist.MovieListView
import kotlin.test.BeforeTest
import kotlin.test.Test

class MovieListPresenterTest {

    lateinit var presenter: MovieListPresenter

    @MockK
    lateinit var context: Dispatchers

    @MockK
    lateinit var view: MovieListView

    @MockK
    lateinit var repository: MovieBrowserRepository

    @MockK
    lateinit var viewModel: SearchViewModel


    @BeforeTest
    fun setup(){
        InjectMocksRule.createMockK(this)
        presenter = MovieListPresenter(context.Default, view, repository)

        coEvery{ repository.getMovieList("Game") } answers  { viewModel }
    }

    @Test
    fun `call repository, show progress, call success and dismiss progress`() {
        presenter.getMovieList("Game")

        verifyOrder {
            view.showProgressIndicator(true)
            view.getMovieSuccsess(viewModel)
            view.showProgressIndicator(false)
        }
    }

    @Test
    fun `input less then 4 char will show error`() {
        presenter.getMovieList("")

        verify { view.showError(any()) }

    }
}