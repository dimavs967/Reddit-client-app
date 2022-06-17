package com.task.reddit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() : INavigator {

    private var navController: NavController? = null

    override fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun open(navDirections: NavDirections) {
        try {
            navController?.navigate(navDirections)
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }
    }

    override fun goBack() {
        if (navController?.previousBackStackEntry?.destination != null) {
            navController?.popBackStack()
        }
    }

}