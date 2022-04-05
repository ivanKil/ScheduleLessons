package com.kusch.exam.ui.home.lessons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.kusch.exam.R
import com.kusch.exam.model.data.Lesson
import java.time.format.DateTimeFormatter

class LessonsAdapter(private val clickListener: (Lesson) -> Unit) :
    RecyclerView.Adapter<LessonsAdapter.RecyclerItemViewHolder>() {

    private var data: List<Lesson> = arrayListOf()
    var dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

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
                itemView.findViewById<TextView>(R.id.lesson_name).text = data.name
                val time = data.time
                val timeUntil = time.plusMinutes(data.duration)
                itemView.findViewById<TextView>(R.id.lesson_time).text =
                    "${data.time.format(dateTimeFormatter)} - ${timeUntil.format(dateTimeFormatter)}"
                itemView.findViewById<ConstraintLayout>(R.id.right_panel)
                    .setOnClickListener { clickListener(data) }
            }
        }
    }
}
