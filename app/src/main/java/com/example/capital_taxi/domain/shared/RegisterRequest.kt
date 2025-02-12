
import com.example.capital_taxi.domain.shared.LoginResponse
import com.example.capital_taxi.domain.shared.RegisterResponse
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

data class RegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("role") val role: String // سيتم تحديده داخل `registerUser`
)



interface AuthApiService {
    @POST("auth/register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>
}




data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("role") val role: String,

)

interface LoginApiService {
    @POST("auth/login")
    suspend fun loginuser(@Body request: LoginRequest): Response<LoginResponse>
}