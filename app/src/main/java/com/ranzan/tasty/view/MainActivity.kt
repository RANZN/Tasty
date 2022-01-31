package com.ranzan.tasty.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ranzan.tasty.R
import com.ranzan.tasty.databinding.ActivityMainBinding
import com.ranzan.tasty.view.fragments.DiscoverFragment
import com.ranzan.tasty.view.fragments.RecipesFragment
import com.ranzan.tasty.viewmodel.TheViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 *
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //int i;
    //val i?=null
    private val viewModel by viewModels<TheViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.getData()
        
        //adding first fragment by default
        supportFragmentManager.beginTransaction().replace(R.id.fragContainer, DiscoverFragment()).commit()
        binding.bottomNavBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.discover -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragContainer, DiscoverFragment()).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.recipes -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragContainer, RecipesFragment()).commit()
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }
    }
}