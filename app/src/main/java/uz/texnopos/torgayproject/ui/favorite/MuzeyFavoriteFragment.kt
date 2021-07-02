package uz.texnopos.torgayproject.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_favorite.*
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.ui.detail.MuzeyDetailFragment
import uz.texnopos.torgayproject.ui.muzey.MuzeyListAdapter

class MuzeyFavoriteFragment(private val mParentNavController: NavController) : Fragment(R.layout.fragment_favorite),TorgayItemClickListener{

    private lateinit var dao: NationalBaseDao
    private val adapter = MuzeyListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
        recyclerViewFav.addItemDecoration(MarginItemDecoration(16))
        recyclerViewFav.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        setData()
    }

    private fun setData(){
        adapter.models = dao.getFavoriteMuzey()
    }

    override fun onItemClickListener(id: Int) {
        val bundle = bundleOf(MuzeyDetailFragment.Muzey_Id to id)
        mParentNavController.navigate(R.id.action_menu_like_to_muzeyDetailFragment, bundle)
    }

}