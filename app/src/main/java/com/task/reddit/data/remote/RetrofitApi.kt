package com.task.reddit.data.remote

import com.task.reddit.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("top.json")
    suspend fun request(
        @Query("limit") limit: String
    ) : Response<ResponseModel>

}