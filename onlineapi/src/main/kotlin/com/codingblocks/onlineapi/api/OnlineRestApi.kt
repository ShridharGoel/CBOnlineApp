package com.codingblocks.onlineapi.api

import com.codingblocks.onlineapi.models.Doubts
import com.codingblocks.onlineapi.models.Extension
import com.codingblocks.onlineapi.models.Leaderboard
import com.codingblocks.onlineapi.models.PostStream
import com.codingblocks.onlineapi.models.RatingModel
import com.codingblocks.onlineapi.models.ResetRunAttempt
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OnlineRestApi {
    @GET("v2/run_attempts/{runid}/progress")
    fun getMyCourseProgress(@Path("runid") id: String): Call<HashMap<Any, Any>>

    @GET("v2/lectures/otp")
    fun getOtp(
        @Query("videoId") videoId: String,
        @Query("sectionId") sectionId: String,
        @Query("runAttemptId") runAttemptId: String,
        @Query("offline") offline: Boolean = false
    ): Call<JsonObject>

    @POST("jwt/login?android=true")
    @FormUrlEncoded
    fun getToken(@Field("code") code: String): Call<JsonObject>

    @GET("v2/courses/{id}/rating")
    suspend fun getCourseRating(@Path("id") id: String): RatingModel

    @GET("v2/users/me")
    fun getMe(): Call<JsonObject>

    @GET("v2/runs/{runId}/enroll")
    fun enrollTrial(@Path("runId") id: String): Call<JsonObject>

    @GET("v2/jwt/logout")
    fun logout(): Call<JsonObject>

    @GET("v2/runs/{runId}/buy")
    fun addToCart(@Path("runId") id: String): Call<JsonObject>

    @GET("v2/runs/{runid}/leaderboard")
    fun leaderboardById(
        @Path("runid") id: String
    ): Call<List<Leaderboard>>

    @GET("v2/courses/{runid}/doubts?order=latest")
    fun getDoubts(@Path("runid") id: String): Call<Doubts>

    @GET("v2/courses/doubts/{doubtid}")
    fun getDoubtById(@Path("doubtid") id: Int): Call<PostStream>

    @GET("v2/runs/cart")
    fun getCart(): Call<JsonObject>

    @GET("v2/runs/clear_cart")
    fun clearCart(): Call<JsonObject>

    @POST("jwt/refresh/?android=true")
    @FormUrlEncoded
    fun refreshToken(@Field("refresh_token") refresh_token: String): Call<JsonObject>

    @POST("v2/progresses/reset")
    fun resetProgress(@Body runAttemptId: ResetRunAttempt): Call<ResponseBody>

    @GET("v2/run_attempts/{runAttemptId}/requestApproval")
    fun requestApproval(@Path("runAttemptId") id: String): Call<ResponseBody>

    @GET("v2/runs/products/{id}")
    fun getExtensions(@Path("id") productId: Int): Call<Extension>

    @POST("v2/runs/extensions/{id}/buy")
    fun buyExtension(@Path("id") extensionId: Int): Call<JsonObject>


}
