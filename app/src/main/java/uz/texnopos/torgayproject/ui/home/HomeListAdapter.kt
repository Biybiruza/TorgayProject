package uz.texnopos.torgayproject.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.data.model.Home

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder> () {
    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun populateModel(milliy: Home){
            itemView.tvTitle.text = milliy.id.toString()
        }
    }

    var models = listOf<Home>()
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