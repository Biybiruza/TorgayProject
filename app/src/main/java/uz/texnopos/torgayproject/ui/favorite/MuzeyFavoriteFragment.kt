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
import uz.texnopos.torgayproject.ui.detail.MuzeyDetailFragment
import uz.texnopos.torgayproject.ui.muzey.MuzeyListAdapter

class MuzeyFavoriteFragment : Fragment(R.layout.fragment_favorite),TorgayItemClickListener{

    private lateinit var dao: NationalBaseDao
    private val adapter = MuzeyListAdapter(this)
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
        navController = Navigation.findNavController(view)
        recyclerViewFav.addItemDecoration(MarginItemDecoration(16))
        recyclerViewFav.adapter = adapter
        setData()
    }

    private fun setData(){
        adapter.models = dao.getFavoriteMuzey()
    }

    override fun onItemClickListener(id: Int) {
        val bundle = bundleOf(MuzeyDetailFragment.Muzey_Id to id)
        navController.navigate(R.id.action_menu_like_to_muzeyFavoriteFragment,bundle)
    }

}