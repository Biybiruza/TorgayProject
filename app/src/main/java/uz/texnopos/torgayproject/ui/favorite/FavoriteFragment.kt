package uz.texnopos.torgayproject.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_view_pager.*
import uz.texnopos.torgayproject.R

class FavoriteFragment : Fragment(R.layout.fragment_view_pager) {

    lateinit var adapter : ViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewPager.adapter = adapter
        toolBarAction.title = "Saylandılar"
        TabLayoutMediator(tabLayout,viewPager){ tab, position ->
            when(position){
                0 -> {tab.text = "Arxeologiya"}
                1 -> {tab.text = "Milliy"}
            }
        }.attach()
    }
}