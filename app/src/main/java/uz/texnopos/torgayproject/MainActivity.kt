package uz.texnopos.torgayproject

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import uz.texnopos.torgayproject.ui.home.HomeFragment
import uz.texnopos.torgayproject.ui.national.NationalFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        supportActionBar?.setTitle("Arxeologiyaliq estelikler")
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment())
            .commit()
        navView.setOnNavigationItemSelectedListener {
            val myBundle = Bundle()
            var myFragment = Fragment()
            when (it.itemId) {
                R.id.menu_home -> {
                    supportActionBar?.setTitle("Arxeologiyaliq estelikler")
                    myFragment = HomeFragment()
                }
                R.id.menu_museum -> {
                    supportActionBar?.setTitle("Muzeyler")
                }
                R.id.menu_nature -> {
                    supportActionBar?.setTitle("Tabiyat")
                }
                R.id.menu_national -> {
                    supportActionBar?.setTitle("Milliy")
                    myFragment = NationalFragment()
                }
                R.id.menu_like -> {
                    supportActionBar?.setTitle("Saylandilar")
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
            supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, myFragment)
                .commit()
            return@setOnNavigationItemSelectedListener true

        }

    }
}