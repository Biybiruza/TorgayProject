package uz.texnopos.torgayproject.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_fav.*
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.ui.detail.HomeDetailFragment
import uz.texnopos.torgayproject.ui.detail.MilliyDetailFragment
import uz.texnopos.torgayproject.ui.national.NationalListAdapter

class NationalFavoriteFragment() : Fragment(R.layout.fragment_fav), TorgayItemClickListener {
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

    fun setData(){
        adapter.models = dao.getFavoriteNational()
    }

    override fun onItemClickListener(id: Int) {
        val fragmentMilliy = MilliyDetailFragment()
        val bundle = Bundle()
        bundle.putInt(HomeDetailFragment.Torgat_Id, id)
        fragmentMilliy.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragmentMilliy)
            .addToBackStack(MilliyDetailFragment::class.simpleName).commit()
    }
}