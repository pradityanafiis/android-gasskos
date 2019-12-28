package id.ac.digind.gasskos.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import id.ac.digind.gasskos.LoginActivity;
import id.ac.digind.gasskos.R;
import id.ac.digind.gasskos.models.User;
import id.ac.digind.gasskos.storage.SharedPreferencesManager;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private TextView textViewName, textViewEmail;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewName = view.findViewById(R.id.textViewNama);
        textViewEmail = view.findViewById(R.id.textViewEmail);
        view.findViewById(R.id.cardViewLogout).setOnClickListener(this);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("GassKos_Shared_Preferences", Context.MODE_PRIVATE);
        textViewName.setText(sharedPreferences.getString("name", ""));
        textViewEmail.setText(sharedPreferences.getString("email", ""));
    }

    public void logout() {
        SharedPreferencesManager.getInstance(getActivity()).clear();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        logout();
    }
}