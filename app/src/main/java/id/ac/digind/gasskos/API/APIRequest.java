package id.ac.digind.gasskos.API;

import id.ac.digind.gasskos.models.DetailPenginapanResponse;
import id.ac.digind.gasskos.models.DetailTransaksiResponse;
import id.ac.digind.gasskos.models.LoginResponse;
import id.ac.digind.gasskos.models.PenginapanResponse;
import id.ac.digind.gasskos.models.StandartResponse;
import id.ac.digind.gasskos.models.TransaksiResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIRequest {
    @FormUrlEncoded
    @POST("auth/register")
    Call<ResponseBody> register(
        @Field("name") String name,
        @Field("email") String email,
        @Field("password") String password,
        @Field("password_confirmation") String password_confirmation
    );

    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResponse> login(
        @Field("email") String email,
        @Field("password") String password
    );

    @GET("penginapan")
    Call<PenginapanResponse> getAllPenginapan(@Header("Authorization") String token);

    @GET("penginapan/n")
    Call<PenginapanResponse> getPenginapan(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("penginapan/i")
    Call<DetailPenginapanResponse> getDetailPenginapan(
        @Header("Authorization") String token,
        @Field("id_penginapan") Integer id_penginapan
    );

    @FormUrlEncoded
    @POST("penginapan/g")
    Call<PenginapanResponse> getPenginapanGender(
        @Header("Authorization") String token,
        @Field("gender") String gender
    );

    @FormUrlEncoded
    @POST("penginapan/r")
    Call<PenginapanResponse> getPenginapanRating (
        @Header("Authorization") String token,
        @Field("gender") String star
    );

    @FormUrlEncoded
    @POST("penginapan/h")
    Call<PenginapanResponse> getPenginapanHarga (
        @Header("Authorization") String token,
        @Field("harga_bawah") String hargaBawah,
        @Field("harga_atas") String hargaAtas
    );

    @FormUrlEncoded
    @POST("transaksi")
    Call<TransaksiResponse> getTransaksi (
        @Header("Authorization") String token,
        @Field("id_user") Integer id_user
    );

    @FormUrlEncoded
    @POST("transaksi/i")
    Call<DetailTransaksiResponse> getDetailTransaksi (
            @Header("Authorization") String token,
            @Field("id_transaksi") Integer id_transaksi
    );

    @FormUrlEncoded
    @POST("transaksi/store")
    Call<StandartResponse> storeTransaksi(
        @Header("Authorization") String token,
        @Field("id_users") Integer id_users,
        @Field("id_kamar") Integer id_kamar,
        @Field("tanggal_masuk") String tanggal_masuk,
        @Field("durasi") Integer durasi
    );

    @FormUrlEncoded
    @POST("transaksi/ulasan")
    Call<StandartResponse> ulasan(
        @Header("Authorization") String token,
        @Field("id_transaksi") Integer id_transaksi,
        @Field("rating") Integer rating,
        @Field("komentar") String komentar
    );

}
