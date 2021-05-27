package uz.texnopos.torgayproject.ui.tabiyat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_tabiyat.*
import uz.texnopos.torgayproject.MainActivity
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.ui.detail.TabiyatDetailFragment


class TabiyatFragment: Fragment(R.layout.fragment_milliy), TorgayItemClickListener {

    private val tabiyatAdapter = TabiyatListAdapter(this)
    private lateinit var dao: NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.addItemDecoration(MarginItemDecoration(16))
        recyclerView.adapter = tabiyatAdapter
        toolBarAction.title = "Tabiyat"

        setData()
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
