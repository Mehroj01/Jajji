package com.example.jajji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

import androidx.navigation.findNavController
import com.example.jajji.R
import com.example.jajji.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomappbar.BottomAppBar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

        }
    }

    fun hideBottom() {
        val btm = findViewById<BottomAppBar>(R.id.btmBar)
        btm.visibility = View.GONE
    }

    fun showBottom() {
        val btm = findViewById<BottomAppBar>(R.id.btmBar)
        btm.visibility = View.VISIBLE
    }

    override fun onNavigateUp(): Boolean = findNavController(R.id.nav_controller).navigateUp()

}