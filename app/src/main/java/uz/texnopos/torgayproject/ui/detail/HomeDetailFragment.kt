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
import uz.texnopos.torgayproject.data.model.Arxeologiya

class HomeDetailFragment : Fragment(R.layout.fragment_detail) {

    companion object{
        const val Torgat_Id = "torgayId"
    }

    private var torgayId = 0
    private lateinit var currentHome : Arxeologiya
    private lateinit var dao                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       : NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        torgayId = arguments?.getInt(Torgat_Id) ?: 0
        currentHome = dao.getArxeologiyaById(torgayId)
        toolBarDetail.title = currentHome.name

        tvName.text = currentHome.name
        tvText.text = Html.fromHtml(currentHome.text,Html.FROM_HTML_MODE_COMPACT)
        Glide
            .with(this)
            .load(resources.getIdentifier("arxeolog$torgayId","drawable",context?.packageName))
            .centerCrop()
            .into(imageDetail)

        toolBarDetail.setNavigationOnClickListener{
            (activity as MainActivity?)?.onBackPressed()
            // back button pressed
        }

        toolBarDetail.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.share -> {
                    val share = Intent(Intent.ACTION_SEND)
                    share.putExtra(Intent.EXTRA_TEXT, currentHome.text)
                    share.type = "text/plain"
                    startActivity(Intent.createChooser(share, "Bolisiw"))
                    true
                }
                R.id.favorite -> {
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }

    }

}