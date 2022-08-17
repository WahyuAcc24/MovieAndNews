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
import com.wr15.mytestproject.model.MNews;
import com.wr15.mytestproject.server.ServerMovieHost;

import java.util.List;

public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.Holder> {

    private List<MNews> mNewss;
    private ItemClickListener<MNews> listenermNews;

    private String TAG = HomeNewsAdapter.class.getSimpleName();

    private RequestQueue requestQueue;

    private Context mcontext;

    private double Rating;


    public HomeNewsAdapter(Context context, List<MNews> MNewss) {
        this.mNewss = MNewss;
        this.mcontext = context;
    }


    public void setListener(ItemClickListener listenerMNews) {
        this.listenermNews = listenerMNews;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news, parent, false));
    }


    @Override
    public void onBindViewHolder(HomeNewsAdapter.Holder holder, final int position) {

        final MNews data_News = mNewss.get(position);

        Glide.with(holder.foto.getContext())
                .load(data_News.getUrlToImage())
                .error(R.drawable.ic_baseline_movie_24)
                .placeholder(R.drawable.ic_baseline_movie_24)
                .into(holder.foto);

        holder.nama.setText(data_News.getTitle());



        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenermNews.onClicked(mNewss.get(position), position, v);



            }
        });


    }

    @Override
    public int getItemCount() {
        return mNewss.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView nama;
        private ImageView foto;
        private LinearLayout item;

        public Holder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.linearNews);
            nama = itemView.findViewById(R.id.txtJudulNews);
            foto = (ImageView) itemView.findViewById(R.id.imgNews);


        }

    }



}

