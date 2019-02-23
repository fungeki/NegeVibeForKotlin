package com.ranuskin.ranloock.zibro

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.ranuskin.ranloock.zibro.DB.Constructors.createChatChannel
import com.ranuskin.ranloock.zibro.Fragments.*
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatChannel
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatMessage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        nav_view.itemIconTintList = null

        bottom_nav_bar.selectedItemId = R.id.bot_nav_event_list
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

       // supportFragmentManager.beginTransaction().add(R.id.main_fragment_container, EventListFragment()).commit()
        nav_view.setNavigationItemSelectedListener(this)
        ViewCompat.setLayoutDirection(nav_view,ViewCompat.LAYOUT_DIRECTION_RTL)
        bottom_nav_bar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottom_nav_bar.itemIconTintList = null
        supportFragmentManager.beginTransaction().replace(R.id.fragments_container, EventListFragment()).commit()
        if (FirebaseAuth.getInstance().currentUser!!.isAnonymous){
            Toast.makeText(this, "שלום, אורח/ת",Toast.LENGTH_SHORT).show()
        } 
    }

    override fun onBackPressed() {
        when {
            drawer_layout.isDrawerOpen(GravityCompat.START) -> drawer_layout.closeDrawer(GravityCompat.START)
            supportFragmentManager.backStackEntryCount == 0 -> {

            }
            else -> super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        supportFragmentManager.beginTransaction().replace(R.id.fragments_container, AuthenticationFragment()).commit()
        supportActionBar!!.title = "חיבור / רישום"
        Toast.makeText(this, "לחצת על התראות, אין בנתיים",Toast.LENGTH_LONG).show()
        return true

//        when (item.itemId) {
//            R.id.action_settings -> return true
//            else -> return super.onOptionsItemSelected(item)
//        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        if(drawer_layout.isDrawerOpen(Gravity.RIGHT)){
            drawer_layout.closeDrawer(Gravity.RIGHT)
        }else{
            drawer_layout.openDrawer(Gravity.RIGHT)
        }
        when (item.itemId) {
            R.id.nav_favorites -> {
                // Handle the camera action
                supportActionBar!!.title = "מועדפים"
                supportFragmentManager.beginTransaction().replace(R.id.fragments_container, MyFavoritesListFragment()).commit()
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_signout -> {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(this, "התנתקנו, שמח?",Toast.LENGTH_SHORT).show()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.bot_nav_event_list -> {
                println("meow")
                supportFragmentManager.beginTransaction().replace(R.id.fragments_container, EventListFragment()).commit()
                supportActionBar!!.title = "אירועים"
                return@OnNavigationItemSelectedListener true
            }
            R.id.bot_nav_chats ->{
                supportFragmentManager.beginTransaction().replace(R.id.fragments_container, GeneralChatChannelsFragment()).commit()
                supportActionBar!!.title = "הצ׳אטים שלי"
                return@OnNavigationItemSelectedListener true
            }
            R.id.bot_nav_my_events -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragments_container, MyEventsListFragment()).commit()
                supportActionBar!!.title = "האירועים שלי"
                return@OnNavigationItemSelectedListener true
            }
            R.id.bot_nav_tickets ->{
                supportFragmentManager.beginTransaction().replace(R.id.fragments_container, MyTicketListFragment()).commit()
                supportActionBar!!.title = "הכרטיסים שלי"
                return@OnNavigationItemSelectedListener true
            }
            R.id.bot_nav_add_event->{
                supportFragmentManager.beginTransaction().replace(R.id.fragments_container,AddMyEventFragment()).commit()
                supportActionBar!!.title = "הוספת אירוע"
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

}


