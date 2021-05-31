package uz.texnopos.torgayproject.ui.home

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_milliy.*
import kotlinx.android.synthetic.main.torgay_info.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao =TorgayDataBase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.addItemDecoration(MarginItemDecoration(16))
        recyclerView.adapter = homeAdapter
        setData()
        toolBarAction.title = "Arxeologiyaliq estalikler"

        search.addTextChangedListener {
            val result : List<Arxeologiya> = dao.searchArxeologiyaByName("${it.toString()}%")
            homeAdapter.models = result
        }

        toolBarAction.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.about -> {
                    val dialog = LayoutInflater.from(requireContext()).inflate(R.layout.torgay_info,null)
                    val alertDialog = AlertDialog.Builder(requireContext()).setView(dialog).show()
//                    textViewInfo.movementMethod = LinkMovementMethod.getInstance()
                    Toast.makeText(requireContext(),"basildi", Toast.LENGTH_LONG).show()
                    // do something
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
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

}