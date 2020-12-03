package com.example.rais12rpl022018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class AddData extends AppCompatActivity {

    EditText txt_kode, txt_merk, txt_warna, txt_jenis, txt_harga;
    Button btn_tambah;
    Bundle bundle;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        txt_kode = findViewById(R.id.et_kode);
        txt_merk = findViewById(R.id.et_merk);
        txt_harga = findViewById(R.id.et_hargasewa);
        txt_warna = findViewById(R.id.et_warna);
        txt_jenis = findViewById(R.id.et_jenis);
        btn_tambah = findViewById(R.id.btn_tambah);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("id")+1;
            Log.d("id", ""+id);
        }

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_kode.getText().toString().trim().equals("") || txt_merk.getText().toString().trim().equals("") || txt_harga.getText().toString().equals("") || txt_warna.getText().toString().equals("") || txt_jenis.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Isi datanya!", Toast.LENGTH_LONG).show();
                } else {
                    AndroidNetworking.post(BaseUrl.url + "add_data.php")
                            .addBodyParameter("id", String.valueOf(id))
                            .addBodyParameter("kode", txt_kode.getText().toString())
                            .addBodyParameter("merk", txt_merk.getText().toString())
                            .addBodyParameter("jenis", txt_jenis.getText().toString())
                            .addBodyParameter("warna", txt_warna.getText().toString())
                            .addBodyParameter("hargasewa", txt_harga.getText().toString())
                            .setPriority(Priority.LOW)
                            .build().
                            getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String status = response.getString("STATUS");
                                        if (status.equals("SUCCESS")) {
                                            Intent returnIntent = new Intent(getApplicationContext(), ViewData.class);
                                            returnIntent.putExtra("refresh", "refresh");
                                            startActivity(returnIntent);
                                            finish();
                                            Toast.makeText(getApplicationContext(), "Sukses menambahkan barang", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Gagal menambahkan barang", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        Log.d("error", e.toString());
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {
                                    Log.d("error", anError.getErrorBody());
                                }
                            });
                }
            }
        });
    }
}