package pruebas.manuel.geekstorming;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Manuel on 04/02/2015.
 */
public class ConfigurationFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.configuration);
    }


}
