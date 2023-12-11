package binhntph28014.fpoly.asm_ph28014.model;

import com.google.gson.annotations.SerializedName;

public class SanPham {

    private String _id;
    @SerializedName("tensach")
    private String tensach;
    @SerializedName("namsx")
    private String namsx;
    @SerializedName("loaisach")
    private String loaisach;
    private String image;
    @SerializedName("giaban")
    private String giaban;


    public SanPham(String _id, String tensach, String namsx, String loaisach, String image, String giaban) {
        this._id = _id;
        this.tensach = tensach;
        this.namsx = namsx;
        this.loaisach = loaisach;
        this.image = image;
        this.giaban = giaban;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getNamsx() {
        return namsx;
    }

    public void setNamsx(String namsx) {
        this.namsx = namsx;
    }

    public String getLoaisach() {
        return loaisach;
    }

    public void setLoaisach(String loaisach) {
        this.loaisach = loaisach;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGiaban() {
        return giaban;
    }

    public void setGiaban(String giaban) {
        this.giaban = giaban;
    }
}
