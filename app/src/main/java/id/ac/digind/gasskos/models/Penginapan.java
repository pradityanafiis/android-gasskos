package id.ac.digind.gasskos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  Penginapan {
    @SerializedName("id_penginapan")
    @Expose
    private Integer idPenginapan;
    @SerializedName("id_users")
    @Expose
    private Integer idUsers;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("telepon")
    @Expose
    private String telepon;
    @SerializedName("foto")
    @Expose
    private String foto;

    public Integer getIdPenginapan() {
        return idPenginapan;
    }

    public Integer getIdUsers() {
        return idUsers;
    }

    public String getGender() {
        return gender;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getTelepon() {
        return telepon;
    }

    public String getFoto() {
        return foto;
    }
}
