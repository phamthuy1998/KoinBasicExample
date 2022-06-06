package com.thuypham.ptithcm.simplebaseapp.data.remote


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Dates(
    @SerializedName("maximum")
    var maximum: String? = null,
    @SerializedName("minimum")
    var minimum: String? = null
) : Parcelable