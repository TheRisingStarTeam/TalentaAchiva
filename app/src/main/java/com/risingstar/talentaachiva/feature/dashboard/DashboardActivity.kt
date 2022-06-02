package com.risingstar.talentaachiva.feature.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDashboardBinding
    private lateinit var viewmodel : DebugVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        findViewById<NavigationView>(R.id.nav_view)
//            .setupWithNavController(navController)
//
//        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)



        binding = ActivityDashboardBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this,DebugFactory())[DebugVM::class.java]

        setContentView(binding.root)
    }
}