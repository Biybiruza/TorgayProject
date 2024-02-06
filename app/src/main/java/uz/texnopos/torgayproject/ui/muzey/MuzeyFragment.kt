package uz.texnopos.torgayproject.ui.muzey

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
import uz.texnopos.torgayproject.data.model.National
import uz.texnopos.torgayproject.ui.detail.MuzeyDetailFragment
import uz.texnopos.torgayproject.ui.info.InfoFragment

class MuzeyFragment: Fragment(R.layout.fragment_milliy), TorgayItemClickListener{

    private val muzeyAdapter = MuzeyListAdapter(this)
    private lateinit var dao: NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if((requireActivity() as MainActivity).nav_view.selectedItemId != R.id.menu_museum)
//            (requireActivity() as MainActivity).nav_view.selectedItemId = R.id.menu_museum

        recyclerView.addItemDecoration(MarginItemDecoration(16))
        recyclerView.adapter = muzeyAdapter
        toolBarAction.title = "Muzeyler"

        setData()

        search.addTextChangedListener {
            val result: List<Muzeyler> = dao.searchMuzeyByName("${it.toString()}%")
            muzeyAdapter.models = result
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