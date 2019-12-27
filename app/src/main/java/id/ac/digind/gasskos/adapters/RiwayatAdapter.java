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
import id.ac.digind.gasskos.models.Riwayat;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {

    private List<Riwayat> dataList;

    public RiwayatAdapter(List<Riwayat> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_riwayat, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Riwayat riwayat = dataList.get(position);

        viewHolder.tvNama.setText(riwayat.getNamakos());
        viewHolder.tvStatus.setText(riwayat.getStatus());
        viewHolder.tvWaktuBayar.setText(riwayat.getWaktuBayar());
        viewHolder.tvId.setText(riwayat.getId());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama;
        public TextView tvStatus;
        public TextView tvWaktuBayar;
        public TextView tvId;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_namakos);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvWaktuBayar = itemView.findViewById(R.id.tv_waktubayar);
            tvId = itemView.findViewById(R.id.tv_id);
        }
    }
}
