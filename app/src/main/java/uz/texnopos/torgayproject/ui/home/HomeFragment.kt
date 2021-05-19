package uz.texnopos.torgayproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_milliy.*
import uz.texnopos.torgayproject.MainActivity
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.ui.detail.HomeDetailFragment
import uz.texnopos.torgayproject.ui.detail.MilliyDetailFragment

class HomeFragment: Fragment(R.layout.fragment_milliy),TorgayItemClickListener {

    private val homeAdapter = HomeListAdapter(this)
    private lateinit var dao : NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao =TorgayDataBase.getInstance(requireContext()).dao()
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.addItemDecoration(MarginItemDecoration(16))
        recyclerView.adapter = homeAdapter

        (activity as MainActivity?)?.setActionBarTitle("Arxeologiyaliq estelikler")
        (activity as MainActivity?)?.setDisplayHomeAsUpEnabled(false)

        setData()

    }

    fun setData(){
        val data = dao.getArxeologiya()
        homeAdapter.models = data
    }

    override fun onItemClickListener(id:Int) {
        val fragmentHome = HomeDetailFragment()
        val bundle = Bundle()
        bundle.putInt(HomeDetailFragment.Torgat_Id, id)
        fragmentHome.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragmentHome)
            .addToBackStack(HomeDetailFragment::class.simpleName).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.action_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about -> {
                Toast.makeText(requireContext(),"basildi", Toast.LENGTH_LONG).show()
            }
            R.id.settings ->{
                Toast.makeText(requireContext(),"basildi", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}