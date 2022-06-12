package com.risingstar.talentaachiva.feature.management

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.ActivityManagementBinding

class ManagementActivity : AppCompatActivity() {
    private lateinit var viewmodel:ManagementVM
    private lateinit var binding: ActivityManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = intent.getStringExtra(CURRENT_USER)
        val event = intent.getStringExtra(CURRENT_EVENT)


        Log.i("Manage","$username and $event")

        viewmodel = ViewModelProvider(
            this,ManagementFactory(username!!, event!!)
        )[ManagementVM::class.java]
        binding = ActivityManagementBinding.inflate(layoutInflater)

        //Toast.makeText(this,"Welcome to $this, $username, $event", Toast.LENGTH_SHORT).show()
        setContentView(binding.root)



        val navView: BottomNavigationView = binding.navView
        setSupportActionBar(binding.tbManagement)
        val navController = findNavController(R.id.nav_host_management)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_people, R.id.navigation_stream, R.id.navigation_assignments))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    companion object {
        const val CURRENT_EVENT = "Fishing"
        const val CURRENT_USER = "Fish"
    }
}