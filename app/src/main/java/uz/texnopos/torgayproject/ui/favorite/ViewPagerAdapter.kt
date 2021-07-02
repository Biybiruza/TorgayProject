package uz.texnopos.torgayproject.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 4

    lateinit var parentNavControllerFragment: NavController

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ArxeologiyaFavorite(parentNavControllerFragment)
            1 -> NationalFavoriteFragment(parentNavControllerFragment)
            2 -> MuzeyFavoriteFragment(parentNavControllerFragment)
            else -> TabiyatFavoriteFragment(parentNavControllerFragment)
        }
    }
}