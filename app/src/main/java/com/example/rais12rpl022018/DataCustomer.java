package com.example.rais12rpl022018;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataCustomer extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;

    ArrayList<Model> datalist;

    TextView tvNama, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datacostumer_page);

        tvNama = findViewById(R.id.tvNama);
        tvEmail = findViewById(R.id.tvEmail);
        recyclerView = findViewById(R.id.listCustomer);

        datalist = new ArrayList<>();
        Log.d("geo", "onCreate: ");

        AndroidNetworking.get(BaseUrl.url + "viewdata.php")
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

                                Model model = new Model();
                                JSONObject object = data.getJSONObject(i);
                                model.setId(object.getString("id"));
                                model.setEmail(object.getString("email"));
                                model.setNama(object.getString("nama"));
                                model.setNohp(object.getString("nohp"));
                                model.setAlamat(object.getString("alamat"));
                                model.setNoktp(object.getString("noktp"));
                                datalist.add(model);

                            }

                            adapter = new Adapter(datalist);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataCustomer.this);

                            recyclerView.setLayoutManager(layoutManager);

                            recyclerView.setAdapter(adapter);

                            Log.d("pay1", "onResponse: " + response.getJSONArray("PAYLOAD"));

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
