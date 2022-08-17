package com.wr15.mytestproject.adapter;

import android.view.View;

public interface ItemClickListener<M> {
    void onClicked(M flm, int position, View view);

}
