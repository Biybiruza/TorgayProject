package uz.texnopos.torgayproject.ui.detail

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
import uz.texnopos.torgayproject.data.model.Muzeyler

class MuzeyDetailFragment: Fragment(R.layout.fragment_detail) {

    companion object {
        const val Muzey_Id = "muzey_id"
    }

    private var muzey_id = 0
    private lateinit var currentMuzey: Muzeyler
    private lateinit var dao: NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        muzey_id = arguments?.getInt(Muzey_Id)?:0
        currentMuzey = dao.getMuzeylerById(muzey_id)

        toolBarDetail.title = currentMuzey.name

        tvName!!.text = currentMuzey.name
        tvText!!.text = Html.fromHtml(currentMuzey.text, Html.FROM_HTML_MODE_COMPACT)

        Glide
            .with(this)
            .load(resources.getIdentifier("muzey$muzey_id","drawable",context?.packageName))
            .centerCrop()
            .into(imageDetail)

        toolBarDetail.setNavigationOnClickListener{
            (activity as MainActivity?)?.onBackPressed()
            // back button pressed
        }


    }
}