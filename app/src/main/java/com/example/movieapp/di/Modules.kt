package com.example.movieapp.di

import com.example.movieapp.datasource.ConfigurationDataSource
import com.example.movieapp.datasource.MovieDataSource
import com.example.movieapp.datasource.NetworkConfigurationDataSource
import com.example.movieapp.datasource.NetworkMovieDataSource
import com.example.movieapp.network.api.MovieApi
import com.example.movieapp.network.NetworkModule
import com.example.movieapp.network.NetworkModuleImpl
import com.example.movieapp.network.api.ConfigurationApi
import org.koin.dsl.module

val appModule = module {
    single<MovieDataSource> { NetworkMovieDataSource(get()) }
    single<ConfigurationDataSource> { NetworkConfigurationDataSource(get()) }
}

val networkModule = module {
    single<NetworkModule> { NetworkModuleImpl() }

    single<MovieApi> { get<NetworkModule>().getMovieApi()}
    single<ConfigurationApi> { get<NetworkModule>().getConfigurationApi()}
}