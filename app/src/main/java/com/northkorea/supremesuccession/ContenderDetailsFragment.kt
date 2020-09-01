package com.northkorea.supremesuccession


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.northkorea.supremesuccession.databinding.FragmentContenderDetailsBinding

class ContenderDetailsFragment : Fragment() {

    companion object{
        private const val CONTENDERMODEL = "model"

        fun newInstance(contenderModel: ContenderModel): ContenderDetailsFragment{
            val args = Bundle()
            args.putSerializable(CONTENDERMODEL, contenderModel)
            val fragment = ContenderDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentContenderDetailsBinding = FragmentContenderDetailsBinding.inflate(inflater, container, false)
        val model = arguments!!.getSerializable(CONTENDERMODEL) as ContenderModel
        fragmentContenderDetailsBinding.contenderModel = model
        model.text = String.format(getString(R.string.description_format), model.description, model.url)
        return fragmentContenderDetailsBinding.root
    }
}