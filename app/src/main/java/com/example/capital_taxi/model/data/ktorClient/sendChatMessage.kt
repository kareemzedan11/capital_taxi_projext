import com.google.gson.Gson
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.http.*

suspend fun sendChatMessage(message: String): String {
    val client = KtorClient.client
    val url = "https://a049-156-213-190-155.ngrok-free.app/chat"

    return try {
        val response: HttpResponse = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(ChatRequest(message))
        }

        // Log or inspect the raw response
        val responseBody = response.bodyAsText()
        println("Raw response: $responseBody")

        // Manually deserialize using Gson if needed
        val gson = Gson()
        val chatResponse = gson.fromJson(responseBody, ChatResponse::class.java)
        chatResponse.response
    } catch (e: Exception) {
        "Error: ${e.message}"
    }
}

