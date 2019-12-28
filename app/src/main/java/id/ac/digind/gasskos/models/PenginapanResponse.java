package id.ac.digind.gasskos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PenginapanResponse {

    @SerializedName("penginapan")
    @Expose
    private List<Penginapan> penginapans;

    public List<Penginapan> getPenginapans() {
        return penginapans;
    }
}
