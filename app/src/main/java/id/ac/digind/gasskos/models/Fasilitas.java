package id.ac.digind.gasskos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fasilitas {
    @SerializedName("nama")
    @Expose
    private String nama;

    public String getNama() {
        return nama;
    }
}
