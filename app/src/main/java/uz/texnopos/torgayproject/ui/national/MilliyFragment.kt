package uz.texnopos.torgayproject.ui.national

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
import uz.texnopos.torgayproject.data.model.National
import uz.texnopos.torgayproject.ui.detail.MilliyDetailFragment
import uz.texnopos.torgayproject.ui.info.InfoFragment

class MilliyFragment: Fragment(R.layout.fragment_milliy),TorgayItemClickListener{

    private val nationalAdapter = NationalListAdapter(this)
    private lateinit var dao:NationalBaseDao
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        recyclerView.addItemDecoration(MarginItemDecoration(16))
        recyclerView.adapter = nationalAdapter

        setData()
        toolBarAction.title = "Milliy"

        search.addTextChangedListener {
            val result: List<National> = dao.searchNationalByName("${it.toString()}%")
            nationalAdapter.models = result
        }

        toolBarAction.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.about -> {
                    navController!!.navigate(R.id.action_menu_national_to_infoFragment)
                    true
                }
                else ->{
                    super.onOptionsItemSelected(it)
                }
            }
        }

    }

    private fun setData(){
        nationalAdapter.models = dao.getNational()
    }

    override fun onItemClickListener(id: Int) {
        val bundle = bundleOf(MilliyDetailFragment.MILLIY_ID to id)
        navController!!.navigate(R.id.action_menu_national_to_milliyDetailFragment, bundle)
    }
}