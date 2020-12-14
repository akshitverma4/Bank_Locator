package com.wednesday.banklocator.network


import com.wednesday.banklocator.model.IfscResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IfscSearchApi {

    @GET("/{ifscCode}")
    suspend fun searchForIFSC(
        @Path("ifscCode")
        Y: String
    ): Response<IfscResponse>
}