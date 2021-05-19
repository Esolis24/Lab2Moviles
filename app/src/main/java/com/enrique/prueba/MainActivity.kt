package com.enrique.prueba


import android.content.Context
import android.os.Bundle
import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.enrique.prueba.ui.perfil.PerfilFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    var isLogged=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment

        navController = navHostFragment.navController
        loadData()
       if(!isLogged) {
           val appBarConfiguration = AppBarConfiguration(setOf(
                   R.id.navigation_explorar, R.id.navigation_favoritos, R.id.navigation_reservas,
                   R.id.navigation_notificaciones, R.id.navigation_perfil))
           setupActionBarWithNavController(navController, appBarConfiguration)
       }
        else
       {

           val appBarConfiguration = AppBarConfiguration(setOf(
                   R.id.navigation_explorar, R.id.navigation_favoritos, R.id.navigation_reservas,
                   R.id.navigation_notificaciones, R.id.navigation_logout))
           setupActionBarWithNavController(navController, appBarConfiguration)
           navView.menu.removeItem(R.id.navigation_perfil)
           navView.menu.add(R.menu.bottom_nav_menu,R.id.navigation_logout,0,"perfil")
                   ?.setIcon(R.mipmap.ic_persona)?.isCheckable=true
       }
        navView.setupWithNavController(navController)
    }

    override fun onResume(){
        super.onResume();
        if (!isNetworkConnected()) {
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage(R.string.internet_connection)
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    private fun isNetworkConnected(): Boolean {
        //1
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //2
        val activeNetwork = connectivityManager.activeNetwork
        //3
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        //4
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()||super.onSupportNavigateUp()
    }

    private fun loadData(){
        val sharedPreferences=getSharedPreferences("user_login", Context.MODE_PRIVATE)
        val savedEmail=sharedPreferences?.getString("EMAIL_KEY",null)
        val savedPass=sharedPreferences?.getString("PASS_KEY",null)
        if(savedEmail!=null&&savedPass!=null)
          isLogged=true
    }
}