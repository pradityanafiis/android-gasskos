package id.ac.digind.gasskos.models;

import java.util.ArrayList;

public class Kost {
    private String nama;
    private double harga;
    private String alamat;
    private String foto;

    public Kost(String nama, double harga, String alamat, String foto) {
        this.nama = nama;
        this.harga = harga;
        this.alamat = alamat;
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getFoto() {
        return foto;
    }

    @Override
    public String toString() {
        return "Kost{" +
                "nama='" + nama + '\'' +
                ", harga=" + harga +
                ", alamat='" + alamat + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }

    public static ArrayList<Kost> dummyData(int count) {
        ArrayList<Kost> kosts = new ArrayList<Kost>();

        for (int i = 1; i <= count; i++) {
            kosts.add(new Kost("Kost " + i, 300000, "Jalan-jalan", ""));
        }

        return kosts;
    }
}
