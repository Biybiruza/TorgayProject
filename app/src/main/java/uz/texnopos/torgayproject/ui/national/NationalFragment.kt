package uz.texnopos.torgayproject.ui.national

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_milliy.*
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao

class NationalFragment: Fragment(R.layout.fragment_milliy){

    private val nationalAdapter = NationalListAdapter()
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

}