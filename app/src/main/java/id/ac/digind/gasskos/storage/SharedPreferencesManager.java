package id.ac.digind.gasskos.storage;

import android.content.Context;
import android.content.SharedPreferences;

import id.ac.digind.gasskos.models.User;

public class SharedPreferencesManager {
    private static final String SHARED_PREF_NAME = "GassKos_Shared_Preferences";
    private static SharedPreferencesManager mInstance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private Context mCtx;

    public SharedPreferencesManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized SharedPreferencesManager getInstance(Context mCtx) {
        if (mInstance == null){
            mInstance = new SharedPreferencesManager(mCtx);
            sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return mInstance;
    }

    public void saveUser(User user, String token) {
        editor.putString("token", "Bearer " + token);
        editor.putInt("id_user", user.getId());
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.apply();
    }

    public boolean isLogin() {
        if (!sharedPreferences.getString("token", "").equals("")){
            return true;
        }
        return false;
    }

    public User getUser() {
        return new User(sharedPreferences.getInt("id_user", 1), sharedPreferences.getString("name", null), sharedPreferences.getString("email", null));
    }

    public String getToken() {
        return sharedPreferences.getString("token", null);
    }

    public void updateUser(String nama, String email){
        editor.putString("name", nama);
        editor.putString("email", email);
        editor.apply();
    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
