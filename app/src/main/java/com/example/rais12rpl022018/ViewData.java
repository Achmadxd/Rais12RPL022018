package com.example.rais12rpl022018;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterSepeda adapter;

    ArrayList<modelsepeda> datalist = new ArrayList<>();

    TextView tvKode;
    TextView tvMerk;
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);


        tvKode = findViewById(R.id.tvKode);
        tvMerk = findViewById(R.id.tvMerk);
        recyclerView = findViewById(R.id.rvSepedaManage);
        btnAdd = findViewById(R.id.btn_add_sepeda);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewData.this, AddData.class);
                if (datalist.size() != 0) {
                    int id = datalist.get(datalist.size()-1).getId();
                    intent.putExtra("id", id);
                }
                startActivity(intent);
            }
        });

        AndroidNetworking.get(BaseUrl.url + "viewdataSepeda.php")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response);
                            JSONArray data = response.getJSONArray("result");

                            for (int i = 0; i < data.length(); i++) {

                                modelsepeda model = new modelsepeda();
                                JSONObject object = data.getJSONObject(i);
                                model.setId(object.getInt("id"));
                                model.setKode(object.getString("kode"));
                                model.setMerk(object.getString("merk"));
                                model.setJenis(object.getString("jenis"));
                                model.setWarna(object.getString("warna"));
                                model.setHargasewa(object.getInt("hargasewa"));
                                datalist.add(model);
                            }

                            adapter = new AdapterSepeda(getApplicationContext(), datalist);

                            //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view_data.this);

                            recyclerView.setAdapter(adapter);

                            if (response.getJSONArray("PAYLOAD").length() == 0){

                                recyclerView.setVisibility(View.GONE);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("geo", "onResponse: " + anError.toString());
                        Log.d("geo", "onResponse: " + anError.getErrorBody());
                        Log.d("geo", "onResponse: " + anError.getErrorCode());
                        Log.d("geo", "onResponse: " + anError.getErrorDetail());
                    }
                });
    }
}