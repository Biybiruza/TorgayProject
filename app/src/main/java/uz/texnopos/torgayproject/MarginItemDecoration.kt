package uz.texnopos.torgayproject

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spaceSize:Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect){
            if (parent.getChildAdapterPosition(view) in 0 .. 1){
                top = spaceSize
            }
            bottom = spaceSize
            left = spaceSize
            right = spaceSize
        }
    }

}