package binhntph28014.fpoly.asm_ph28014.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import binhntph28014.fpoly.asm_ph28014.ChiTietSanPham;
import binhntph28014.fpoly.asm_ph28014.UpdateSp;
import binhntph28014.fpoly.asm_ph28014.api.apiService;

import com.squareup.picasso.Picasso;

import java.util.List;

import binhntph28014.fpoly.asm_ph28014.R;
import binhntph28014.fpoly.asm_ph28014.model.DeleteSp;
import binhntph28014.fpoly.asm_ph28014.model.SanPham;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder>{


    private List<SanPham> ltsSP;


    private Context context;


    public SanPhamAdapter(List<SanPham> ltsSP , Context context) {
        this.ltsSP = ltsSP;
        this.context = context;
    }



    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPham sanPham = ltsSP.get(position);
        if (sanPham == null){
            return;
        }
        String _id = ltsSP.get(position).get_id();
        String imgSrc = ltsSP.get(position).getImage();
        holder.txtTenSp.setText(sanPham.getTensach());
        holder.txtMoTaSP.setText(sanPham.getGiaban());
        holder.txtStatusSP.setText(sanPham.getLoaisach());
        //holder.txtSoLuongSP.setText(sanPham.getImage());
        holder.txtGiaSP.setText(sanPham.getNamsx());
        Picasso.get().load(Uri.parse(imgSrc)).into(holder.imgSP);
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO",Context.MODE_PRIVATE);
        String role = sharedPreferences.getString("role","");

        if (role.equals("1")){

            holder.btnDelete.setVisibility(View.GONE);
            holder.btnEdit.setVisibility(View.GONE);
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setMessage("Bạn chắc chắn muốn xóa? ")
                        .setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int adapterPosition = holder.getAdapterPosition();
                                callApiXoaSP(_id,adapterPosition);
                            }
                        })
                        .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();

            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateSp.class);
                intent.putExtra("_idSp",_id);
                context.startActivity(intent);
            }


        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietSanPham.class);
                intent.putExtra("_idSp",_id);
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        if (ltsSP != null){
            return ltsSP.size();

        }
        return 0;
    }

    class SanPhamViewHolder extends RecyclerView.ViewHolder{

        TextView txtTenSp,txtMoTaSP,txtGiaSP,txtSoLuongSP, txtStatusSP;
        ImageView imgSP;
        ImageButton btnEdit,btnDelete;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTenSp = itemView.findViewById(R.id.idTenSanPham);
            txtMoTaSP = itemView.findViewById(R.id.idMoTaSanPham);
            txtGiaSP = itemView.findViewById(R.id.idGiaSanPham);
            txtStatusSP = itemView.findViewById(R.id.idStatusSanPham);

            imgSP = itemView.findViewById(R.id.imgSanPham);
            btnEdit = itemView.findViewById(R.id.btnSua);
            btnDelete = itemView.findViewById(R.id.btnXoa);
        }
    }
    private void callApiXoaSP(String _id, int position){

        DeleteSp deleteSp = new DeleteSp(_id);
        apiService.Apiservice.xoaSP(deleteSp).enqueue(new Callback<DeleteSp>() {
            @Override
            public void onResponse(Call<DeleteSp> call, Response<DeleteSp> response) {
                if (response.isSuccessful()) {
                    ltsSP.remove(position);
                    notifyDataSetChanged();

                } else {
                    Log.e("API_CALL_ERROR", "Error code: " + response.code());

                }
            }

            @Override
            public void onFailure(Call<DeleteSp> call, Throwable t) {
                Log.e("API_CALL_ERROR", "Error: " + t.getMessage());


            }
        });
    }
    private void upDateSp(String _id , String imgsrc ,String NameSp,int MoTa,int gia, int status) {



    }
    private void callApiUpdateSp(String edtten, String edtImg, String edtGia, String edtmota, String status) {
            SanPham sanPham = new SanPham(null,edtten,edtGia,edtmota,edtImg, status);

            apiService.Apiservice.updateSP(sanPham).enqueue(new Callback<SanPham>() {
                @Override
                public void onResponse(Call<SanPham> call, Response<SanPham> response) {
                    if (response.isSuccessful()) {

                        notifyDataSetChanged();

                    } else {
                        Log.e("API_CALL_ERROR", "Error code: " + response.code());

                    }
                }

                @Override
                public void onFailure(Call<SanPham> call, Throwable t) {

                }
            });

    }
}
