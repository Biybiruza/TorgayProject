package uz.texnopos.torgayproject.ui.national

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import kotlinx.android.synthetic.main.fragment_milliy.*
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.ui.detail.MilliyDetailActivity

class NationalFragment: Fragment(R.layout.fragment_milliy),TorgayItemClickListener{

    private val nationalAdapter = NationalListAdapter(this)
    private lateinit var dao:NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.addItemDecoration(MarginItemDecoration(16))
        recyclerView.adapter = nationalAdapter

        setData()
    }

    fun setData(){
        nationalAdapter.models = dao.getNational()
    }

    override fun onItemClickListener(id: Int) {
        val milliyFragment = MilliyDetailActivity()
        val bundle = Bundle()
        bundle.putInt(MilliyDetailActivity.MILLIY_ID, id)

        milliyFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, milliyFragment)
            .addToBackStack(MilliyDetailActivity::class.simpleName).commit()
    }

}