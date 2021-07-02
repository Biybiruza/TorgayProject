package uz.texnopos.torgayproject.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_favorite.*
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.ui.detail.MilliyDetailFragment
import uz.texnopos.torgayproject.ui.national.NationalListAdapter

class NationalFavoriteFragment(private val parentNavController: NavController) : Fragment(R.layout.fragment_favorite), TorgayItemClickListener {
    lateinit var dao: NationalBaseDao
    private val adapter = NationalListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewFav.addItemDecoration(MarginItemDecoration(16))
        recyclerViewFav.adapter = adapter
        setData()
    }

    private fun setData(){
        adapter.models = dao.getFavoriteNational()
    }

    override fun onItemClickListener(id: Int) {
        val bundle = bundleOf(MilliyDetailFragment.MILLIY_ID to id)
        parentNavController.navigate(R.id.action_menu_like_to_milliyDetailFragment,bundle)
    }
}