package com.depi.ghr4_swd4_s1_compose

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("products")
    suspend fun getProducts() : Response<List<ProductModel>>


    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest)
    : Response<LoginResponse>
}

data class LoginRequest(
    val email:String,
    val password:String
)

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken:String,

    @SerializedName("refresh_token")
    val refreshToken:String,
)

data class ProductModel(
    val id:Int,
    val title:String,
    val price:Int,
    val description:String,
    val images:List<String>
)