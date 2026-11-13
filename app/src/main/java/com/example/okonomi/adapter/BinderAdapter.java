package com.example.okonomi.adapter;

import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;

import com.example.okonomi.R;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.okonomi.BinderDataProto.Binder;


public class BinderAdapter extends RecyclerView.Adapter<BinderAdapter.ViewHolder> {

    private List<Binder> binders = new ArrayList<>();

    public BinderAdapter() {
        //仮データ消しました。
    }

    public void setBinders(List<Binder> newBinders) {
        this.binders = newBinders;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover;
        TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCover = itemView.findViewById(R.id.imgCover);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_binder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Binder binder = binders.get(position);
        holder.txtTitle.setText(binder.getTitle());
    }

    @Override
    public int getItemCount() {
        return binders.size();
    }
}