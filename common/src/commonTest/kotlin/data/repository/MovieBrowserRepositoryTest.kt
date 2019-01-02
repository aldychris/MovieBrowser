package data.repository

import InjectMocksRule
import data.api.MovieApiService
import data.responses.SearchResultResponse
import data.viewmodel.SearchViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.parse
import org.amshove.kluent.should
import kotlin.test.BeforeTest
import kotlin.test.Test

class MovieBrowserRepositoryTest {

    lateinit var repository: MovieBrowserRepository

    @MockK
    lateinit var apiService: MovieApiService

    @BeforeTest
    fun setup(){
        InjectMocksRule.createMockK(this)

        repository = MovieBrowserRepositoryImpl(apiService)
    }

    @Test
    fun `get data from network should call search by title and SearchViewModel instance`() {

        coEvery{ apiService.searchByTitle("Game") } returns mockSearchResult()

        var result: SearchViewModel? = null
        GlobalScope.launch {
           result = repository.getMovieList("Game")
        }

        coVerify { apiService.searchByTitle("Game") }
        should { result is SearchViewModel }
    }

    @UseExperimental(ImplicitReflectionSerializer::class)
    fun mockSearchResult(): SearchResultResponse{
        return JSON(strictMode = false).parse("{\"Search\":[{\"Title\":\"Game of Thrones\",\"Year\":\"2011–\",\"imdbID\":\"tt0944947\",\"Type\":\"series\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMjE3NTQ1NDg1Ml5BMl5BanBnXkFtZTgwNzY2NDA0MjI@._V1_SX300.jpg\"},{\"Title\":\"The Imitation Game\",\"Year\":\"2014\",\"imdbID\":\"tt2084970\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BOTgwMzFiMWYtZDhlNS00ODNkLWJiODAtZDVhNzgyNzJhYjQ4L2ltYWdlXkEyXkFqcGdeQXVyNzEzOTYxNTQ@._V1_SX300.jpg\"},{\"Title\":\"Sherlock Holmes: A Game of Shadows\",\"Year\":\"2011\",\"imdbID\":\"tt1515091\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTQwMzQ5Njk1MF5BMl5BanBnXkFtZTcwNjIxNzIxNw@@._V1_SX300.jpg\"},{\"Title\":\"The Game\",\"Year\":\"1997\",\"imdbID\":\"tt0119174\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZGVmMDNmYmEtNGQ2Mi00Y2ZhLThhZTYtYjE5YmQzMjZiZGMxXkEyXkFqcGdeQXVyNDk3NzU2MTQ@._V1_SX300.jpg\"},{\"Title\":\"Ender's Game\",\"Year\":\"2013\",\"imdbID\":\"tt1731141\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMjAzMzI5OTgzMl5BMl5BanBnXkFtZTgwMTU5MTAwMDE@._V1_SX300.jpg\"},{\"Title\":\"Spy Game\",\"Year\":\"2001\",\"imdbID\":\"tt0266987\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNjNhOGZkNzktMGU3NC00ODk2LWE4NjctZTliN2JjZTQxZmIxXkEyXkFqcGdeQXVyNDk3NzU2MTQ@._V1_SX300.jpg\"},{\"Title\":\"Game Night\",\"Year\":\"2018\",\"imdbID\":\"tt2704998\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMjI3ODkzNDk5MF5BMl5BanBnXkFtZTgwNTEyNjY2NDM@._V1_SX300.jpg\"},{\"Title\":\"Molly's Game\",\"Year\":\"2017\",\"imdbID\":\"tt4209788\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNTkzMzRlYjEtMTQ5Yi00OWY3LWI0NzYtNGQ4ZDkzZTU0M2IwXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg\"},{\"Title\":\"Gerald's Game\",\"Year\":\"2017\",\"imdbID\":\"tt3748172\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMzg0NGE0N2MtYTg1My00NTBkLWI5NjEtZTgyMDA0MTU4MmIyXkEyXkFqcGdeQXVyMTU2NTcyMg@@._V1_SX300.jpg\"},{\"Title\":\"The Game Plan\",\"Year\":\"2007\",\"imdbID\":\"tt0492956\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTAzNDIyODYzMTJeQTJeQWpwZ15BbWU3MDA3NTA5NDE@._V1_SX300.jpg\"}],\"totalResults\":\"3330\",\"Response\":\"True\"}")
    }

}