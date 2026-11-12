package com.example.okonomi.adapter;

import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;

import com.example.okonomi.BinderActivity;
import com.example.okonomi.R;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BinderAdapter extends RecyclerView.Adapter<BinderAdapter.ViewHolder> {
    /*
    private String[] titles = {
            "日記",
            "レシピ",
            "旅行",
            "授業ノート"
    };*/
    private ArrayList<String> titles =
            new ArrayList<>();

    public BinderAdapter() {

        titles.add("日記");
        titles.add("レシピ");
        titles.add("旅行");
        titles.add("授業ノート");

    }

    public void addTitle(String title) {

        titles.add(title);

        notifyDataSetChanged();

    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {
        ImageView imgCover;
        TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCover =
                    itemView.findViewById(R.id.imgCover);

            txtTitle =
                    itemView.findViewById(R.id.txtTitle);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_binder, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {
        holder.txtTitle.setText(
                titles.get(position)
        );
        holder.itemView.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            v.getContext(),
                            BinderActivity.class
                    );

            v.getContext().startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}
