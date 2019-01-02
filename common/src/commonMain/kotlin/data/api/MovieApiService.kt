package data.api

import data.responses.MovieResponse
import data.responses.RatingsResponse
//import data.responses.RatingsResponse
import data.responses.SearchResultResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.UserAgent
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.takeFrom
import kotlinx.serialization.json.JSON


class MovieApiService(private val endPoint: String, private val apiKey: String) {

    var client: HttpClient = makeHttpClient()

    fun makeHttpClient (): HttpClient {
        return HttpClient{
            expectSuccess = false
            install(JsonFeature) {
                serializer = KotlinxSerializer(json = JSON.nonstrict).apply {
                    setMapper(SearchResultResponse::class, SearchResultResponse.serializer())
                    setMapper(MovieResponse::class, MovieResponse.serializer())
                    setMapper(RatingsResponse::class, RatingsResponse.serializer())
                }
            }
            install(MovieBrowserApiAdapter)
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            install(UserAgent) {
                agent = "kotlinlibs"
            }
        }
    }


    suspend fun searchByTitle(movieTitle: String): SearchResultResponse = client.get {
        url {
            takeFrom(endPoint)
            parameter("apikey", apiKey)
            parameter("s", movieTitle)
        }
    }

}