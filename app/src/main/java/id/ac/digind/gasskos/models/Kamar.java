package id.ac.digind.gasskos.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kamar implements Parcelable {
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

    protected Kamar(Parcel in) {
        if (in.readByte() == 0) {
            idKamar = null;
        } else {
            idKamar = in.readInt();
        }
        if (in.readByte() == 0) {
            idPenginapan = null;
        } else {
            idPenginapan = in.readInt();
        }
        tipe = in.readString();
        if (in.readByte() == 0) {
            harga = null;
        } else {
            harga = in.readInt();
        }
        if (in.readByte() == 0) {
            kapasitas = null;
        } else {
            kapasitas = in.readInt();
        }
        if (in.readByte() == 0) {
            jumlah = null;
        } else {
            jumlah = in.readInt();
        }
    }

    public static final Creator<Kamar> CREATOR = new Creator<Kamar>() {
        @Override
        public Kamar createFromParcel(Parcel in) {
            return new Kamar(in);
        }

        @Override
        public Kamar[] newArray(int size) {
            return new Kamar[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (idKamar == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idKamar);
        }
        if (idPenginapan == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idPenginapan);
        }
        dest.writeString(tipe);
        if (harga == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(harga);
        }
        if (kapasitas == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(kapasitas);
        }
        if (jumlah == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(jumlah);
        }
    }
}
