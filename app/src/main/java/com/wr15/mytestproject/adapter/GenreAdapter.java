package com.wr15.mytestproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wr15.mytestproject.R;
import com.wr15.mytestproject.model.MGenre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.Holder> {

    private List<MGenre> mgenres;



    private String TAG_GENRE = GenreAdapter.class.getSimpleName();


    private Context mcontext;




    public GenreAdapter(Context context, List<MGenre> mgenres) {
        this.mgenres = mgenres;
        this.mcontext = context;


    }



    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false));
    }


    @Override
    public void onBindViewHolder(final GenreAdapter.Holder holder, final int position) {

        holder.genre.setText(mgenres.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return mgenres.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView genre;

        public Holder(View itemView) {
            super(itemView);

            genre = itemView.findViewById(R.id.txtGenreDetail);


        }

    }



}

