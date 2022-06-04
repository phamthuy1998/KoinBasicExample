package com.thuypham.ptithcm.simplebaseapp.api

import com.thuypham.ptithcm.simplebaseapp.util.ApiConstant
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import retrofit2.Retrofit

object ApiManager : KoinComponent {

   /*
   fun getMainApi(): MovieApi {
        return createApi(clazz = MovieApi::class.java)
    }

    private fun <T> createApi(
        mainDomain: String = "",
        clazz: Class<T>
    ): T {
        val finalDomain = when {
            mainDomain.isNotEmpty() -> {
                mainDomain
            }
            else -> {
                ApiConstant.BASE_MOVIE_URL
            }
        }
        val retrofit = get<Retrofit> { parametersOf(finalDomain) }
        return retrofit.create(clazz)
    }
    */
}