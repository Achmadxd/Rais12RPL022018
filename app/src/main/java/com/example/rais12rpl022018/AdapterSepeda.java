package com.example.rais12rpl022018;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.ArrayList;

public class AdapterSepeda extends RecyclerView.Adapter<AdapterSepeda.UserViewHolder> {
    private ArrayList<modelsepeda> dataList;
    View viewku;
    private OnItemClickListener mListener;
    Context mContext;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public AdapterSepeda(Context mContext, ArrayList<modelsepeda> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.item_admin_sepeda_layout, parent, false);
        return new AdapterSepeda.UserViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        AndroidNetworking.initialize(mContext);

        holder.tvMerk.setText(dataList.get(position).getMerk());
        holder.tvKode.setText(dataList.get(position).getKode());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditData.class);
                intent.putExtra("id", dataList.get(position).getId());
                Log.d("test id", "id: " + dataList.get(position).getId());
                Log.d("sewa", "harga: " + dataList.get(position).getHargasewa());
                intent.putExtra("kode", dataList.get(position).getKode());
                intent.putExtra("merk", dataList.get(position).getMerk());
                intent.putExtra("jenis", dataList.get(position).getJenis());
                intent.putExtra("warna", dataList.get(position).getWarna());
                intent.putExtra("sewa", dataList.get(position).getHargasewa());
                view.getContext().startActivity(intent);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("clicked", "yeyy");
                final int userId = dataList.get(position).getId();
                AndroidNetworking.post(BaseUrl.url + "delete_data.php")
                        .addBodyParameter("id", String.valueOf(userId))
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                dataList.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Data berhasil dihapus", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(ANError anError) {
                                dataList.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Data berhasil dihapus", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvKode, tvMerk;
        private LinearLayout cvInbox;
        RelativeLayout layout;
        ImageView imageView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKode = itemView.findViewById(R.id.tvKode);
            tvMerk = itemView.findViewById(R.id.tvMerk);
            cvInbox = itemView.findViewById(R.id.divdetail);
            layout = itemView.findViewById(R.id.layout_1);
            imageView = itemView.findViewById(R.id.ivDelete);
        }
    }
}
