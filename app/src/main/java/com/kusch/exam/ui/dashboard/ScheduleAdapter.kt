package com.kusch.exam.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kusch.exam.R
import com.kusch.exam.databinding.AddLessonItemBinding
import com.kusch.exam.databinding.LessonItemBinding
import com.kusch.exam.model.data.AddLesson
import com.kusch.exam.model.data.Lesson
import com.kusch.exam.model.data.ListData
import com.kusch.exam.model.data.TYPE_LESSON
import java.time.format.DateTimeFormatter

class ScheduleAdapter(private val clickListener: (ListData) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder>() {
    var dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private var data: List<ListData> = arrayListOf()

    fun setData(data: List<ListData>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            TYPE_LESSON ->
                LessonViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.lesson_item, parent, false)
                )
            else ->
                AddLessonViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.add_lesson_item, parent, false)
                )
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class LessonViewHolder(view: View) : BaseViewHolder(view) {

        private val viewBinding: LessonItemBinding by viewBinding()

        override fun bind(item: ListData, position: Int) {
            with(viewBinding) {
                val data = item as Lesson
                lessonName.text = data.name
                val time = data.time
                val timeUntil = time.plusMinutes(data.duration)
                lessonTime.text =
                    "${data.time.format(dateTimeFormatter)} - ${timeUntil.format(dateTimeFormatter)}"
                if (data.mayCall)
                    rightPanel.setOnClickListener { clickListener(data) }
                else
                    rightPanel.visibility = View.GONE
            }
        }
    }

    inner class AddLessonViewHolder(view: View) : BaseViewHolder(view) {

        private val viewBinding: AddLessonItemBinding by viewBinding()

        override fun bind(item: ListData, position: Int) {
            with(viewBinding) {
                val data = item as AddLesson
                lessonName.text = data.name
                val time = data.time
                val timeUntil = time.plusMinutes(data.duration)
                lessonTime.text =
                    "${data.time.format(dateTimeFormatter)} - ${timeUntil.format(dateTimeFormatter)}"
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].getType()
    }
}
