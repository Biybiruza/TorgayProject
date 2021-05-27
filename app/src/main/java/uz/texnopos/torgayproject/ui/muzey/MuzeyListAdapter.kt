package uz.texnopos.torgayproject.ui.muzey

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.model.Muzeyler

class MuzeyListAdapter(private val listener: TorgayItemClickListener): RecyclerView.Adapter<MuzeyListAdapter.MuzeyListViewHolder>() {

    inner class MuzeyListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun populateModel(muzey: Muzeyler){
            itemView.tvTitle.text = muzey.name
            val imageResName = "muzey${muzey.id}"
            Glide
                .with(itemView)
                .load(itemView.context.resources.getIdentifier(imageResName,"drawable", itemView.context.packageName))
                .centerCrop()
                .into(itemView.imageView)

            itemView.setOnClickListener {
                listener.onItemClickListener(muzey.id)
            }
        }
    }

    var models = listOf<Muzeyler>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuzeyListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return MuzeyListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MuzeyListViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size
}