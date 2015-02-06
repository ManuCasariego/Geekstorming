package pruebas.manuel.geekstorming;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Manuel on 04/02/2015.
 */
public class ConfigurationActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, new ConfigurationFragment());
        transaction.commit();
    }
}
