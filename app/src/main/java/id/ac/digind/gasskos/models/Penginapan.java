package id.ac.digind.gasskos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  Penginapan {
    @SerializedName("id_penginapan")
    @Expose
    private Integer idPenginapan;

    @SerializedName("id_users")
    @Expose
    private String idUsers;

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
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("telepon")
    @Expose
    private String telepon;

    public Integer getIdPenginapan() {
        return idPenginapan;
    }

    public String getIdUsers() {
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

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getTelepon() {
        return telepon;
    }
}
