import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*


object KtorClient {
    val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            gson {
                // Make sure Gson has the right configuration (optional, can be customized)
                setLenient()
                serializeNulls()
            }
        }
    }
}

data class ChatRequest(val message: String)
data class ChatResponse(val response: String)
