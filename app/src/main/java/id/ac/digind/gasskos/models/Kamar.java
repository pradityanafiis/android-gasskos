package id.ac.digind.gasskos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kamar {
    @SerializedName("id_kamar")
    @Expose
    private Integer idKamar;

    @SerializedName("id_penginapan")
    @Expose
    private Integer idPenginapan;

    @SerializedName("tipe")
    @Expose
    private String tipe;

    @SerializedName("harga")
    @Expose
    private Integer harga;

    @SerializedName("kapasitas")
    @Expose
    private Integer kapasitas;

    @SerializedName("jumlah")
    @Expose
    private Integer jumlah;

    public Integer getIdKamar() {
        return idKamar;
    }

    public Integer getIdPenginapan() {
        return idPenginapan;
    }

    public String getTipe() {
        return tipe;
    }

    public Integer getHarga() {
        return harga;
    }

    public Integer getKapasitas() {
        return kapasitas;
    }

    public Integer getJumlah() {
        return jumlah;
    }
}
