package uz.texnopos.torgayproject

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_milliy.*
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.data.model.*
import uz.texnopos.torgayproject.ui.detail.HomeDetailFragment
import uz.texnopos.torgayproject.ui.detail.MilliyDetailFragment
import uz.texnopos.torgayproject.ui.detail.MuzeyDetailFragment
import uz.texnopos.torgayproject.ui.detail.TabiyatDetailFragment
import uz.texnopos.torgayproject.ui.favorite.FragmentListAdapter
import uz.texnopos.torgayproject.ui.info.InfoFragment

class FragmentView :Fragment(R.layout.fragment_milliy),TorgayItemClickListener{

    private val adapter = FragmentListAdapter(this)
    private lateinit var dao : NationalBaseDao
    private var modelType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
        modelType = arguments?.getInt(MainActivity.DATA_TYPE) ?: 1
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(MarginItemDecoration(16))

        setData(modelType)
        search(modelType)

        toolBarAction.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.about -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, InfoFragment())
                        .addToBackStack(InfoFragment::class.simpleName).commit()
                    // do something
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

    private fun search(type: Int){
        search.addTextChangedListener {
            when (type) {
                1 -> {
                    toolBarAction.title = "Arxeologiyaliq estalikler"
                    val result : List<Arxeologiya> = dao.searchArxeologiyaByName("${it.toString()}%")
                    adapter.models = result
                }
                2 -> {
                    toolBarAction.title = "Milliy"
                    val  result: List<National> = dao.searchNationalByName("${it.toString()}%")
                    adapter.models = result
                }
                3 -> {
                    toolBarAction.title = "Muzey"
                    val result : List<Muzeyler> = dao.searchMuzeyByName("${it.toString()}%")
                    adapter.models = result
                }
                2 -> {
                    toolBarAction.title = "Tábiyat"
                    val  result: List<Tabiyat> = dao.searchTabiyatByName("${it.toString()}%")
                    adapter.models = result
                }
            }
        }
    }

    private fun setData(type: Int){
        when (type) {
            1 -> {
                val data = dao.getArxeologiya()
                adapter.models = data
            }
            2 -> {
                val data = dao.getNational()
                adapter.models = data
            }
            3 -> {
                val data = dao.getMuzeyler()
                adapter.models = data
            }
            4 -> {
                val data = dao.getTabiyat()
                adapter.models = data
            }
        }
    }

    override fun onItemClickListener(id:Int,mtype: Int) {
        when (mtype) {
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
                bundle.putInt(MuzeyDetailFragment.Muzey_Id,id)
                fragmentMuzey.arguments = bundle
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, fragmentMuzey)
                    .addToBackStack(MuzeyDetailFragment::class.simpleName).commit()
            }
            4 -> {
                val tabiyatFragment = TabiyatDetailFragment()
                val bundle = Bundle()
                bundle.putInt(TabiyatDetailFragment.Tabiyat_Id,id)
                tabiyatFragment.arguments = bundle
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment,tabiyatFragment)
                    .addToBackStack(TabiyatDetailFragment::class.simpleName).commit()
            }
        }
    }
}