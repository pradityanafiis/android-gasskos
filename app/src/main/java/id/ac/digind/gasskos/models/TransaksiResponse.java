package id.ac.digind.gasskos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransaksiResponse {
    @SerializedName("transaksi")
    @Expose
    private List<Transaksi> transaksi;

    public List<Transaksi> getTransaksi() {
        return transaksi;
    }
}
