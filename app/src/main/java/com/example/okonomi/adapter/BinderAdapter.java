package com.example.okonomi.adapter;
import android.view.ViewGroup;
import android.view.View;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import androidx.recyclerview.widget.RecyclerView;
import com.example.okonomi.R;
import android.widget.ImageView;
import android.widget.TextView;
public class BinderAdapter extends RecyclerView.Adapter<BinderAdapter.ViewHolder>
{
    private String[] titles = {
            "日記",
            "レシピ",
            "旅行",
            "授業ノート"
    };

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
                titles[position]
        );
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
