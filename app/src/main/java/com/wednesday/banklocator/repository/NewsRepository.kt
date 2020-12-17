package com.wednesday.banklocator.repository

import com.wednesday.banklocator.network.RetrofitInstance

class NewsRepository {
    suspend fun getIfscCode(IFSC:String)=RetrofitInstance.api.searchForIFSC(IFSC)

}