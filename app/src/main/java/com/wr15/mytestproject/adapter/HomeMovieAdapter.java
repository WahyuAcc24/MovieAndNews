package com.wr15.mytestproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.wr15.mytestproject.R;
import com.wr15.mytestproject.model.MFilm;
import com.wr15.mytestproject.server.ServerMovieHost;

import java.util.List;

public class HomeMovieAdapter extends RecyclerView.Adapter<HomeMovieAdapter.Holder> {

    private List<MFilm> mfilms;
    private ItemClickListener<MFilm> listenermfilm;

    private String TAG = HomeMovieAdapter.class.getSimpleName();

    private RequestQueue requestQueue;

    private Context mcontext;

    private double Rating;


    public HomeMovieAdapter(Context context, List<MFilm> Mfilms) {
        this.mfilms = Mfilms;
        this.mcontext = context;
    }


    public void setListener(ItemClickListener listenerMfilm) {
        this.listenermfilm = listenerMfilm;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_movie, parent, false));
    }


    @Override
    public void onBindViewHolder(HomeMovieAdapter.Holder holder, final int position) {

        final MFilm data_film = mfilms.get(position);

        Glide.with(holder.foto.getContext())
                .load(ServerMovieHost.URLIMAGE + data_film.getPoster_path())
                .error(R.drawable.ic_baseline_movie_24)
                .placeholder(R.drawable.ic_baseline_movie_24)
                .into(holder.foto);

        holder.nama.setText(data_film.getTitle());

        Rating = data_film.getVote_average();

        float newValue = (float)Rating;
        holder.ratingBar.setNumStars(5);
        holder.ratingBar.setStepSize((float) 0.5);
        holder.ratingBar.setRating(newValue / 2);




        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenermfilm.onClicked(mfilms.get(position), position, v);



            }
        });


    }

    @Override
    public int getItemCount() {
        return mfilms.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView nama;
        private ImageView foto;
        private LinearLayout item;
        private RatingBar ratingBar;

        public Holder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.linearFilm);
            nama = itemView.findViewById(R.id.txtNamaFilm);
            foto = (ImageView) itemView.findViewById(R.id.imgFilm);

            ratingBar = (RatingBar) itemView.findViewById(R.id.rtFilm);

        }

    }



}

