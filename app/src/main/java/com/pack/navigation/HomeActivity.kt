package com.pack.navigation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.infodart.kenstar_crm.ClickListener
import com.infodart.kenstar_crm.NavigationItemModel
import com.infodart.kenstar_crm.DrawerItemAdapter
import com.infodart.kenstar_crm.RecyclerTouchListener
import com.pack.navigation.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var context: Context = this@HomeActivity


    private lateinit var adapter: DrawerItemAdapter

    private var items = arrayListOf(
        NavigationItemModel(R.drawable.ic_menu_home, "Home"),
        NavigationItemModel(R.drawable.ic_menu_call, "Calls"),
        NavigationItemModel(R.drawable.ic_menu_attendance, "Attendance"),
        NavigationItemModel(R.drawable.ic_menu_leave, "Leave"),
        NavigationItemModel(R.drawable.ic_menu_setting, "Settings"),
        NavigationItemModel(R.drawable.ic_logout, "Logout"),

        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setupToolbar("Home")

        // @@@@@@@@@@@@@@@@@@@@@@
        binding.toolbarLayout.img.setOnClickListener {
            // Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        // Setup Recyclerview's Layout
        binding.navigationRv.layoutManager = LinearLayoutManager(this)
        binding.navigationRv.setHasFixedSize(true)

        // Add Item Touch Listener
        binding.navigationRv.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        showToastMsg("Home");
                    }

                    1 -> {
                        showToastMsg("Calls");
                    }

                    2 -> {
                        showToastMsg("Attendance");
                    }

                    3 -> {
                        showToastMsg("Leave");
                    }

                    4 -> {
                        showToastMsg("Setting");
                    }

                    5 -> {
                        showToastMsg("Logout");
                    }


                }
                updateAdapter(position)

                Handler().postDelayed({
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }, 200)
            }
        }))

        updateAdapter(0)

        // Close the soft keyboard when you open or close the Drawer
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this@HomeActivity,
            binding.drawerLayout, R.string.open, R.string.close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                // Triggered once the drawer closes
                super.onDrawerClosed(drawerView)
                hideSoftKeyboard()
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                hideSoftKeyboard()
            }
        }
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navigationLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.white))


        // @@@@@@@@@@@@@@@@@@@@@@
    }


    private fun updateAdapter(highlightItemPos: Int) {
        adapter = DrawerItemAdapter(items, highlightItemPos)
        binding.navigationRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            // Checking for fragment count on back stack
            super.onBackPressed()
        }
    }


    private fun setupToolbar(title: String) {
        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        val titleTv = findViewById<TextView>(R.id.text)
        val img = findViewById<ImageView>(R.id.img)
        //img.setImageDrawable(YOUR_DRAWABLE)
        titleTv.text = title
        setSupportActionBar(mToolbar)
    }

    fun showToastMsg(message: String) {
        Toast.makeText(context, "$message Clicked!", Toast.LENGTH_SHORT).show()
    }

    fun hideSoftKeyboard() {
        try {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        } catch (e: Exception) {
            e.stackTrace
        }
    }

}