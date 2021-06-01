package uz.texnopos.torgayproject.ui.favorite.arxeologiya

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.texnopos.torgayproject.R
import uz.texnopos.torgayproject.data.TorgayDataBase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.ui.home.HomeFragment

class Arxeologiya : Fragment(R.layout.fragment_milliy){

    lateinit var arxeologiyaFragment : HomeFragment
    lateinit var dao: NationalBaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = TorgayDataBase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}