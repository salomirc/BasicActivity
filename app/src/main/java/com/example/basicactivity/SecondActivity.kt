package com.example.basicactivity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

    private var isFavSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        toolbar.title = resources.getString(R.string.title_activity_second)
        setSupportActionBar(toolbar)
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        //getting the search view from the menu
        val searchViewItem = menu.findItem(R.id.menuSearch)

        //getting search manager from systemservice
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        //getting the search view
        val searchView = searchViewItem.actionView as SearchView

        //you can put a hint for the search input field
        searchView.queryHint = "Search Tutorials..."
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        //here we will get the search query
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //do the search here
                Toast.makeText(this@SecondActivity, "You are searching : $query", Toast.LENGTH_SHORT).show()
                searchView.onActionViewCollapsed()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_favorite -> {
                Toast.makeText(this, "You clicked favorite", Toast.LENGTH_SHORT).show()
                isFavSelected = !isFavSelected
                item.setIcon(if (isFavSelected) R.drawable.ic_favorite_red_24dp else R.drawable.ic_favorite_border_red_24dp)
                true
            }
            R.id.action_about -> {
                Toast.makeText(this, "You clicked about", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_logout -> {
                Toast.makeText(this, "You clicked logout", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
