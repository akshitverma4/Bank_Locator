package com.wednesday.banklocator.model

data class IfscResponse(
    val ADDRESS: String,
    val BANK: String,
    val BRANCH: String,
    val CITY: String,
    val CONTACT: String,
    val DISTRICT: String,
    var IFSC: String,
    val RTGS: Boolean,
    val STATE: String
)