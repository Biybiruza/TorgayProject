package uz.texnopos.torgayproject.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.model.*

class FragmentListAdapter(private val listener: TorgayItemClickListener) : RecyclerView.Adapter<FragmentListAdapter.FragmentListViewHolder>() {

    inner class FragmentListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun populateMoldeArxeologiya(arxeologiyaFav: Arxeologiya){
            itemView.tvTitle.text = arxeologiyaFav.name
            val imageResName = "arxeolog${arxeologiyaFav.id}"
            Glide
                .with(itemView)
                .load(itemView.context.resources.getIdentifier(imageResName,"drawable",itemView.context.packageName))
                .centerCrop()
                .into(itemView.imageView)
            itemView.setOnClickListener {
                listener.onItemClickListener(arxeologiyaFav.id,ModelType.ARXEOLOGIYA_TYPE)
            }
        }
        fun populateMoldeMilliy(milliy: National){
            itemView.tvTitle.text = milliy.name
            val imageResName = "national${milliy.id}"
            Glide
                .with(itemView)
                .load(itemView.context.resources.getIdentifier(imageResName,"drawable",itemView.context.packageName))
                .centerCrop()
                .into(itemView.imageView)

            itemView.setOnClickListener {
                listener.onItemClickListener(milliy.id,ModelType.MILLIY_TYPE)
            }
        }
        fun populateModelMuzey(muzey: Muzeyler){
            itemView.tvTitle.text = muzey.name
            val imageResName = "muzey${muzey.id}"
            Glide
                .with(itemView)
                .load(itemView.context.resources.getIdentifier(imageResName,"drawable", itemView.context.packageName))
                .centerCrop()
                .into(itemView.imageView)

            itemView.setOnClickListener {
                listener.onItemClickListener(muzey.id,ModelType.MUZEY_TYPE)
            }
        }
        fun populateModelTabiyat(tabiyat: Tabiyat){
            itemView.tvTitle.text = tabiyat.name
            val imageResName = "tab${tabiyat.id}"
            Glide
                .with(itemView)
                .load(itemView.context.resources.getIdentifier(imageResName,"drawable",itemView.context.packageName))
                .centerCrop()
                .into(itemView.imageView)

            itemView.setOnClickListener {
                listener.onItemClickListener(tabiyat.id,ModelType.TABIYAT_TYPE)
            }
        }
    }

    var models = listOf<ModelType>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentListViewHolder {
        val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return FragmentListViewHolder(itemView)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: FragmentListViewHolder, position: Int) {
        when {
            getItemViewType(position) == ModelType.ARXEOLOGIYA_TYPE -> {
                holder.populateMoldeArxeologiya(models[position] as Arxeologiya)
            }
            getItemViewType(position) == ModelType.MILLIY_TYPE -> {
                holder.populateMoldeMilliy(models[position] as National)
            }
            getItemViewType(position) == ModelType.MUZEY_TYPE -> {
                holder.populateModelMuzey(models[position] as Muzeyler)
            }
            getItemViewType(position) == ModelType.TABIYAT_TYPE -> {
                holder.populateModelTabiyat(models[position] as Tabiyat)
            }
        }
    }
}