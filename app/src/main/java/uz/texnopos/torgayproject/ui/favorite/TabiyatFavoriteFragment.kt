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
import uz.texnopos.torgayproject.ui.detail.TabiyatDetailFragment
import uz.texnopos.torgayproject.ui.tabiyat.TabiyatListAdapter

class TabiyatFavoriteFragment : Fragment(R.layout.fragment_favorite),TorgayItemClickListener {

    private lateinit var dao: NationalBaseDao
    private lateinit var navController: NavController
    private val adapter = TabiyatListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
        navController = Navigation.findNavController(view)
        recyclerViewFav.addItemDecoration(MarginItemDecoration(16))
        recyclerViewFav.adapter = adapter
        setData()
    }

    private fun setData() {
        adapter.models = dao.getFavoriteTabiyat()
    }

    override fun onItemClickListener(id: Int) {
        val bundle = bundleOf(TabiyatDetailFragment.Tabiyat_Id to id)
        navController.navigate(R.id.action_menu_like_to_tabiyatFavoriteFragment,bundle)
    }
}