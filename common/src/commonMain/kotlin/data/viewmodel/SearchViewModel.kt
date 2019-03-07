package data.viewmodel

import data.responses.SearchResultResponse

data class SearchViewModel(
    var movies: List<MovieViewModel>) {

    companion object {
        fun create(response: SearchResultResponse): SearchViewModel {

            val listContent = ArrayList<MovieViewModel>()
            response.searchResult.forEach {
                listContent.add(MovieViewModel.create(it))
            }

            return SearchViewModel(listContent)

        }
    }

    fun parseForFlutter(): List<Map<String, String>> {

        val listMov = ArrayList<Map<String, String>>()
        for (mdl in this.movies){
            listMov.add(mdl.parseForFlutter())
        }

        return listMov
    }

}