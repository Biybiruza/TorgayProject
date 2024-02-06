package uz.texnopos.torgayproject.ui.tabiyat

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_milliy.*
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.data.model.Muzeyler
import uz.texnopos.torgayproject.data.model.Tabiyat
import uz.texnopos.torgayproject.ui.detail.TabiyatDetailFragment
import uz.texnopos.torgayproject.ui.info.InfoFragment

class TabiyatFragment: Fragment(R.layout.fragment_milliy), TorgayItemClickListener {

    private val tabiyatAdapter = TabiyatListAdapter(this)
    private lateinit var dao: NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if((requireActivity() as MainActivity).nav_view.selectedItemId != R.id.menu_nature)
//            (requireActivity() as MainActivity).nav_view.selectedItemId = R.id.menu_nature

        recyclerView.addItemDecoration(MarginItemDecoration(16))
        recyclerView.adapter = tabiyatAdapter
        toolBarAction.title = "Tabiyat"

        setData()

        search.addTextChangedListener {
            val result: List<Tabiyat> = dao.searchTabiyatByName("${it.toString()}%")
            tabiyatAdapter.models = result
        }

        toolBarAction.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.about -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, InfoFragment())
                        .addToBackStack(InfoFragment::class.simpleName).commit()
                    true
                }
                else ->{
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

    fun setData(){
        tabiyatAdapter.models = dao.getTabiyat()
    }

    override fun onItemClickListener(id: Int){
        val tabiyatFragment =
            TabiyatDetailFragment()
        val bundle = Bundle()
        bundle.putInt(TabiyatDetailFragment.Tabiyat_Id,id)

        tabiyatFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment,tabiyatFragment)
            .addToBackStack(TabiyatDetailFragment::class.simpleName).commit()
    }
}
