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
    @SerializedName("total_harga")
    @Expose
    private Integer total_harga;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("komentar")
    @Expose
    private String komentar;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("kamar")
    @Expose
    private Kamar kamar;
    @SerializedName("penginapan")
    @Expose
    private Penginapan penginapan;

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

    public Integer getTotalHarga() {
        return total_harga;
    }

    public String getStatus() {
        return status;
    }

    public Integer getRating() {
        return rating;
    }

    public String getKomentar() {
        return komentar;
    }

    public String getFoto() {
        return foto;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public Penginapan getPenginapan() {
        return penginapan;
    }
}
