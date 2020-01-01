package id.ac.digind.gasskos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import id.ac.digind.gasskos.R;
import id.ac.digind.gasskos.models.Transaksi;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {

    private List<Transaksi> transaksiList;
    private OnItemRiwayatListener mListener;

    public RiwayatAdapter(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        mListener = (OnItemRiwayatListener) context;
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_riwayat, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            Transaksi transaksi = transaksiList.get(position);
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));

            viewHolder.tvNama.setText(transaksi.getPenginapan().getNama());
            viewHolder.tvStatus.setText(transaksi.getStatus());
            viewHolder.tvWaktu.setText(transaksi.getTanggalMasuk() + " s/d " + transaksi.getTanggalKeluar());
            viewHolder.tvHarga.setText(formatRupiah.format(transaksi.getTotalHarga()));
            Glide.with(viewHolder.itemView)
                    .load("https://gasskos.pradityanafiis.id/foto_penginapan/" + transaksi.getFoto())
                    .into(viewHolder.ivFotoPenginapan);
        }

        @Override
        public int getItemCount() {
            return transaksiList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public TextView tvNama;
            public TextView tvStatus;
            public TextView tvWaktu;
            public TextView tvHarga;
            public ImageView ivFotoPenginapan;
            CardView cardView;

            public ViewHolder(View itemView) {
                super(itemView);
                tvNama = itemView.findViewById(R.id.tv_namakos);
                tvStatus = itemView.findViewById(R.id.tv_status);
                tvWaktu = itemView.findViewById(R.id.tv_waktu);
                tvHarga = itemView.findViewById(R.id.tv_harga);
                ivFotoPenginapan = itemView.findViewById(R.id.imageViewFotoPenginapan);
                cardView = itemView.findViewById(R.id.cardview);
                cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.openDetailRiwayat(transaksiList.get(getAdapterPosition()).getIdTransaksi());
        }
    }

    public interface OnItemRiwayatListener {
        void openDetailRiwayat(Integer id);
    }
}
