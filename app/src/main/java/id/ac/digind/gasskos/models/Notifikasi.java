package id.ac.digind.gasskos.models;

import java.util.ArrayList;
public class Notifikasi {
    private String namakos;
    private String pesan;
    private String foto;
    private String waktu;

       
    public Notifikasi(String namakos, String pesan, String foto, String waktu) {
        this.namakos = namakos;
        this.pesan = pesan;
        this.foto = foto;
        this.waktu = waktu;    
    }

    public String getNamakos() {
        return namakos;
    }

    public String getPesan() {
        return pesan;
    }

    public String getFoto() {
        return foto;
    }

    public String getWaktu() {
        return waktu;
    }

    @Override
    public String toString() {
        return "Notifikasi{" +
                "namakos='" + namakos + '\'' +
                ", pesan='" + pesan + '\'' +
                ", foto='" + foto + '\'' +
                ", waktu='" + waktu + '\'' +
                '}';
    }

    public static ArrayList<Notifikasi> dummyData(int count) {
        ArrayList<Notifikasi> notif = new ArrayList<Notifikasi>();

        for (int i = 1; i <= count; i++) {
            notif.add(new Notifikasi("Kos Bu Ros " +i, "Anda mengirim pesan", "", "17.00" ));
        }

        return notif;
    }
}
