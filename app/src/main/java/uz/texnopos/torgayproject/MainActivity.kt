package uz.texnopos.torgayproject

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import uz.texnopos.torgayproject.ui.favorite.ArxeologiyaFavorite
import uz.texnopos.torgayproject.ui.favorite.FavoriteFragment
import uz.texnopos.torgayproject.ui.home.HomeFragment
import uz.texnopos.torgayproject.ui.muzey.MuzeyFragment
import uz.texnopos.torgayproject.ui.national.MilliyFragment
import uz.texnopos.torgayproject.ui.tabiyat.TabiyatFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment())
            .addToBackStack(HomeFragment::class.java.simpleName + "$")
            .commit()
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment())
                        .addToBackStack(HomeFragment::class.java.simpleName + "$")
                        .commit()
                }
                R.id.menu_museum -> {
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, MuzeyFragment())
                        .addToBackStack(MuzeyFragment::class.java.simpleName + "$")
                        .commit()
                }
                R.id.menu_nature -> {
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, TabiyatFragment())
                        .addToBackStack(TabiyatFragment::class.java.simpleName + "$")
                        .commit()
                }
                R.id.menu_national -> {
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, MilliyFragment())
                        .addToBackStack(MilliyFragment::class.java.simpleName + "$")
                        .commit()
                }
                R.id.menu_like -> {
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,FavoriteFragment())
                        .addToBackStack(FavoriteFragment::class.simpleName + "$")
                        .commit()
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun onBackPressedFav(){
        if(supportFragmentManager.backStackEntryCount == 0){
            onBackPressed()
        } else {
            supportFragmentManager.popBackStack(supportFragmentManager.getBackStackEntryAt(0).id,
                FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else if (
            supportFragmentManager
                .getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
                .name.toString().endsWith("$")
        ) {
            if (supportFragmentManager.backStackEntryCount==2 &&
                supportFragmentManager.getBackStackEntryAt(0).name.toString() == HomeFragment::class.simpleName+"$" &&
                supportFragmentManager.getBackStackEntryAt(1).name.toString() == HomeFragment::class.simpleName+"$") {
                finish()
                return
            }
            supportFragmentManager
                .popBackStackImmediate(
                    supportFragmentManager.getBackStackEntryAt(1).id,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
            if (supportFragmentManager.backStackEntryCount == 1 && nav_view.selectedItemId != R.id.menu_home) {
                nav_view.selectedItemId = R.id.menu_home
            }
        } else {
            supportFragmentManager.popBackStackImmediate()
        }
    }
}