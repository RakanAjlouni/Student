package com.rakanajlouni.student.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.rakanajlouni.student.R
import com.rakanajlouni.student.views.fragments.*
import kotlinx.android.synthetic.main.activity_host.*

class HostActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.SearchProjects, R.id.Uploadproject, R.id.PurchasedProjects,
                R.id.MyProjects, R.id.SettingsFragment, R.id.logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.SearchProjects -> {
                    LoadFragment(SearchProjects())
                    toolbar.setTitle(getString(R.string.SearchProjects))
                }
                R.id.Uploadproject -> {
                    LoadFragment(UploadProjects())
                    toolbar.setTitle(getString(R.string.Uploadaproject))
                }
                R.id.PurchasedProjects -> {
                    LoadFragment(PurchasedProjects())
                    toolbar.setTitle(getString(R.string.PurchasedProjects))
                }
                R.id.MyProjects -> {
                    LoadFragment(MyProjects())
                    toolbar.setTitle(getString(R.string.MyProjects))
                }
                R.id.SettingsFragment -> {
                    LoadFragment(SettingsFragment())
                    toolbar.setTitle(getString(R.string.SettingsFragment))
                }
                R.id.logout -> {
                    getSharedPreferences("user_data", MODE_PRIVATE).edit().clear().apply()
                    var intent = Intent(this ,SplashPage::class.java)
                    startActivity(intent)
                }
            }

            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    fun LoadFragment(fragment:Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.host, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
