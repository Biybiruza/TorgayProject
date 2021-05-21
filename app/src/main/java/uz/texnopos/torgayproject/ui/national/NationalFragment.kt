package uz.texnopos.torgayproject.ui.national

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.dialog_settings.view.*
import kotlinx.android.synthetic.main.fragment_milliy.*
import kotlinx.android.synthetic.main.fragment_milliy.toolBarActionTitle
import uz.texnopos.torgayproject.MarginItemDecoration
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.data.model.National
import uz.texnopos.torgayproject.ui.detail.MilliyDetailFragment

class NationalFragment: Fragment(R.layout.fragment_milliy),TorgayItemClickListener{

    private val nationalAdapter = NationalListAdapter(this)
    private lateinit var dao:NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.addItemDecoration(MarginItemDecoration(16))
        recyclerView.adapter = nationalAdapter

        toolBarActionTitle.text = "Milliy"
        setData()

        search.addTextChangedListener {
            val result: List<National> = dao.searchNationalByName("${it.toString()}%")
            nationalAdapter.models = result
        }

        toolBarAction.setOnMenuItemClickListener {
            val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            when(it.itemId){
                R.id.about -> {

                    true
                }
                R.id.settings ->{
                    val dialog = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_settings,null)
                    val alertDialog = androidx.appcompat.app.AlertDialog.Builder(requireContext()).setView(dialog).show()
                    dialog.switch_settings.setOnCheckedChangeListener{buttonView, isChecked ->
                        if(isChecked){
                            Toast.makeText(requireContext(),"janıq",Toast.LENGTH_LONG).show()
                            alertDialog.dismiss()
                        }else{
                            Toast.makeText(requireContext(),"óshik",Toast.LENGTH_LONG).show()
                            alertDialog.dismiss()
                        }
                    }
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