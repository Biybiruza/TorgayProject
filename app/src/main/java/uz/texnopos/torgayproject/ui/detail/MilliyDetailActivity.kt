package uz.texnopos.torgayproject.ui.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.data.model.National

class MilliyDetailActivity : Fragment(R.layout.activity_detail){

    companion object{
        const val MILLIY_ID = "milliy_id"
    }

    private var milliyId = 0
    private lateinit var dao: NationalBaseDao
    private lateinit var currentMilliy: National

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()

        milliyId = arguments?.getInt(MILLIY_ID) ?: 0
        currentMilliy = dao.getNationalById(milliyId)

        tvName.text = currentMilliy.name
        tvText.text = Html.fromHtml(currentMilliy.text,Html.FROM_HTML_MODE_COMPACT)
        Glide
            .with(this)
            .load(resources.getIdentifier("national$milliyId","drawable",context?.packageName))
            .centerCrop()
            .into(imageDetail)
    }
}