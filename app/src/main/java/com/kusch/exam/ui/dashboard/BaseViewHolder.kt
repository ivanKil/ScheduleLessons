package com.kusch.exam.ui.dashboard

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kusch.exam.model.data.ListData

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(dataItem: ListData, position: Int)
}