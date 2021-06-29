package uz.texnopos.torgayproject

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import uz.texnopos.torgayproject.data.model.ModelType
import uz.texnopos.torgayproject.ui.favorite.FavoriteFragment

class MainActivity : AppCompatActivity() {

    companion object{
        const val DATA_TYPE = "data_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val fragment = FragmentView()
        val bundle = Bundle()
        bundle.putInt(DATA_TYPE,ModelType.ARXEOLOGIYA_TYPE)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(FragmentView::class.java.simpleName + "$")
            .commit()
        navView.setOnNavigationItemSelectedListener {
            val mFragment = FragmentView()
            val mBundle = Bundle()
            when (it.itemId) {
                R.id.menu_home -> {
                    mBundle.putInt(DATA_TYPE,ModelType.ARXEOLOGIYA_TYPE)
                    mFragment.arguments = mBundle
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, mFragment)
                        .addToBackStack(FragmentView::class.java.simpleName + "$")
                        .commit()
                }
                R.id.menu_museum -> {
                    mBundle.putInt(DATA_TYPE,ModelType.MUZEY_TYPE)
                    mFragment.arguments = mBundle
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, mFragment)
                        .addToBackStack(FragmentView::class.java.simpleName + "$")
                        .commit()
                }
                R.id.menu_nature -> {
                    mBundle.putInt(DATA_TYPE,ModelType.TABIYAT_TYPE)
                    mFragment.arguments = mBundle
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, mFragment)
                        .addToBackStack(FragmentView::class.java.simpleName + "$")
                        .commit()
                }
                R.id.menu_national -> {
                    mBundle.putInt(DATA_TYPE,ModelType.MILLIY_TYPE)
                    mFragment.arguments = mBundle
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, mFragment)
                        .addToBackStack(FragmentView::class.java.simpleName + "$")
                        .commit()
                }
                R.id.menu_like -> {
                    mBundle.putInt(DATA_TYPE,ModelType.ARXEOLOGIYA_TYPE)
                    mFragment.arguments = mBundle
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
//            if (supportFragmentManager.backStackEntryCount==2 &&
//                supportFragmentManager.getBackStackEntryAt(0).name.toString() == HomeFragment::class.simpleName+"$" &&
//                supportFragmentManager.getBackStackEntryAt(1).name.toString() == HomeFragment::class.simpleName+"$") {
//                finish()
//                return
//            }
//            supportFragmentManager
//                .popBackStackImmediate(
//                    supportFragmentManager.getBackStackEntryAt(1).id,
//                    FragmentManager.POP_BACK_STACK_INCLUSIVE
//                )
            if (supportFragmentManager.backStackEntryCount == 1 && nav_view.selectedItemId != R.id.menu_home) {
                nav_view.selectedItemId = R.id.menu_home
            }
        } else {
            supportFragmentManager.popBackStackImmediate()
        }
    }
}