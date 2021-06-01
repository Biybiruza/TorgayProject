package uz.texnopos.torgayproject.ui.info

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_info.*
import uz.texnopos.torgayproject.MainActivity
import uz.texnopos.torgayproject.R

class InfoFragment : Fragment(R.layout.fragment_info) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewInfo.movementMethod = LinkMovementMethod.getInstance()

        infoToolbar.setNavigationOnClickListener {
            (activity as MainActivity?)?.onBackPressed()
        }
    }

}