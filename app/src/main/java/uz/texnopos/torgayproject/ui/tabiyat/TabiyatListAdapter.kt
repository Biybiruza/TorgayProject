package uz.texnopos.torgayproject.ui.tabiyat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.model.Tabiyat

class TabiyatListAdapter(private val listener: TorgayItemClickListener): RecyclerView.Adapter<TabiyatListAdapter.TabiyatViewHolder>() {

    inner class TabiyatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun populateModel(tabiyat: Tabiyat){
            itemView.tvTitle.text = tabiyat.name
            val imageResName = "tab${tabiyat.id}"
             Glide
                .with(itemView)
                .load(itemView.context.resources.getIdentifier(imageResName,"drawable",itemView.context.packageName))
                .centerCrop()
                .into(itemView.imageView)

            itemView.setOnClickListener {
                listener.onItemClickListener(tabiyat.id)
            }
        }
    }
    var models = listOf<Tabiyat>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabiyatViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return TabiyatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TabiyatViewHolder, position: Int) {
       holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size

}