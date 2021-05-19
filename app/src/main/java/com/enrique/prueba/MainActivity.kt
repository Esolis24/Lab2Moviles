package com.enrique.prueba

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.enrique.prueba.ui.perfil.PerfilFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
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