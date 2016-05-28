package com.test.sam.imgviewerdeluxe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ImgViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView textView;

    public ImageView getImageView() {
        return this.imageView;
    }

    public TextView getTextView() {
        return this.textView;
    }

    public ImgViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.tile_image);
        textView = (TextView) itemView.findViewById(R.id.tile_description);
    }
}
