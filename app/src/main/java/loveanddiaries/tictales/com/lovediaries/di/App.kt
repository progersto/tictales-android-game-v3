package loveanddiaries.tictales.com.lovediaries.di

import android.content.Context
import com.google.gson.Gson
import loveanddiaries.tictales.com.lovediaries.datamanagers.AuthDataManager
import loveanddiaries.tictales.com.lovediaries.datamanagers.AuthDataManagerImpl
import loveanddiaries.tictales.com.lovediaries.datamanagers.MainDataManager
import loveanddiaries.tictales.com.lovediaries.datamanagers.MainDataManagerImpl
import loveanddiaries.tictales.com.lovediaries.network.ApiInterface
import loveanddiaries.tictales.com.lovediaries.ui.auth.AuthViewModel
import loveanddiaries.tictales.com.lovediaries.ui.main.MainViewModel
import loveanddiaries.tictales.com.lovediaries.utils.BASE_URSL
import loveanddiaries.tictales.com.lovediaries.utils.PreffsManager
import org.jetbrains.anko.defaultSharedPreferences
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { MainViewModel(get()) }
}

private val networkModule = module {
    single { Retrofit.Builder()
            .baseUrl(BASE_URSL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build().create(ApiInterface::class.java)}
}

private val dataModule = module {
    single { get<Context>().defaultSharedPreferences }
    single { PreffsManager(get()) }
    single { AuthDataManagerImpl(get(), get(), get()) as AuthDataManager }
    single { MainDataManagerImpl(get(), get()) as MainDataManager }
}

val appModules  = mutableListOf(viewModelModule, networkModule, dataModule)