package uz.texnopos.torgayproject.ui.muzey

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_tabiyat.*
import uz.texnopos.torgayproject.MainActivity
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.ui.data.TorgayDataBase
import uz.texnopos.torgayproject.ui.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.ui.tabiyat.TabiyatItemClickListener

class MuzeyFragment: Fragment(R.layout.fragment_tabiyat), TabiyatItemClickListener {

    private val muzeyAdapter = MuzeyListAdapter(this)
    private lateinit var dao: NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = muzeyAdapter

        (activity as MainActivity?)?.setActionBarTitle("Muzeyler")

        setData()
    }

    fun setData(){
       val data = dao.getMuzeyler()
       muzeyAdapter.models = data
    }

    override fun onItemClickListener(id: Int){
        val fragmentMuzey = MuzeyDetailFragment()
        val bundle = Bundle()
        bundle.putInt(MuzeyDetailFragment.Muzey_Id,id)
        fragmentMuzey.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragmentMuzey)
            .addToBackStack(MuzeyDetailFragment::class.simpleName).commit()
    }
}