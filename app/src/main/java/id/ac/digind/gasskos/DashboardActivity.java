package id.ac.digind.gasskos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import id.ac.digind.gasskos.adapters.PenginapanAdapter;
import id.ac.digind.gasskos.models.Penginapan;
import id.ac.digind.gasskos.storage.SharedPreferencesManager;

public class DashboardActivity extends AppCompatActivity implements PenginapanAdapter.OnItemRvClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_profile).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!SharedPreferencesManager.getInstance(DashboardActivity.this).isLogin()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notification_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.notif:
                startActivity(new Intent(this, NotifikasiActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void goToDetailActivity(Penginapan penginapan) {
        //TODO : kirim data ke detail activity pakai bundle/intent extras
        Bundle bundle = new Bundle();
        bundle.putInt("id_penginapan", penginapan.getIdPenginapan());
        startActivity(new Intent(this, DetailKostActivity.class).putExtras(bundle));
    }
}
