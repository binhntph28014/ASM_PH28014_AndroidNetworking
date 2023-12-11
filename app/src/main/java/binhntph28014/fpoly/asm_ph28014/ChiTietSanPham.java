package binhntph28014.fpoly.asm_ph28014;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import binhntph28014.fpoly.asm_ph28014.api.apiService;

import com.squareup.picasso.Picasso;

import binhntph28014.fpoly.asm_ph28014.model.SanPham;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSanPham extends AppCompatActivity {
    ImageView imganh;
    TextView txtTenSach,txtNamSxSP,txtSoLuongSp,txtGiaSachSP, txtLoaiSachSP;
    
    SanPham lstSp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        imganh = findViewById(R.id.imgAnh_sanpham_chitiet);
        txtTenSach = findViewById(R.id.tvTen_sanpham_chitiet);
        txtNamSxSP = findViewById(R.id.tvGia_sanpham_chitiet);
        txtLoaiSachSP = findViewById(R.id.txtStatussp);
        txtGiaSachSP = findViewById(R.id.txtMotasp);
        Intent intent = getIntent();

        String _id = intent.getStringExtra("_idSp");
        CallApiChitietSp(_id);


//        txtTenSp.setText(lstSp.getNameproduct());
//        txtGiaSp.setText(lstSp.getPrice());
////        txtSoLuongSp.setText(lstSp.get);
//        txtMoTaSp.setText(lstSp.getDescription());

    }

    private void CallApiChitietSp(String id) {
        SanPham sanPham = new SanPham(id,null,null,null, null,null);

        apiService.Apiservice.ChitietSP(sanPham).enqueue(new Callback<SanPham>() {
            @Override
            public void onResponse(Call<SanPham> call, Response<SanPham> response) {
                if (response.isSuccessful()) {
                    lstSp = response.body();

                    if (lstSp != null) {

                        txtTenSach.setText(lstSp.getTensach());
                        txtNamSxSP.setText(lstSp.getNamsx());
                        txtLoaiSachSP.setText(lstSp.getLoaisach());
                        txtGiaSachSP.setText(lstSp.getGiaban());
                        Picasso.get().load(Uri.parse(lstSp.getImage())).into(imganh);

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


}