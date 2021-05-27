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
import uz.texnopos.torgayproject.data.model.Tabiyat

class TabiyatDetailFragment: Fragment(R.layout.fragment_detail) {

    companion object{
        const val Tabiyat_Id = "tabiyat_id"
    }

    private var tabiyat_id = 0
    private lateinit var currentTabiyat: Tabiyat
    private lateinit var dao: NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabiyat_id = arguments?.getInt(Tabiyat_Id)?:0
        currentTabiyat = dao.getTabiyatById(tabiyat_id)

        toolBarDetail.title = currentTabiyat.name

        tvName!!.text = currentTabiyat.name
        tvText!!.text = Html.fromHtml(currentTabiyat.text, Html.FROM_HTML_MODE_COMPACT)

        Glide
            .with(this)
            .load(resources.getIdentifier("tab$tabiyat_id","drawable",context?.packageName))
            .centerCrop()
            .into(imageDetail)

        toolBarDetail.setNavigationOnClickListener {
            (activity as MainActivity?)?.onBackPressed()
        }
    }
}