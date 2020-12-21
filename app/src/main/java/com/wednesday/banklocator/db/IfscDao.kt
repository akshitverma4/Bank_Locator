package com.wednesday.banklocator.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wednesday.banklocator.model.IfscResponse


@Dao
interface IfscDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(Ifsc: IfscResponse): Long

    @Query("SELECT * FROM favourite_banks")
    fun getAllBanks(): LiveData<List<IfscResponse>>

    @Delete
    suspend fun deleteBanks(Ifsc: IfscResponse)
}