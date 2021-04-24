package com.noahliu.nestedrecyclerdemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements NestedAdapter.OnChildClick {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<MyData> myData;
    private OnItemClick onItemClick;
    public MyAdapter(List<MyData> myData,OnItemClick onItemClick) {
        this.myData = myData;
        this.onItemClick = onItemClick;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private RecyclerView recyclerView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.textView_Title);
            recyclerView = itemView.findViewById(R.id.recyclerview_Nested);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(myData.get(position).getTitle());
        //設置巢狀RecyclerView
        holder.recyclerView.setAdapter(new NestedAdapter(myData.get(position).getNesData()
                ,position,this));
        holder.recyclerView.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        return myData.size();
    }
    /**此處為點選Child-Item後從
     * @see NestedAdapter 的回傳*/

    @Override
    public void onChildClick(MyData.NestedData data, int parentPosition) {
        onItemClick.onItemClick(data,myData.get(parentPosition));
    }
    interface OnItemClick{
        void onItemClick(MyData.NestedData data,MyData myData);
    }
}
