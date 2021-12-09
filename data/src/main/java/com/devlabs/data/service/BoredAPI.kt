package com.devlabs.data.service

import com.devlabs.data.dto.DTOActivity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BoredAPI {
    @GET("activity/")
    suspend fun getActivity(@Query("type") type: String): Response<DTOActivity>
}