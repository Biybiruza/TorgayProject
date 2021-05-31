package uz.texnopos.torgayproject

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import uz.texnopos.torgayproject.ui.home.HomeFragment
import uz.texnopos.torgayproject.ui.muzey.MuzeyFragment
import uz.texnopos.torgayproject.ui.national.NationalFragment
import uz.texnopos.torgayproject.ui.tabiyat.TabiyatFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment())
            .addToBackStack(HomeFragment::class.java.simpleName)
            .commit()
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    if (supportFragmentManager.backStackEntryCount > 1)
                    supportFragmentManager
                        .popBackStack(supportFragmentManager.getBackStackEntryAt(1).id,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment())
                      //  .addToBackStack(HomeFragment::class.java.simpleName)
                        .commit()
                }
                R.id.menu_museum -> {
                    if (supportFragmentManager.backStackEntryCount > 1)
                    supportFragmentManager.popBackStackImmediate(supportFragmentManager.getBackStackEntryAt(1).id,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, MuzeyFragment())
                        .addToBackStack(MuzeyFragment::class.java.simpleName)
                        .commit()
                }
                R.id.menu_nature -> {
                    if (supportFragmentManager.backStackEntryCount > 1)
                    supportFragmentManager.popBackStackImmediate(supportFragmentManager.getBackStackEntryAt(1).id,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, TabiyatFragment())
                        .addToBackStack(TabiyatFragment::class.java.simpleName)
                        .commit()
                }
                R.id.menu_national -> {
                    if (supportFragmentManager.backStackEntryCount > 1)
                    supportFragmentManager.popBackStackImmediate(supportFragmentManager.getBackStackEntryAt(1).id,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, NationalFragment())
                        .addToBackStack(NationalFragment::class.java.simpleName)
                        .commit()
                }
                R.id.menu_like -> {
                    supportActionBar?.setTitle("Saylandilar")
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1){
            finish()
        } else {
            supportFragmentManager.popBackStackImmediate()
            if (supportFragmentManager.backStackEntryCount == 1) {
                if (nav_view.selectedItemId != R.id.menu_home)
                nav_view.selectedItemId = R.id.menu_home
            }
        }
    }
}