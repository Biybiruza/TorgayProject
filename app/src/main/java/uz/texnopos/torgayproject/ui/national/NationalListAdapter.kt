package uz.texnopos.torgayproject.ui.national

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.model.National

class NationalListAdapter (private val listener : TorgayItemClickListener):RecyclerView.Adapter<NationalListAdapter.NationalViewHolder>(){
    inner class NationalViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun populateModel(milliy : National){
            itemView.tvTitle.text = milliy.name
            val imageResName = "national${milliy.id}"
            Glide
                .with(itemView)
                .load(itemView.context.resources.getIdentifier(imageResName,"drawable",itemView.context.packageName))
                .centerCrop()
                .into(itemView.imageView)

            itemView.setOnClickListener {
                listener.onItemClickListener(milliy.id)
            }
        }
    }

    var models = listOf<National>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NationalViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return NationalViewHolder(itemView)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: NationalViewHolder, position: Int) {
        holder.populateModel(models[position])
    }
}