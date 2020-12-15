package com.wednesday.banklocator.model

data class IfscResponse(
    val address: String,
    val bank: String,
    val branch: String,
    val city: String,
    val contact: String,
    val district: String,
    var ifsc: String,
    val rtgs: Boolean,
    val state: String
)
