package com.example.contactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlist.databinding.ActivityCategoryBinding
import com.example.contactlist.databinding.ActivityMainBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCategoryBinding
    private val myAdapter = CategoryAdapter(this)
    private val categories = Categories()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setSupportActionBar(binding.appBar)
        setupData(binding)
    }
    private fun setupData (binding: ActivityCategoryBinding){

        binding.categoryRv.apply {
            layoutManager = GridLayoutManager(context,2)
            myAdapter.setupCategory(categories.categoryList)
            adapter = myAdapter
        }
    }
}