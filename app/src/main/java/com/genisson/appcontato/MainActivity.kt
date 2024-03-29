package com.genisson.appcontato

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.genisson.appcontato.DetailActivity.Companion.EXTRA_CONTACT
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), ClickItemContactListener {
    private val rvList: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_list)
    }
    private val adapter = ContactAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)
        initDrawer()
        fetchListContact()
        bindViews()
    }

    private fun fetchListContact(){
        val list = arrayListOf(
                Contact("Genisson Ferreira", "(00) 0000-0000", "img.png"),
                Contact("Genisson Ferreira", "(00) 0000-0000", "img.png")
        )
        getInstanceSharedPreferences().edit{
            putString("contacts", Gson().toJson(list))
            commit()
        }
    }

    private fun getInstanceSharedPreferences(): SharedPreferences{
        return getSharedPreferences("com.genisson.appcontato.PREFERENCES", Context.MODE_PRIVATE)
    }

   private fun initDrawer(){
        val drawerlayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        setSupportActionBar(findViewById(R.id.toobarId))
        val toggle = ActionBarDrawerToggle(this, drawerlayout, findViewById(R.id.toobarId), R.string.open_drawer, R.string.close_drawer)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun bindViews(){
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        updateList()
    }
    private fun getListContacts(): List<Contact>{
        val list = getInstanceSharedPreferences().getString("contacts", "[]")
        val turnsType = object: TypeToken<List<Contact>>(){}.type
        return Gson().fromJson(list, turnsType)
    }
    private fun updateList(){
        adapter.updateList(getListContacts())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_menu1 -> {
                showToast("Exibindo item de menu 1")
                true
            }
            R.id.item_menu2 -> {
                showToast("Exibindo item de menu 2")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun clickItemContact(contact: Contact) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }
}