package uz.texnopos.torgayproject.ui.muzey

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import uz.texnopos.torgayproject.MainActivity
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.ui.data.TorgayDataBase
import uz.texnopos.torgayproject.ui.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.ui.data.model.Muzeyler

class MuzeyDetailFragment: Fragment(R.layout.activity_detail) {

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

        (activity as MainActivity?)?.setActionBarTitle(currentMuzey.name)
        (activity as MainActivity?)?.setDisplayHomeAsUpEnabled(true)

        tvName!!.text = currentMuzey.name
        tvText!!.text = Html.fromHtml(currentMuzey.text, Html.FROM_HTML_MODE_COMPACT)

        Glide
            .with(this)
            .load(resources.getIdentifier("muzey$muzey_id","drawable",context?.packageName))
            .centerCrop()
            .into(imageDetail)
}
}