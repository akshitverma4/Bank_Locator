package com.wednesday.banklocator.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "favourite_banks"
)
data class IfscResponse(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var ADDRESS: String="",
    var BANK: String="Search",
    var BRANCH: String="",
    var CITY: String="",
    var CONTACT: String="",
    var DISTRICT: String="",
    var IFSC: String="",
    var RTGS: Boolean=false,
    var STATE: String=""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id!!)
        parcel.writeString(ADDRESS)
        parcel.writeString(BANK)
        parcel.writeString(BRANCH)
        parcel.writeString(CITY)
        parcel.writeString(CONTACT)
        parcel.writeString(DISTRICT)
        parcel.writeString(IFSC)
        parcel.writeByte(if (RTGS) 1 else 0)
        parcel.writeString(STATE)
    }
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IfscResponse> {
        override fun createFromParcel(parcel: Parcel): IfscResponse {
            return IfscResponse(parcel)
        }

        override fun newArray(size: Int): Array<IfscResponse?> {
            return arrayOfNulls(size)
        }
    }
}

