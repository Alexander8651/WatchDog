package com.andromoticaia.watchingdog.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.andromoticaia.watchingdog.R
import com.andromoticaia.watchingdog.data.DataSource
import com.andromoticaia.watchingdog.domain.RepositoryImpl
import com.andromoticaia.watchingdog.viewmodel.VMFactory
import com.andromoticaia.watchingdog.viewmodel.ViewModelMainFragment

class MainActivity : AppCompatActivity() {

    //declare appconfiguration
    lateinit var appBarConfiguration: AppBarConfiguration

    //declare navCOntroller
    lateinit var navController:NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ini navController
        navController = findNavController(R.id.fragmentNavigation)

        //set the action
        NavigationUI.setupActionBarWithNavController(this, navController)

        //init the appbarconfiguration
        appBarConfiguration = AppBarConfiguration(navController.graph)


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }
}