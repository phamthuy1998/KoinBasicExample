package com.thuypham.ptithcm.simplebaseapp.data.remote


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class MovieList(
    @SerializedName("dates")
    var dates: Dates?,
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var movies: ArrayList<Movie>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
) : Parcelable