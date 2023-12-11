package binhntph28014.fpoly.asm_ph28014;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import binhntph28014.fpoly.asm_ph28014.api.apiService;


import binhntph28014.fpoly.asm_ph28014.model.SanPham;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateSp extends AppCompatActivity {

    SanPham lstSp;
    EditText edtTen,edtMota,edtgia,edtimg, edStatus;
    Button btnupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sp);

         edtTen = findViewById(R.id.edtTenSPUpdate);
         edtMota= findViewById(R.id.edtMoTaSPUpdate);
         edtgia = findViewById(R.id.edtGiaSPUpdate);
        edtimg = findViewById(R.id.edtImgSPUpdate);
         btnupdate = findViewById(R.id.btnUpdate);
         edStatus = findViewById(R.id.edtStatusSPUpdate);

        Intent intent = getIntent();
        String _id = intent.getStringExtra("_idSp");
        CallApiChitietSp(_id);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edtten = edtTen.getText().toString();
                String edtmota = edtMota.getText().toString();
                String edtGia = edtgia.getText().toString();
                String edtImg = edtimg.getText().toString();
                String edtStatus = edStatus.getText().toString();

                callApUpSP(_id,edtten ,
                        edtmota ,
                        edtGia ,
                        edtImg,
                        edtStatus);
            }
        });

    }

    private void CallApiChitietSp(String id) {
        SanPham sanPham = new SanPham(id,null,null,null, null,null);

        apiService.Apiservice.ChitietSP(sanPham).enqueue(new Callback<SanPham>() {
            @Override
            public void onResponse(Call<SanPham> call, Response<SanPham> response) {
                if (response.isSuccessful()) {
                    lstSp = response.body();

                    if (lstSp != null) {

                        edtTen.setText(lstSp.getTensv());
                        edtgia.setText(lstSp.getTuoi());
                        edStatus.setText(lstSp.getStatus());
                        edtMota.setText(lstSp.getDiemtb());
                        edtimg.setText(lstSp.getImage());

                    }

                    Log.i("Thành công" ,"OBJ:" + response.body());
                } else {
                    Log.e("API_CALL_ERROR", "Error code: " + response.code());

                }

            }

            @Override
            public void onFailure(Call<SanPham> call, Throwable t) {
                Log.i("Thất bại" ,"lỗi:" + t.getMessage());
            }
        });
    }


    private void callApUpSP(String _id,String edtten, String edtImg, String edtGia, String edtmota, String edtstatus){

        SanPham sanPham = new SanPham(_id,edtten,edtGia,edtmota, edtstatus,edtImg);
        apiService.Apiservice.updateSP(sanPham).enqueue(new Callback<SanPham>() {
            @Override
            public void onResponse(Call<SanPham> call, Response<SanPham> response) {
                if (response.isSuccessful()) {

                    Intent intent = new Intent(UpdateSp.this,
                            MainActivity.class);
                    startActivity(intent);


                } else {
                    Log.e("API_CALL_ERROR", "Error code: " + response.code());
                    Toast.makeText(UpdateSp.this, "Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SanPham> call, Throwable t) {
                Log.e("API_CALL_ERROR", "Error: " + t.getMessage());

                Toast.makeText(UpdateSp.this,"Thất Bại",Toast.LENGTH_SHORT).show();
            }
        });
    }
}