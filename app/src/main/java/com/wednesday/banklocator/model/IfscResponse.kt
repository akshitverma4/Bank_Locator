package com.wednesday.banklocator.model


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "favourite_banks"
)
data class IfscResponse(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var ADDRESS: String,
    var BANK: String,
    var BRANCH: String,
    var CITY: String,
    var CONTACT: String,
    var DISTRICT: String,
    var IFSC: String,
    var RTGS: Boolean,
    var STATE: String
)
   

