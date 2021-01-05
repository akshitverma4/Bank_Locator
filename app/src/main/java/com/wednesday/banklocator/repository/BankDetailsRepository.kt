package com.wednesday.banklocator.repository

import com.wednesday.banklocator.db.FavouritesBankDatabase
import com.wednesday.banklocator.model.Ifsc
import com.wednesday.banklocator.network.RetrofitInstance

class BankDetailsRepository ( private val db : FavouritesBankDatabase) {

    suspend fun getIfscCode(IFSC:String)=RetrofitInstance.api.searchForIFSC(IFSC)

    suspend fun upsert(item: Ifsc) = db.getBanksDao().upsert(item)

    suspend fun delete(item: Ifsc) = db.getBanksDao().deleteBanks(item)

    fun getAllBanks() = db.getBanksDao().getAllBanks()

}