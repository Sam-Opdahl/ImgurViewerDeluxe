package com.test.sam.imgviewerdeluxe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImgRecyclerView extends RecyclerView.Adapter<ImgViewHolder> {
    private List<GenericImgurData.JsonData> jsonDataList;
    private Context context;

    public ImgRecyclerView(Context context, List<GenericImgurData.JsonData> jsonDataList) {
        this.context = context;
        this.jsonDataList = jsonDataList;
    }

    @Override
    public ImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tile, null);
        ImgViewHolder holder = new ImgViewHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImgViewHolder holder, int position) {
        Picasso.with(context)
                .load(jsonDataList.get(position).getLink())
                .fit()
                .tag(context)
                .into(holder.getImageView());
        holder.getTextView().setText(jsonDataList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return this.jsonDataList.size();
    }
}
