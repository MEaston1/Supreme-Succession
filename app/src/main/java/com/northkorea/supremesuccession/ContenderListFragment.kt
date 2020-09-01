package com.northkorea.supremesuccession

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.northkorea.supremesuccession.databinding.RecyclerItemContenderModelBinding

class ContenderListFragment : Fragment() {

    private lateinit var imageResIds: IntArray
    private lateinit var names: Array<String>
    private lateinit var descriptions: Array<String>
    private lateinit var urls: Array<String>
    private lateinit var listener: OnContenderSelected

    companion object {

        fun newInstance(): ContenderListFragment {
            return ContenderListFragment()
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnContenderSelected) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnContenderSelected.")
        }

        // Get contender names and descriptions.
        val resources = context.resources
        names = resources.getStringArray(R.array.names)
        descriptions = resources.getStringArray(R.array.descriptions)
        urls = resources.getStringArray(R.array.urls)

        // Get contender images.
        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = names.size
        imageResIds = IntArray(imageCount)
        for (i in 0 until imageCount) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
        }
        typedArray.recycle()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_contender_list, container, false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = ContenderListAdapter(activity)
        return view
    }
    internal inner class ContenderListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>(){

        private val layoutInflater = LayoutInflater.from(context)
        override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): ViewHolder {
            val recyclerViewContenderModelBinding =  RecyclerItemContenderModelBinding.inflate(layoutInflater, viewgroup, false)
            return ViewHolder(recyclerViewContenderModelBinding.root, recyclerViewContenderModelBinding)
        }

        override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {
            val contender = ContenderModel(imageResIds[position], names[position], descriptions[position], urls[position])
            viewholder.setData(contender)
            viewholder.itemView.setOnClickListener{ listener.onContenderSelected(contender)}
        }

        override fun getItemCount() = names.size
    }
     internal inner class ViewHolder constructor(itemView: View, private val recyclerItemContenderListBinding: RecyclerItemContenderModelBinding) : RecyclerView.ViewHolder(itemView){
         fun setData(contenderModel: ContenderModel){
             recyclerItemContenderListBinding.contenderModel = contenderModel
         }
     }

    interface OnContenderSelected {
        fun onContenderSelected(contenderModel: ContenderModel)
    }
}