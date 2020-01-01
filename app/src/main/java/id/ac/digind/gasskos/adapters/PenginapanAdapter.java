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
import java.util.List;
import id.ac.digind.gasskos.R;
import id.ac.digind.gasskos.models.Penginapan;


public class PenginapanAdapter extends RecyclerView.Adapter<PenginapanAdapter.PenginapanViewHolder> {

    private Context mCtx;
    private List<Penginapan> penginapanList;
    private OnItemRvClicked mListener;

    public PenginapanAdapter(Context mCtx, List<Penginapan> penginapanList) {
        this.mCtx = mCtx;
        this.mListener = (OnItemRvClicked) mCtx;
        this.penginapanList = penginapanList;
    }

    @NonNull
    @Override
    public PenginapanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_penginapan, viewGroup, false);
        return new PenginapanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenginapanViewHolder penginapanViewHolder, int i) {
        Penginapan penginapan = penginapanList.get(i);

        penginapanViewHolder.textViewNama.setText(penginapan.getNama());
        penginapanViewHolder.textViewGender.setText(penginapan.getGender());
        penginapanViewHolder.textViewAlamat.setText(penginapan.getAlamat());
        Glide.with(penginapanViewHolder.itemView)
                .load("https://gasskos.pradityanafiis.id/foto_penginapan/" + penginapan.getFoto())
                .into(penginapanViewHolder.imageViewFotoPenginapan);
    }

    @Override
    public int getItemCount() {
        return penginapanList.size();
    }

    class PenginapanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewNama, textViewGender, textViewAlamat;
        ImageView imageViewFotoPenginapan;
        CardView cardViewPenginapan;

        public PenginapanViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNama = itemView.findViewById(R.id.textViewNama);
            textViewGender = itemView.findViewById(R.id.textViewGender);
            textViewAlamat = itemView.findViewById(R.id.textViewAlamat);
            imageViewFotoPenginapan = itemView.findViewById(R.id.imageViewFotoPenginapan);
            cardViewPenginapan = itemView.findViewById(R.id.cardViewKamar);
            cardViewPenginapan.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.goToDetailActivity(penginapanList.get(getAdapterPosition()));
        }
    }

    public interface OnItemRvClicked {
        void goToDetailActivity(Penginapan penginapan);
    }
}
