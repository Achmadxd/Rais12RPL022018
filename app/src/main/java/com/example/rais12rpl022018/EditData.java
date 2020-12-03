package com.example.rais12rpl022018;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class EditData extends AppCompatActivity {

    TextView txtId;
    EditText txtKode, txtJenis, txtMerk, txtWarna, txtSewa;
    Button btnEdit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_edit);

        txtId = findViewById(R.id.txtId);
        txtKode = findViewById(R.id.txtKode);
        txtJenis = findViewById(R.id.txtJenis);
        txtMerk = findViewById(R.id.txtMerk);
        txtWarna = findViewById(R.id.txtWarna);
        txtSewa = findViewById(R.id.txtHargaSewa);
        btnEdit = findViewById(R.id.btnEdit);

        Bundle extras = getIntent().getExtras();
        final String id = String.valueOf(extras.getInt("id"));
        final String kode = extras.getString("kode");
        final String jenis = extras.getString("jenis");
        final String merk = extras.getString("merk");
        final String warna = extras.getString("warna");
        final String sewa = String.valueOf(extras.getInt("sewa"));

        txtId.setText("Id :" + id);
        txtKode.setText(kode);
        txtJenis.setText(jenis);
        txtWarna.setText(warna);
        txtSewa.setText(sewa);
        txtMerk.setText(merk);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("clicked", "yes");
                AndroidNetworking.post(BaseUrl.url + "update_data.php")
                        .addBodyParameter("id", id)
                        .addBodyParameter("kode", txtKode.getText().toString())
                        .addBodyParameter("merk", txtMerk.getText().toString())
                        .addBodyParameter("jenis", txtJenis.getText().toString())
                        .addBodyParameter("warna", txtWarna.getText().toString())
                        .addBodyParameter("hargasewa", txtSewa.getText().toString())
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String status = response.getString("STATUS");
                                    if (status.equals("SUCCESS")) {
                                        Intent returnIntent = new Intent(EditData.this, ViewData.class);
                                        returnIntent.putExtra("refresh", "refresh");
                                        startActivity(returnIntent);
                                        finish();
                                        Toast.makeText(EditData.this, "Edit Suskses", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(EditData.this, "Edit gagal", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.d("eror", anError.getErrorBody());
                            }
                        });
            }
        });

    }
}
