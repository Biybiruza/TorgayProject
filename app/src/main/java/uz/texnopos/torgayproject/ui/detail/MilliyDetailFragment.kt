package uz.texnopos.torgayproject.ui.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import uz.texnopos.torgayproject.MainActivity
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.data.model.National

class MilliyDetailFragment : Fragment(R.layout.fragment_detail){

    companion object{
        const val MILLIY_ID = "milliy_id"
    }

    private var milliyId = 0
    private lateinit var dao: NationalBaseDao
    private lateinit var currentMilliy: National

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
        setHasOptionsMenu(true)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        milliyId = arguments?.getInt(MILLIY_ID) ?: 0
        currentMilliy = dao.getNationalById(milliyId)

        (activity as MainActivity?)?.setActionBarTitle(currentMilliy.name)
        (activity as MainActivity?)?.setDisplayHomeAsUpEnabled(true)

        tvName!!.text = currentMilliy.name
        tvText!!.text = Html.fromHtml(currentMilliy.text,Html.FROM_HTML_MODE_COMPACT)
        Glide
            .with(this)
            .load(resources.getIdentifier("national$milliyId","drawable",context?.packageName))
            .centerCrop()
            .into(imageDetail)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> {
                val share = Intent(Intent.ACTION_SEND)
                share.putExtra(Intent.EXTRA_TEXT, currentMilliy.text)
                share.type = "text/plain"
                startActivity(Intent.createChooser(share, "Bolisiw"))
            }
            R.id.favorite ->{
                Toast.makeText(requireContext(),"basildi", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}