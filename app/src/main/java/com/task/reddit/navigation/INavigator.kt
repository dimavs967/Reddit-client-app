package com.task.reddit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

interface INavigator {

    fun setNavController(navController: NavController)

    fun open(navDirections: NavDirections)

    fun goBack()

}