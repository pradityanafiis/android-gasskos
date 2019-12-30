package id.ac.digind.gasskos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailPenginapanResponse {
    @SerializedName("penginapan")
    @Expose
    private Penginapan penginapan;

    @SerializedName("fasilitas")
    @Expose
    private List<Fasilitas> fasilitasList;

    @SerializedName("kamar")
    @Expose
    private List<Kamar> kamarList;

    @SerializedName("foto")
    @Expose
    private List<FotoPenginapan> fotoPenginapanList;

    public Penginapan getPenginapan() {
        return penginapan;
    }

    public List<Fasilitas> getFasilitasList() {
        return fasilitasList;
    }

    public List<Kamar> getKamarList() {
        return kamarList;
    }

    public List<FotoPenginapan> getFotoPenginapanList() {
        return fotoPenginapanList;
    }

    public String fasilitasToString(List<Fasilitas> listFasilitas) {
        String fasilitasString = "";
        for (Fasilitas fasilitas: listFasilitas) {
            fasilitasString = fasilitasString + fasilitas.getNama() + " ";
        }
        return fasilitasString;
    }
}
