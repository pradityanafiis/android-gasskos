package id.ac.digind.gasskos.models;

import java.util.ArrayList;

public class Riwayat {
    private String foto;
    private String status;
    private  String namakos;
    private String waktuBayar;
    private String id;

    public Riwayat(String foto, String status, String namakos, String waktuBayar, String id) {
        this.foto = foto;
        this.status = status;
        this.namakos = namakos;
        this.waktuBayar = waktuBayar;
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public String getStatus() {
        return status;
    }

    public String getNamakos() {
        return namakos;
    }

    public String getWaktuBayar() {
        return waktuBayar;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Riwayat{" +
                "foto='" + foto + '\'' +
                ", status='" + status + '\'' +
                ", namakos='" + namakos + '\'' +
                ", waktu='" + waktuBayar + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public static ArrayList<Riwayat> dummyData(int count) {
        ArrayList<Riwayat> riwayat = new ArrayList<Riwayat>();

        for (int i = 1; i <= count; i++) {
            riwayat.add(new Riwayat("", (i < 8 ? "Selesai":"Belum"), "Kos Bu Ros","Dibayar pada 12/12/2019","8119000001"));
        }

        return riwayat;
    }
}
