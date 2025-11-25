package com.kittiwuht.applicationformaesuphanneemooyor

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var mainToolbar: Toolbar
    lateinit var mainDrawerLayout: DrawerLayout
    lateinit var mainNavigationView: NavigationView
    lateinit var mainBottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainToolbar = findViewById(R.id.toolbar)
        mainDrawerLayout = findViewById(R.id.bottomNavigation)
        mainNavigationView = findViewById(R.id.main_navigation_view)
        mainBottomNavigationView = findViewById(R.id.main_bottom_navigation_view)

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.profitFragment,
            R.id.stockFragment
        ).setOpenableLayout(mainDrawerLayout).build()

        setSupportActionBar(mainToolbar)

        setupActionBarWithNavController(navController,appBarConfiguration)

        mainNavigationView.setupWithNavController(navController)

        mainBottomNavigationView.setupWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }
}