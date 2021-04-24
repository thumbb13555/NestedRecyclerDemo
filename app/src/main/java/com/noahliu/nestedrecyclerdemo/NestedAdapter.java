package com.noahliu.nestedrecyclerdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.NesHolder> {

    private List<MyData.NestedData> nestedData;
    private OnChildClick childClick;
    private int parentPosition;

    public NestedAdapter(List<MyData.NestedData> nestedData,int parentPosition, OnChildClick childClick) {
        this.nestedData = nestedData;
        this.childClick = childClick;
        this.parentPosition = parentPosition;
    }

    public class NesHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public NesHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.textView_nesTitle);
        }
    }

    @NonNull
    @Override
    public NesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NesHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nested, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NesHolder holder, int position) {

        MyData.NestedData item = nestedData.get(position);
        holder.tvTitle.setText(item.getNesTitle());

        holder.itemView.setOnClickListener(v -> {
            //取得點擊到的item，並使用interface回傳
            childClick.onChildClick(item,parentPosition);
        });
    }

    @Override
    public int getItemCount() {
        return nestedData.size();
    }

    interface OnChildClick {
        void onChildClick(MyData.NestedData data,int parentPosition);
    }

}
