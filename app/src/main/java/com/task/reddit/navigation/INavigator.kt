package com.task.reddit.navigation

import androidx.navigation.NavController

interface INavigator {

    fun setNavController(navController: NavController)

    fun open(screen: Int)

    fun goBack()

}