package data.api

import io.ktor.client.HttpClient
import io.ktor.client.features.HttpClientFeature
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.HttpResponsePipeline
import io.ktor.http.isSuccess
import io.ktor.util.AttributeKey

object MovieBrowserApiAdapter : HttpClientFeature<Unit, MovieBrowserApiAdapter> {

    override val key: AttributeKey<MovieBrowserApiAdapter> = AttributeKey("MovieBrowserApiAdapter")

    override fun prepare(block: Unit.() -> Unit): MovieBrowserApiAdapter = this

    override fun install(feature: MovieBrowserApiAdapter, scope: HttpClient) {
        scope.responsePipeline.intercept(HttpResponsePipeline.Receive) {
            val response = subject.response as HttpResponse

            if (!response.status.isSuccess())
                throw BadRequest(response)

            proceedWith(subject)
        }
    }
}

class BadRequest(val response: HttpResponse) : Throwable()
