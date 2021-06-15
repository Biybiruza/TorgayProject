package uz.texnopos.torgayproject.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.TorgayItemClickListener
import uz.texnopos.torgayproject.data.model.Arxeologiya

class HomeListAdapter(private val listener: TorgayItemClickListener) : RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder> () {
    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun populateModel(home: Arxeologiya){
            itemView.tvTitle.text = home.name
            val imageResName = "arxeolog${home.id}"
            Glide
                .with(itemView)
                .load(itemView.context.resources.getIdentifier(imageResName,"drawable",itemView.context.packageName))
                .centerCrop()
                .into(itemView.imageView)
            itemView.setOnClickListener {
                listener.onItemClickListener(home.id)
            }
        }
    }

    var models = listOf<Arxeologiya>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
        val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return HomeListViewHolder(itemView)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        holder.populateModel(models[position])
    }
}