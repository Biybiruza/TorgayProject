package uz.texnopos.torgayproject.ui.national

import android.os.Bundle
import android.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if((requireActivity() as MainActivity).nav_view.selectedItemId != R.id.menu_national)
//            (requireActivity() as MainActivity).nav_view.selectedItemId = R.id.menu_national

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
        nationalAdapter.models = dao.getNational()
    }

    override fun onItemClickListener(id: Int) {
        val milliyFragment = MilliyDetailFragment()
        val bundle = Bundle()
        bundle.putInt(MilliyDetailFragment.MILLIY_ID, id)
        milliyFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, milliyFragment)
            .addToBackStack(MilliyDetailFragment::class.simpleName).commit()
    }
}