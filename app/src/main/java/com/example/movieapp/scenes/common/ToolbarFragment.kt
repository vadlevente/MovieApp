package com.example.movieapp.scenes.common

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.google.android.material.navigation.NavigationView

abstract class ToolbarFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val drawerLayout = view.findViewById<DrawerLayout>(R.id.drawerLayout)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.popularMoviesFragment, R.id.searchMovieFragment), drawerLayout)

        view.findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)
        view.findViewById<NavigationView>(R.id.navView)
            ?.setupWithNavController(navController)
    }

}