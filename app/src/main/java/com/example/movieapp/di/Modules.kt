package com.example.movieapp.di

import com.example.movieapp.datasource.MovieDataSource
import com.example.movieapp.datasource.NetworkMovieDataSource
import com.example.movieapp.network.api.MovieApi
import com.example.movieapp.network.NetworkModule
import com.example.movieapp.network.NetworkModuleImpl
import org.koin.dsl.module

val appModule = module {
    single<MovieDataSource> { NetworkMovieDataSource(get()) }
}

val networkModule = module {
    single<NetworkModule> { NetworkModuleImpl() }

    single<MovieApi> { get<NetworkModule>().getMovieApi()}
}