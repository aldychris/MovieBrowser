package js

import data.api.MovieApiService

fun main(arguments: Array<String>) {

    val message = "Hello JavaScript!"
    println(message)

    val api = MovieApiService("https://www.omdbapi.com", "")
    println(api)
}


