package id.ac.digind.gasskos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaksi {
    @SerializedName("id_transaksi")
    @Expose
    private Integer idTransaksi;
    @SerializedName("id_users")
    @Expose
    private Integer idUsers;
    @SerializedName("id_kamar")
    @Expose
    private Integer idKamar;
    @SerializedName("tanggal_masuk")
    @Expose
    private String tanggalMasuk;
    @SerializedName("tanggal_keluar")
    @Expose
    private String tanggalKeluar;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    public Integer getIdUsers() {
        return idUsers;
    }

    public Integer getIdKamar() {
        return idKamar;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }

    public String getStatus() {
        return status;
    }
}
