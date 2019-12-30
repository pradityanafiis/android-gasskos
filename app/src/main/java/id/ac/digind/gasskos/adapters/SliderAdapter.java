package id.ac.digind.gasskos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

import id.ac.digind.gasskos.R;
import id.ac.digind.gasskos.models.FotoPenginapan;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {
    private Context context;
    private List<FotoPenginapan> fotoPenginapans;

    public SliderAdapter(Context context, List<FotoPenginapan> fotoPenginapans) {
        this.context = context;
        this.fotoPenginapans = fotoPenginapans;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        viewHolder.textViewDescription.setText("Foto penginapan " + position);
        FotoPenginapan foto = fotoPenginapans.get(position);
        Glide.with(viewHolder.itemView)
                .load("https://gasskos.pradityanafiis.id/foto_penginapan/" + foto.getPath())
                .into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {
        return fotoPenginapans.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
