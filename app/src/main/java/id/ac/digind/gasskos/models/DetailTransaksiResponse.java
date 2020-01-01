package id.ac.digind.gasskos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailTransaksiResponse {
    @SerializedName("transaksi")
    @Expose
    private Transaksi transaksi;

    public Transaksi getTransaksi() {
        return transaksi;
    }
}
