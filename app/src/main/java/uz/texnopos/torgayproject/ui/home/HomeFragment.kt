package uz.texnopos.torgayproject.ui.home

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_milliy.*
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.data.model.Arxeologiya
import uz.texnopos.torgayproject.ui.detail.HomeDetailFragment

class HomeFragment: Fragment(R.layout.fragment_milliy),TorgayItemClickListener {

    private val homeAdapter = HomeListAdapter(this)
    private lateinit var dao : NationalBaseDao
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao =TorgayDataBase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        recyclerView.addItemDecoration(MarginItemDecoration(16))
        recyclerView.adapter = homeAdapter
        toolBarAction.title = "Arxeologiyaliq estalikler"
        setData()

        search.addTextChangedListener {
            val result : List<Arxeologiya> = dao.searchArxeologiyaByName("${it.toString()}%")
            homeAdapter.models = result
        }

        toolBarAction.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.about -> {
                    navController!!.navigate(R.id.action_menu_home_to_infoFragment)
                    // do something
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

    private fun setData(){
        val data = dao.getArxeologiya()
        homeAdapter.models = data
    }

    override fun onItemClickListener(id:Int) {
        val bundle = bundleOf(HomeDetailFragment.Torgay_Id to id)
        navController!!.navigate(R.id.action_menu_home_to_homeDetailFragment, bundle)
    }

}