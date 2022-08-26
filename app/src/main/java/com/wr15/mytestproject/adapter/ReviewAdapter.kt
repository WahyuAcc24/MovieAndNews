package com.wr15.mytestproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wr15.mytestproject.R
import com.wr15.mytestproject.model.MReview

class ReviewAdapter(private val mcontext: Context, private val mReviews: List<MReview>) :
    RecyclerView.Adapter<ReviewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_bs_review, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data_review = mReviews[position]
        holder.txt_user.text = data_review.username
        holder.txt_komen.text = data_review.content
    }

    override fun getItemCount(): Int {
        return mReviews.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_user: TextView
        val txt_komen: TextView

        init {
            txt_user = itemView.findViewById(R.id.txtNamaRating)
            txt_komen = itemView.findViewById(R.id.txtKomen)
        }
    }
}