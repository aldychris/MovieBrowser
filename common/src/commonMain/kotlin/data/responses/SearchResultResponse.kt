package data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultResponse(

    @SerialName("Search")
    val searchResult: List<MovieResponse>,

    @SerialName("Response")
    val response: String

//    @SerialName("totalResults")
//    val totalResult: String
)