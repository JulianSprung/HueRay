package com.example.hueray.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hueray.R
import com.example.hueray.database.HappyScore

class HappyScoreListAdapter internal constructor(
    context: Context?
) : RecyclerView.Adapter<HappyScoreListAdapter.HappyScoreViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var scores = emptyList<HappyScore>() // Cached copy of scores

    inner class HappyScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scoreItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HappyScoreViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return HappyScoreViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HappyScoreViewHolder, position: Int) {
        val current = scores[position]
        holder.scoreItemView.text = current.score.toString()
    }

    internal fun setScores(scores: List<HappyScore>) {
        this.scores = scores
        notifyDataSetChanged()
    }

    override fun getItemCount() = scores.size
}