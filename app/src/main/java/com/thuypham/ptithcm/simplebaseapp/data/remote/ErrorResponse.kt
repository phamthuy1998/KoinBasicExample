package com.thuypham.ptithcm.simplebaseapp.data.remote

import com.google.gson.annotations.SerializedName


data class ErrorResponse(
    @SerializedName("status_code")
    var statusCode: Int?,
    @SerializedName("status_message")
    var statusMessage: String?,
    @SerializedName("success")
    var success: Boolean?
)