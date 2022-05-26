package com.thuypham.ptithcm.simplebaseapp.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MainViewModel
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MovieDetailViewModel
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { MovieDetailViewModel() }
}
//
//val sampleModule = module {
//    viewModel { MainViewModel(get()) }
//
//    factory { GetNowPlayingUseCase(get()) }
//    // Another way
//    // factoryOf(::GetNowPlayingUseCase)
//
//    single<MovieRepository> { MovieRepositoryImpl(get()) }
////    // New way (from Koin 3.2)
////    singleOf(::MovieRepositoryImpl) {
////        bind<MovieRepository>()
////        createdAtStart()
////        named("MovieRepository")
////    }
////
////    singleOf(::MovieRepositoryImpl) bind MovieRepository::class
//
//    single(named("SharedPref")) { SharedPreferencesStorage(get()) } withOptions {
//        named("qualifier")
//        named<IStorage>()
//        bind<SharedPreferencesStorage>()
//        createdAtStart()
//    }
//}

