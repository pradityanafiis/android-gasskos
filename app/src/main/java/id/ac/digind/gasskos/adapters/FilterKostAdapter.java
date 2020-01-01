package id.ac.digind.gasskos.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.ac.digind.gasskos.R;
import id.ac.digind.gasskos.models.Kost;
import id.ac.digind.gasskos.models.Penginapan;

public class FilterKostAdapter extends RecyclerView.Adapter<FilterKostAdapter.ViewHolder> {

    private Context context;
    private List<Penginapan> penginapanList;

    public FilterKostAdapter(List<Penginapan> penginapanList) {
        this.penginapanList = penginapanList;
    }

    public void clearItems() {
        penginapanList.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_penginapan, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Penginapan penginapan = penginapanList.get(position);

        viewHolder.tvNama.setText(penginapan.getNama());
        viewHolder.tvGender.setText(penginapan.getGender());
        viewHolder.tvAlamat.setText(penginapan.getAlamat());

        Glide.with(viewHolder.itemView)
                .load("https://gasskos.pradityanafiis.id/foto_penginapan/" + penginapan.getFoto())
                .into(viewHolder.imageViewFotoPenginapan);
    }

    @Override
    public int getItemCount() {
        return penginapanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama;
        public TextView tvHarga;
        public TextView tvAlamat;
        public TextView tvGender;
        public ImageView imageViewFotoPenginapan;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.textViewNama);
            //tvHarga = itemView.findViewById(R.id.textViewGender);
            tvAlamat = itemView.findViewById(R.id.textViewAlamat);
            tvGender = itemView.findViewById(R.id.textViewGender);
            imageViewFotoPenginapan = itemView.findViewById(R.id.imageViewFotoPenginapan);
        }
    }
}
