package data.responses

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(

    @SerialName("imdbID")
    val id: String,

    @SerialName("Title")
    val title: String,

    @SerialName("Year")
    val year: String,

    @SerialName("Poster")
    val poster: String,

    @Optional
    @SerialName("Rated")
    val rated: String = "",

    @Optional
    @SerialName("Released")
    val released: String = "",

    @Optional
    @SerialName("Runtime")
    val runTime: String = "",

    @Optional
    @SerialName("Director")
    val director: String = "",

    @Optional
    @SerialName("Writer")
    val writer: String = "",

    @Optional
    @SerialName("Actors")
    val actors: String = "",

    @Optional
    @SerialName("Plot")
    val plot: String = "",

    @Optional
    @SerialName("Language")
    val language: String = "",

    @Optional
    @SerialName("Country")
    val country: String = "",

    @Optional
    @SerialName("Awards")
    val awards: String = "",

    @Optional
    @SerialName("RatingsResponse")
    val ratingsResponse: RatingsResponse = RatingsResponse("",""),

    @Optional
    @SerialName("Metascore")
    val metascore: String = "",

    @Optional
    @SerialName("imdbRating")
    val imdbRating: String = "",

    @Optional
    @SerialName("imdbVotes")
    val imdbVotes: String = "",

    @Optional
    @SerialName("Type")
    val type: String = "",

    @Optional
    @SerialName("DVD")
    val dvdReleaseDate: String = "",

    @Optional
    @SerialName("BoxOffice")
    val boxOfficeEarn: String = "",

    @Optional
    @SerialName("Production")
    val productionHouse: String = "",

    @Optional
    @SerialName("Website")
    val website: String = ""
)
