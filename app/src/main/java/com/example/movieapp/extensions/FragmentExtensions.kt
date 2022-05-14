package com.example.movieapp.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.movieapp.R

val Fragment.navController: NavController?
    get() {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as? NavHostFragment
        return navHostFragment?.navController
    }