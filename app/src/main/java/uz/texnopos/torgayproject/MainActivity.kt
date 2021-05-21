package uz.texnopos.torgayproject

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View.inflate
import android.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment())
            .commit()
        navView.setOnNavigationItemSelectedListener {
            val myBundle = Bundle()
            var myFragment = Fragment()
            when (it.itemId) {
                R.id.menu_home -> {
                    myFragment = HomeFragment()
                }
                R.id.menu_museum -> {
                    supportActionBar?.setTitle("Muzeyler")
                }
                R.id.menu_nature -> {
                    supportActionBar?.setTitle("Tabiyat")

                }
                R.id.menu_national -> {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            //Title bar back press triggers onBackPressed()
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0){
            finish()
        } else {
            supportFragmentManager.popBackStackImmediate(supportFragmentManager.getBackStackEntryAt(0).id,
                FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

}