package uz.texnopos.torgayproject.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_view.*
import kotlinx.android.synthetic.main.item_view.view.*
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.data.model.Arxeologiya

class DetailActivity : AppCompatActivity() {

    companion object{
        const val Torgat_Id = "torgayId"
    }

    private var torgayId = 0
    private lateinit var currentTorgay : Arxeologiya
    private lateinit var dao                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       : NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Arxeologiyaliq estelikler"

        dao = TorgayDataBase.getInstance(this).dao()

        torgayId = intent.getIntExtra(Torgat_Id, 0)
        currentTorgay = dao.getArxeologiyaById(torgayId)

        tvName.text = currentTorgay.name
        tvText.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Html.fromHtml(currentTorgay.text, Html.FROM_HTML_MODE_COMPACT)
        }else{
            Html.fromHtml(currentTorgay.text)
        }
        Glide
            .with(this)
            .load(resources.getIdentifier("arxeolog$torgayId","drawable",packageName))
            .centerCrop()
            .into(imageDetail)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}