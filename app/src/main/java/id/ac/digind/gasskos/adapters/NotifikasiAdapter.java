package id.ac.digind.gasskos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.ac.digind.gasskos.R;
import id.ac.digind.gasskos.models.Kost;
import id.ac.digind.gasskos.models.Notifikasi;

public class NotifikasiAdapter extends RecyclerView.Adapter<NotifikasiAdapter.ViewHolder> {

    private List<Notifikasi> dataList;

    public NotifikasiAdapter(List<Notifikasi> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_notifikasi, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Notifikasi notif = dataList.get(position);

        viewHolder.tvNama.setText(notif.getNamakos());
        viewHolder.tvPesan.setText(notif.getPesan());
        viewHolder.tvWaktu.setText(notif.getWaktu());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama;
        public TextView tvPesan;
        public TextView tvWaktu;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_namakos);
            tvPesan = itemView.findViewById(R.id.tv_pesan);
            tvWaktu = itemView.findViewById(R.id.tv_waktu);
        }
    }
}
