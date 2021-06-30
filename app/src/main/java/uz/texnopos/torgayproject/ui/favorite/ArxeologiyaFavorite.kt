package uz.texnopos.torgayproject.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_favorite.*
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.ui.detail.HomeDetailFragment
import uz.texnopos.torgayproject.ui.home.HomeListAdapter

class ArxeologiyaFavorite : Fragment(R.layout.fragment_favorite),TorgayItemClickListener{

    lateinit var dao: NationalBaseDao
    private val homeAdapter = HomeListAdapter(this)
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        recyclerViewFav.addItemDecoration(MarginItemDecoration(16))
        recyclerViewFav.adapter = homeAdapter
        setData()
    }

    private fun setData(){
        homeAdapter.models = dao.getFavoriteArxeologiya()
    }

    override fun onItemClickListener(id: Int) {
        val bundle = bundleOf(HomeDetailFragment.Torgay_Id to id)
        navController.navigate(R.id.action_arxeologiyaFavorite_to_homeDetailFragment,bundle)

    }
}