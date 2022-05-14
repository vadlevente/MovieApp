package com.example.movieapp.di

import com.example.movieapp.datasource.*
import com.example.movieapp.network.api.MovieApi
import com.example.movieapp.network.NetworkModule
import com.example.movieapp.network.NetworkModuleImpl
import com.example.movieapp.network.api.ConfigurationApi
import com.example.movieapp.network.api.SearchApi
import org.koin.dsl.module

val appModule = module {
    single<MovieDataSource> { NetworkMovieDataSource(get()) }
    single<ConfigurationDataSource> { NetworkConfigurationDataSource(get()) }
    single<SearchDataSource> { NetworkSearchDataSource(get()) }
}

val networkModule = module {
    single<NetworkModule> { NetworkModuleImpl() }

    single<MovieApi> { get<NetworkModule>().getMovieApi()}
    single<ConfigurationApi> { get<NetworkModule>().getConfigurationApi()}
    single<SearchApi> { get<NetworkModule>().getSearchApi()}
}