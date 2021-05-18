package uz.texnopos.torgayproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_milliy.*
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.ui.detail.DetailActivity

class HomeFragment: Fragment(R.layout.fragment_milliy),TorgayItemClickListener {

    private val homeAdapter = HomeListAdapter(this)
    private lateinit var dao : NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao =TorgayDataBase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.addItemDecoration(MarginItemDecoration(16))
        recyclerView.adapter = homeAdapter

        setData()

    }

    fun setData(){
        val data = dao.getArxeologiya()
        homeAdapter.models = data
    }

    override fun onItemClickListener(id:Int) {
        val mIntent = Intent(requireActivity(),DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.Torgat_Id, id)
        startActivity(mIntent)
    }

}