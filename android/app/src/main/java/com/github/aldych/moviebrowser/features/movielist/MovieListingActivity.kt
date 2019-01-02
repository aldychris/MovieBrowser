package com.github.aldych.moviebrowser.features.movielist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.github.aldych.moviebrowser.MovieBrowser
import com.github.aldych.moviebrowser.R
import com.github.aldych.moviebrowser.ui.MarginDecoration
import data.viewmodel.MovieViewModel
import data.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.act_movie_list_activity.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.anko.*
import presentation.movielist.MovieListPresenter
import presentation.movielist.MovieListView
import java.util.logging.Logger

class MovieListingActivity : AppCompatActivity(), AnkoLogger, MovieListView {

    private val repository by lazy { (application as MovieBrowser).dataRepository }
    private val presenter by lazy { MovieListPresenter(Dispatchers.Main, this, repository) }

    private var listAdapter = MovieListAdapter(this) {itemClicked(it)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_movie_list_activity)
        ButterKnife.bind(this)

        rvMovie.layoutManager = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
        rvMovie.addItemDecoration(MarginDecoration(this.resources.getDimensionPixelSize(R.dimen.divider_height)))
        rvMovie.adapter = listAdapter
    }

    private fun itemClicked(mdl: MovieViewModel){
        info { mdl }
    }

    @OnClick(R.id.btnSearch)
    fun btnSearchClicked(){
        presenter.getMovieList(txtSearch.text.toString())
    }

    override fun getMovieSuccsess(searchViewModel: SearchViewModel) {
        listAdapter.refreshAllData(searchViewModel.movies)
    }

    override fun showProgressIndicator(show: Boolean) {
        when(show){
            true -> prgsbar.visibility = View.VISIBLE
            false -> prgsbar.visibility = View.GONE
        }
    }

    override fun showError(error: Throwable) {
        error { error.message }

        alert(""+error.message) {
            title = "Warning"
            yesButton {  }
        }.show()
    }



}
