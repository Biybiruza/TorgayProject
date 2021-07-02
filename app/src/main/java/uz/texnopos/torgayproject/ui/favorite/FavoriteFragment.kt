package uz.texnopos.torgayproject.ui.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_view_pager.*
import uz.texnopos.torgayproject.R

class FavoriteFragment : Fragment(R.layout.fragment_view_pager) {
    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        toolBarAction.title = "Saylandılar"
        val adapter =
            ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        adapter.parentNavControllerFragment = navController!!
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout,viewPager){ tab, position ->
            when(position){
                0 -> {tab.text = "Arxeologiya"}
                1 -> {tab.text = "Milliy"}
                2 -> tab.text = "Muzey"
                3 -> tab.text = "Tábiyat"
            }
        }.attach()
        toolBarAction.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.about -> {
                    navController!!.navigate(R.id.action_menu_like_to_infoFragment)
                    // do something
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

}