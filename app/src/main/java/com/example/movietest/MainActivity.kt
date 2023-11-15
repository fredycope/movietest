package com.example.movietest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movietest.databinding.ActivityMainBinding
import com.example.movietest.ui.galery.GaleryFragment
import com.example.movietest.ui.maps.MapFragment
import com.example.movietest.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        setCurrentFragment(ProfileFragment())
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    setCurrentFragment(ProfileFragment())
                    true
                }
                R.id.page_2 -> {
                    setCurrentFragment(com.example.movietest.ui.list.ListFragment())
                    true
                }
                R.id.page_3 -> {
                    setCurrentFragment(MapFragment())
                    true
                }
                else -> {
                    setCurrentFragment(GaleryFragment())
                    true
                }
            }
        }
    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}