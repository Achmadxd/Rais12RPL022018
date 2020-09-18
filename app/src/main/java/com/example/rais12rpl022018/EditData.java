package com.example.rais12rpl022018;

import android.content.Intent;
import android.os.Bundle;
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
    EditText txtNama, txtEmail, txtNoktp, txtNohp, txtAlamat;
    Button btnEdit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_edit);

        txtId = findViewById(R.id.txtId);
        txtNama = findViewById(R.id.txtNama);
        txtEmail = findViewById(R.id.txtEmail);
        txtNoktp = findViewById(R.id.txtNoktp);
        txtNohp = findViewById(R.id.txtNohp);
        txtAlamat = findViewById(R.id.txtAlamat);
        btnEdit = findViewById(R.id.btnEdit);

        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("id");
        final String nama = extras.getString("nama");
        final String email = extras.getString("email");
        final String nohp = extras.getString("nohp");
        final String alamat = extras.getString("alamat");
        final String noktp = extras.getString("noktp");

        txtId.setText("Id :" + id);
        txtNama.setText(nama);
        txtEmail.setText(email);
        txtNohp.setText(nohp);
        txtAlamat.setText(alamat);
        txtNoktp.setText(noktp);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.post(BaseUrl.url + "edit_data.php")
                        .addBodyParameter("id", id)
                        .addBodyParameter("nama", txtNama.getText().toString())
                        .addBodyParameter("email", txtEmail.getText().toString())
                        .addBodyParameter("nohp", txtNohp.getText().toString())
                        .addBodyParameter("alamat", txtAlamat.getText().toString())
                        .addBodyParameter("noktp", txtNoktp.getText().toString())
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject hasil = response.getJSONObject("hasil");
                                    boolean sukses = hasil.getBoolean("respon");
                                    if (sukses) {
                                        Intent returnIntent = new Intent(EditData.this, DataCustomer.class);
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

                            }
                        });
            }
        });

    }
}
