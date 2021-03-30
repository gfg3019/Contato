package com.genisson.appcontato

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {
    private var contact: Contact? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_detail)
        initToolbar()
        getExtras()
        bindViews()
    }

    private fun getExtras(){
        contact = intent.getParcelableExtra(EXTRA_CONTACT)
    }
    private fun initToolbar(){
        setSupportActionBar(findViewById(R.id.toolbarId2))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun bindViews(){
        findViewById<TextView>(R.id.textName).text = contact?.nome
        findViewById<TextView>(R.id.textPhoto).text = contact?.phone

    }

    companion object{
        const val EXTRA_CONTACT: String = "EXTRA_CONTACT"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}