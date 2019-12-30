package id.ac.digind.gasskos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FotoPenginapan {
    @SerializedName("path")
    @Expose
    private String path;

    public String getPath() {
        return path;
    }
}
