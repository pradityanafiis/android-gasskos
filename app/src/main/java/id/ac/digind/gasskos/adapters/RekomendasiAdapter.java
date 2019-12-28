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

public class RekomendasiAdapter extends RecyclerView.Adapter<RekomendasiAdapter.ViewHolder> {

    private List<Kost> dataList;

    public RekomendasiAdapter(List<Kost> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_rekomendasi, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Kost kost = dataList.get(position);

        viewHolder.tvNama.setText(kost.getNama());
        viewHolder.tvHarga.setText("Rp " + kost.getHarga());
        viewHolder.tvAlamat.setText(kost.getAlamat());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama;
        public TextView tvHarga;
        public TextView tvAlamat;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.textViewNama);
            tvHarga = itemView.findViewById(R.id.textViewGender);
            tvAlamat = itemView.findViewById(R.id.textViewAlamat);
        }
    }
}
