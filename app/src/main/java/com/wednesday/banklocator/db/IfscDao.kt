package com.wednesday.banklocator.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wednesday.banklocator.model.Ifsc


@Dao
interface IfscDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(ifsc: Ifsc): Long

    @Query("SELECT * FROM favourite_banks")
    fun getAllBanks(): LiveData<List<Ifsc>>

    @Delete
    suspend fun deleteBanks(ifsc: Ifsc)
}