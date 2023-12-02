package binhntph28014.fpoly.asm_ph28014.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import binhntph28014.fpoly.asm_ph28014.model.DeleteSp;
import binhntph28014.fpoly.asm_ph28014.model.SanPham;
import binhntph28014.fpoly.asm_ph28014.model.auth;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        apiService Apiservice = new Retrofit.Builder() .baseUrl("http://192.168.56.1:3000/")
                .addConverterFactory(GsonConverterFactory
                .create(gson))
                .build().create(apiService.class);


    @GET("sanpham")
    Call<List<SanPham>> getallSP();
    @POST("sanpham")
    Call <SanPham> addSP(@Body SanPham sanPham);

    @POST("xoasanpham")
    Call <DeleteSp> xoaSP(@Body DeleteSp deleteSp);

    @POST("signin")
    Call<auth> checkSignIn(@Body auth Auth);

    @POST("reg")
    Call<auth> postU(@Body auth Auth);

    @POST("upsanpham")
    Call<SanPham> updateSP(@Body SanPham sanPham);

    @POST("chitiet")
    Call<SanPham> ChitietSP(@Body SanPham sanPham);


}
