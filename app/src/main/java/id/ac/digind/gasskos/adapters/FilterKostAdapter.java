package id.ac.digind.gasskos.adapters;

import android.content.Context;
import android.content.SharedPreferences;
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
import id.ac.digind.gasskos.models.Kost;
import id.ac.digind.gasskos.models.Penginapan;

public class FilterKostAdapter extends RecyclerView.Adapter<FilterKostAdapter.ViewHolder> {

    private Context context;
    private List<Penginapan> penginapanList;
    private OnItemFilterClicked mListener;

    public FilterKostAdapter(List<Penginapan> penginapanList, Context context) {
        this.penginapanList = penginapanList;
        this.mListener = (OnItemFilterClicked) context;
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

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));

        try {
            if (penginapan.getMin().equals(penginapan.getMax())) {
                viewHolder.tvHarga.setText(formatRupiah.format(penginapan.getMin()));
            } else {
                viewHolder.tvHarga.setText(formatRupiah.format(penginapan.getMin()) + "-" + formatRupiah.format(penginapan.getMax()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Glide.with(viewHolder.itemView)
                .load("https://gasskos.pradityanafiis.id/foto_penginapan/" + penginapan.getFoto())
                .into(viewHolder.imageViewFotoPenginapan);
    }

    @Override
    public int getItemCount() {
        return penginapanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvNama;
        public TextView tvHarga;
        public TextView tvAlamat;
        public TextView tvGender;
        public ImageView imageViewFotoPenginapan;
        CardView cardViewPenginapan;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.textViewNama);
            tvHarga = itemView.findViewById(R.id.textViewHarga);
            tvAlamat = itemView.findViewById(R.id.textViewAlamat);
            tvGender = itemView.findViewById(R.id.textViewGender);
            imageViewFotoPenginapan = itemView.findViewById(R.id.imageViewFotoPenginapan);
            cardViewPenginapan = itemView.findViewById(R.id.cardViewKamar);
            cardViewPenginapan.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.goToDetailActivity(penginapanList.get(getAdapterPosition()));
        }
    }

    public interface OnItemFilterClicked {
        void goToDetailActivity(Penginapan penginapan);
    }
}
