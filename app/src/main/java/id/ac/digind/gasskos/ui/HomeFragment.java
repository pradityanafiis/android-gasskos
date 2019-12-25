package id.ac.digind.gasskos.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import java.util.List;

import id.ac.digind.gasskos.R;
import id.ac.digind.gasskos.adapters.RekomendasiAdapter;
import id.ac.digind.gasskos.models.Kost;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rv_rekomendasi);
        List<Kost> dataList = Kost.dummyData(10);
        RekomendasiAdapter adapter = new RekomendasiAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }
}