package binhntph28014.fpoly.asm_ph28014.model;

import com.google.gson.annotations.SerializedName;

public class SanPham {

    private String _id;
    @SerializedName("tensv")
    private String tensv;
    @SerializedName("tuoi")
    private String tuoi;
    @SerializedName("status")
    private String status;
    private String image;
    @SerializedName("diemtb")
    private String diemtb;


    public SanPham(String _id, String tensv, String tuoi, String status, String image, String diemtb) {
        this._id = _id;
        this.tensv = tensv;
        this.tuoi = tuoi;
        this.status = status;
        this.image = image;
        this.diemtb = diemtb;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getTuoi() {
        return tuoi;
    }

    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiemtb() {
        return diemtb;
    }

    public void setDiemtb(String diemtb) {
        this.diemtb = diemtb;
    }
}
