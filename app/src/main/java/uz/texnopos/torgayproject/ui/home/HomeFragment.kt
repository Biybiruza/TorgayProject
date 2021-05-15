package uz.texnopos.torgayproject.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_milliy.*
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.data.model.Home

class HomeFragment: Fragment(R.layout.fragment_milliy) {

    private val homeAdapter = HomeListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nationalHomeRecyclerView.adapter = homeAdapter
        setData()

    }

    fun setData(){
        val homeModels = mutableListOf<Home>()
        for (i in 1..8){
            homeModels.add(Home(i))
        }
        homeAdapter.models = homeModels
    }

}