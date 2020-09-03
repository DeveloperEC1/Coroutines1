package com.elior.coroutines.CustomAdaptersPackage

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elior.coroutines.ModelsPackage.Results
import com.elior.coroutines.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_part.view.*

class CustomAdaptersPart(private var partItemList: List<Results>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.adapter_part, parent, false)
        return PartViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PartViewHolder).bind(partItemList[position])
    }

    override fun getItemCount() = partItemList.size

    class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(part: Results) {
            itemView.title1.text = part.title
            itemView.overview1.text = part.overview
            Picasso.get().load("https://image.tmdb.org/t/p/original" + part.poster_path)
                .into(itemView.image1)
        }
    }

}
