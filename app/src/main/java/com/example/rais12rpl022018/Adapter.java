package com.example.rais12rpl022018;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.UserViewHolder> {

    private ArrayList<Model> dataList;
    View viewku;

    public Adapter(ArrayList<Model> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.itemview, parent, false);
        return new UserViewHolder(viewku);

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        holder.tvNama.setText(dataList.get(position).getNama());
        holder.tvEmail.setText(dataList.get(position).getEmail());
        holder.cvInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditCustomer.class);
                intent.putExtra("id", dataList.get(position).getId());
                intent.putExtra("nama", dataList.get(position).getNama());
                intent.putExtra("email", dataList.get(position).getEmail());
                intent.putExtra("nohp", dataList.get(position).getNohp());
                intent.putExtra("alamat", dataList.get(position).getAlamat());
                intent.putExtra("noktp", dataList.get(position).getNoktp());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNama, tvEmail;
        CardView cvInbox;

        UserViewHolder(View itemView) {
            super(itemView);
            cvInbox = itemView.findViewById(R.id.cvInbox);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }

}
