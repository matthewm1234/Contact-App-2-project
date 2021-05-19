package com.example.contactlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.databinding.ContactListItemBinding

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private val contacts = mutableListOf<Contact>()

    inner class ContactViewHolder (private var binding: ContactListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindItem(contact:Contact) {
            binding.nameTv.text = contact.name
            binding.numberTv.text= contact.number
        }
    }
    fun setupContacts(contact: List<Contact>){
        this.contacts.addAll(contact)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
                ContactListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bindItem(contact)
    }
}