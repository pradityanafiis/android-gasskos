package id.ac.digind.gasskos.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;
import id.ac.digind.gasskos.API.RetrofitClient;
import id.ac.digind.gasskos.R;
import id.ac.digind.gasskos.adapters.PenginapanAdapter;
import id.ac.digind.gasskos.models.Penginapan;
import id.ac.digind.gasskos.models.PenginapanResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private Context context;
    private RecyclerView recyclerView;
    private PenginapanAdapter adapter;
    private List<Penginapan> penginapanList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_rekomendasi);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("GassKos_Shared_Preferences", Context.MODE_PRIVATE);
        Call<PenginapanResponse> getPenginapan = RetrofitClient.getInstance().getAPI().getPenginapan(sharedPreferences.getString("token", ""));
        getPenginapan.enqueue(new Callback<PenginapanResponse>() {
            @Override
            public void onResponse(Call<PenginapanResponse> call, Response<PenginapanResponse> response) {
                penginapanList = response.body().getPenginapans();
                adapter = new PenginapanAdapter(context, penginapanList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PenginapanResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}