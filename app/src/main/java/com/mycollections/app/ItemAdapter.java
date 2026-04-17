package com.mycollections.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(CollectionItem item, int position);
    }

    private final List<CollectionItem>    items;
    private final OnDeleteClickListener   deleteListener;

    public ItemAdapter(List<CollectionItem> items, OnDeleteClickListener listener) {
        this.items          = items;
        this.deleteListener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder h, int position) {
        CollectionItem item = items.get(position);
        h.tvTitle.setText(item.getTitle());
        h.tvDescription.setText(item.getDescription() != null ? item.getDescription() : "");
        h.tvDate.setText(item.getDateAdded());
        h.btnDelete.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDeleteClick(item, h.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() { return items.size(); }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvDate;
        Button   btnDelete;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle       = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate        = itemView.findViewById(R.id.tv_item_date);
            btnDelete     = itemView.findViewById(R.id.btn_delete);
        }
    }
}
