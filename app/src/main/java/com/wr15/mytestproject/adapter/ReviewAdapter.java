package com.wr15.mytestproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wr15.mytestproject.R;
import com.wr15.mytestproject.model.MFilm;
import com.wr15.mytestproject.model.MReview;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.Holder> {

    private List<MReview> mReviews;


    private Context mcontext;



    public ReviewAdapter(Context context, List<MReview> mReviews) {
        this.mReviews = mReviews;
        this.mcontext = context;


    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bs_review, parent, false));

    }


    @Override
    public void onBindViewHolder(final ReviewAdapter.Holder holder, final int position) {

        final MReview data_review = mReviews.get(position);

        holder.txt_user.setText(data_review.getUsername());


        holder.txt_komen.setText(data_review.getContent());


    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView txt_user, txt_komen;


        public Holder(View itemView) {
            super(itemView);

            txt_user = itemView.findViewById(R.id.txtNamaRating);
            txt_komen = itemView.findViewById(R.id.txtKomen);




        }

    }


}

