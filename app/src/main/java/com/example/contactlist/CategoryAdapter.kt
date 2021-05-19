package com.example.contactlist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactlist.databinding.CategoryListItemBinding

 class CategoryAdapter (context: Context): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private val categories = mutableListOf<Category>()
     var  mContext : Context =  context

    inner class CategoryViewHolder (private var binding: CategoryListItemBinding) : RecyclerView.ViewHolder(binding.root){
        var cardview = binding.cardView
        fun bindItem(option:Category) {
            binding.categoryName.text = option.name
            Glide.with(mContext)
                .load(option.picture)
                .into(binding.categoryImage)
        }
    }
    fun setupCategory(category: List<Category>){
        this.categories.addAll(category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryListItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,
            false))

    }

    override fun getItemCount(): Int {
      return  categories.size

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bindItem(category)
        holder.cardview.setOnClickListener{
            var intent = Intent(mContext,MainActivity::class.java)
            intent.putExtra(Category,category.name)
            mContext.startActivity(intent)
        }

    }
     companion object{
        var Category = "CATEGORY"
     }
}