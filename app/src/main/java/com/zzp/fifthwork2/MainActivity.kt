package com.zzp.fifthwork2

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val contactsList = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initContacts()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = ContactAdapter(contactsList)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this,
            DividerItemDecoration.VERTICAL)
        )
    }

    private fun initContacts() {
        val uri = Uri.parse("content://com.zzp.myprovider.provider/contact")
        contentResolver.query(uri, null,
            null, null, null)?.apply {
            while (moveToNext()) {
                val name = getString(getColumnIndex("name"))
                val number = getString(getColumnIndex("number"))
                val contact = Contact(name, number)
                if (!contactsList.contains(contact)) {
                    contactsList.add(contact)
                }
            }
            close()
        }
    }
}