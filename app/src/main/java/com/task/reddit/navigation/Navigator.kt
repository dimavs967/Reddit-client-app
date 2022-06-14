package com.task.reddit.navigation

import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor(): INavigator {

    private var navController: NavController? = null

    override fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun open(screen: Int) {
        try {
            navController?.navigate(screen)
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