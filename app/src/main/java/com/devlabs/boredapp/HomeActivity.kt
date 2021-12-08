package com.devlabs.boredapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devlabs.activities.fragment.AddActivityFragment
import com.devlabs.activities.fragment.ListActivitiesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val firstFragment = AddActivityFragment()
        val secondFragment = ListActivitiesFragment()

        setCurrentFragment(firstFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.add_activity-> setCurrentFragment(firstFragment)
                R.id.list_activity-> setCurrentFragment(secondFragment)

            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}