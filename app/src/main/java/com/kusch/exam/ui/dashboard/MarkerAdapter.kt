package com.kusch.exam.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kusch.exam.R
import com.kusch.exam.model.data.Lesson

class MarkerAdapter(private val clickListener: (Lesson) -> Unit) :
    RecyclerView.Adapter<MarkerAdapter.RecyclerItemViewHolder>() {

    private var data: List<Lesson> = arrayListOf()

    fun setData(data: List<Lesson>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.lesson_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Lesson) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.marker_item_name).text = data.name
                //itemView.findViewById<TextView>(R.id.marker_item_annotation).text = data.date
                itemView.setOnClickListener { clickListener(data) }
            }
        }
    }
}
