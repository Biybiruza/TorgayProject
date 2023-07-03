package uz.texnopos.torgayproject

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
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

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

    }

}