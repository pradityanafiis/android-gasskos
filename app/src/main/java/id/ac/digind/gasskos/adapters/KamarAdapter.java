package id.ac.digind.gasskos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.Locale;
import java.text.NumberFormat;
import id.ac.digind.gasskos.R;
import id.ac.digind.gasskos.models.Kamar;


public class KamarAdapter extends RecyclerView.Adapter<KamarAdapter.KamarViewHolder> {

    private Context mCtx;
    private List<Kamar> kamarList;

    public KamarAdapter(Context mCtx, List<Kamar> kamarList) {
        this.mCtx = mCtx;
        this.kamarList = kamarList;
    }

    @NonNull
    @Override
    public KamarAdapter.KamarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_kamar, viewGroup, false);
        return new KamarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KamarAdapter.KamarViewHolder kamarViewHolder, int i) {
        Kamar kamar = kamarList.get(i);
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));

        kamarViewHolder.textViewJenisKamar.setText(kamar.getTipe() + " (" + kamar.getKapasitas() + " orang)");
        kamarViewHolder.textViewHargaKamar.setText(formatRupiah.format((double)kamar.getHarga()));
    }

    @Override
    public int getItemCount() {
        return kamarList.size();
    }

    public class KamarViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewJenisKamar, textViewHargaKamar;

        public KamarViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewJenisKamar = itemView.findViewById(R.id.textViewJenisKamar);
            textViewHargaKamar = itemView.findViewById(R.id.textViewHargaKamar);
        }
    }
}
