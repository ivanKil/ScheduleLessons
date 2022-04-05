package com.kusch.exam.ui.home.homeworks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kusch.exam.R
import com.kusch.exam.model.data.Homework
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class HomeworksAdapter(private val clickListener: (Homework) -> Unit) :
    RecyclerView.Adapter<HomeworksAdapter.RecyclerItemViewHolder>() {

    private var data: List<Homework> = arrayListOf()
    
    fun setData(data: List<Homework>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.homework_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Homework) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.homework_name).text = data.name
                val date = data.time
                val curDate = LocalDate.now()
                val delta = ChronoUnit.DAYS.between(curDate, date)
                itemView.findViewById<TextView>(R.id.homework_time).text =
                    if (delta >= 0) "Осталось $delta дней" else "Прошло ${-delta} дней"
                itemView.findViewById<TextView>(R.id.homework_content).text = data.description
            }
        }
    }
}
