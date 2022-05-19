package com.example.movieapp.mock

import com.example.movieapp.models.movieoverview.MovieOverview
import java.util.*

object MockMovies {

    var movies = listOf<MovieOverview>()

    fun setup() {
        movies = listOf(
            MovieOverview(1, null, "cím 1", getDateFromYear(2010), 1f),
            MovieOverview(2, null, "cím 2", getDateFromYear(2011), 1f),
            MovieOverview(3, null, "cím 3", getDateFromYear(2012), 1f),
            MovieOverview(4, null, "cím 4", getDateFromYear(2013), 1f),
            MovieOverview(5, null, "cím 5", getDateFromYear(2014), 1f),
            MovieOverview(6, null, "cím 6", getDateFromYear(2015), 1f),
            MovieOverview(7, null, "cím 7", getDateFromYear(2016), 1f),
            MovieOverview(8, null, "cím 8", getDateFromYear(2017), 1f),
            MovieOverview(9, null, "cím 9", getDateFromYear(2018), 1f),
        )
    }

    private fun getDateFromYear(year: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, 1, 1)
        return calendar.time
    }

}