package uz.texnopos.torgayproject.ui.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
        milliyId = arguments?.getInt(MILLIY_ID) ?: 0
        currentMilliy = dao.getNationalById(milliyId)
        toolBarDetail.title = currentMilliy.name

        tvName!!.text = currentMilliy.name
        tvText!!.text = Html.fromHtml(currentMilliy.text,Html.FROM_HTML_MODE_COMPACT)
        Glide
            .with(this)
            .load(resources.getIdentifier("national$milliyId","drawable",context?.packageName))
            .centerCrop()
            .into(imageDetail)

        toolBarDetail.setNavigationOnClickListener{
            (activity as MainActivity?)?.onBackPressed()
            // back button pressed
        }

        if (currentMilliy.isFavorite == 0){
            toolBarDetail.menu.findItem(R.id.favorite).setIcon(R.drawable.ic_baseline_bookmark_border_24)
        }else{
            toolBarDetail.menu.findItem(R.id.favorite).setIcon(R.drawable.ic_baseline_bookmark_24)
        }

        toolBarDetail.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.share -> {
                    val share = Intent(Intent.ACTION_SEND)
                    share.putExtra(Intent.EXTRA_TEXT, currentMilliy.text)
                    share.type = "text/plain"
                    startActivity(Intent.createChooser(share, "Bolisiw"))
                    true
                }
                R.id.favorite -> {
                    currentMilliy.isFavorite = 1 - currentMilliy.isFavorite
                    dao.updateMilliy(currentMilliy)
                    if (currentMilliy.isFavorite == 0){
                        it.setIcon(R.drawable.ic_baseline_bookmark_border_24)
                    }else{
                        it.setIcon(R.drawable.ic_baseline_bookmark_24)
                    }
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }

    }


}