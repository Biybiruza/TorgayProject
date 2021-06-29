package uz.texnopos.torgayproject.ui.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_view_pager.*
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.data.model.ModelType
import uz.texnopos.torgayproject.ui.detail.HomeDetailFragment
import uz.texnopos.torgayproject.ui.detail.MilliyDetailFragment
import uz.texnopos.torgayproject.ui.detail.MuzeyDetailFragment
import uz.texnopos.torgayproject.ui.detail.TabiyatDetailFragment

class FavoriteFragment : Fragment(R.layout.fragment_view_pager),TorgayItemClickListener {

    lateinit var dao: NationalBaseDao
    private val adapter = FragmentListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolBarAction.title = "Saylandilar"
        recyclerViewFavorite.addItemDecoration(MarginItemDecoration(16))
        recyclerViewFavorite.adapter = adapter

        arxeologiyaBtn.setOnClickListener {
            setData(ModelType.ARXEOLOGIYA_TYPE)
            Toast.makeText(requireContext(),"dsgjfg",Toast.LENGTH_LONG).show()
        }
        MilliyBtn.setOnClickListener {
            setData(ModelType.ARXEOLOGIYA_TYPE)
        }
        muzeyBtn.setOnClickListener {

        }
        tabiyatBtn.setOnClickListener {

        }
    }

    private fun setData(type: Int){
        if (type == 1) {
            adapter.models = dao.getFavoriteArxeologiya()
        }else if (type == 2){
            adapter.models = dao.getFavoriteNational()
        }
    }

    override fun onItemClickListener(id: Int,type: Int) {
        when (type) {
            1 -> {
                val fragmentHome = HomeDetailFragment()
                val bundle = Bundle()
                bundle.putInt(HomeDetailFragment.Torgay_Id, id)
                fragmentHome.arguments = bundle
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, fragmentHome)
                    .addToBackStack(HomeDetailFragment::class.simpleName).commit()
            }
            2 -> {
                val milliyFragment = MilliyDetailFragment()
                val bundle = Bundle()
                bundle.putInt(MilliyDetailFragment.MILLIY_ID, id)
                milliyFragment.arguments = bundle
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, milliyFragment)
                    .addToBackStack(MilliyDetailFragment::class.simpleName).commit()
            }
            3 -> {
                val fragmentMuzey = MuzeyDetailFragment()
                val bundle = Bundle()
                bundle.putInt(MuzeyDetailFragment.Muzey_Id, id)
                fragmentMuzey.arguments = bundle
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, fragmentMuzey)
                    .addToBackStack(MuzeyDetailFragment::class.simpleName).commit()
            }
            4 -> {
                val tabiyatFragment = TabiyatDetailFragment()
                val bundle = Bundle()
                bundle.putInt(TabiyatDetailFragment.Tabiyat_Id, id)
                tabiyatFragment.arguments = bundle
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, tabiyatFragment)
                    .addToBackStack(TabiyatDetailFragment::class.simpleName).commit()
            }
        }
    }
}