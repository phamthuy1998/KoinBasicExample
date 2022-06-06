package com.thuypham.ptithcm.simplebaseapp.data.remote


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieList(
    @SerializedName("dates")
    var dates: Dates? = null,
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("results")
    var movies: ArrayList<Movie>? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
) : Parcelable