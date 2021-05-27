package uz.texnopos.torgayproject
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import uz.texnopos.torgayproject.ui.muzey.MuzeyFragment
import uz.texnopos.torgayproject.ui.tabiyat.TabiyatFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        /*supportActionBar?.setTitle("Arxeologiyaliq estelikler")
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment())
            .commit()*/
        navView.setOnNavigationItemSelectedListener {
            val myBundle = Bundle()
            var myFragment = Fragment()
            when (it.itemId) {
                R.id.menu_home -> {
                    supportActionBar?.setTitle("Arxeologiyaliq estelikler")
                }
                R.id.menu_museum -> {
                    supportActionBar?.setTitle("Muzeyler")
                    myFragment = MuzeyFragment()
                }
                R.id.menu_nature -> {
                    supportActionBar?.setTitle("Tabiyat")
                    myFragment = TabiyatFragment()
                }
                R.id.menu_national -> {
                    supportActionBar?.setTitle("Milliy")

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
    fun setDisplayHomeAsUpEnabled(boolean: Boolean){
        supportActionBar?.setDisplayHomeAsUpEnabled(boolean)
    }

    fun setActionBarTitle(string: String?){
        supportActionBar?.title = string
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0){
            finish()
        }else{
            supportFragmentManager.popBackStackImmediate(supportFragmentManager.getBackStackEntryAt(0).id,
            FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}
