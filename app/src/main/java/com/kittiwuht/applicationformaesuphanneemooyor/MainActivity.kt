package com.kittiwuht.applicationformaesuphanneemooyor

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    // ประกาศตัวแปรสำหรับ Navigation
    private lateinit var navController: NavController
    private lateinit var mainBottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ตั้งค่า Layout หลักของ Activity
        setContentView(R.layout.fragment_activity_main)

        // จัดการ System Bars (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.homeFragment)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // **สำคัญ**: หา NavHostFragment และดึง NavController ออกมา
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // เชื่อม Bottom Navigation กับ Navigation Controller
        mainBottomNavigationView = findViewById(R.id.main_bottom_navigation_view)
        mainBottomNavigationView.setupWithNavController(navController)
    }
}