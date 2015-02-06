package pruebas.manuel.geekstorming;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pruebas.manuel.geekstorming.util.Entry;
import pruebas.manuel.geekstorming.util.EntryListAdapter;
import pruebas.manuel.geekstorming.util.RssParserSax;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ProgressDialog pDialog;

    private ListView listView;
    private ArrayAdapter<Entry> adapter;
    private String link = "https://geekstorming.wordpress.com/feed/";
    private List<Entry> entradas;
    private List<Entry> entradasAux;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();
        empezarTarea();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(MainActivity.this, EntryActivity.class);
        i.putExtra("title", entradas.get(position).getTitle());
        i.putExtra("content", entradas.get(position).getContent());
        //Esto es de la animacion
                /*ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        MainActivity.this,

                        // Now we provide a list of Pair items which contain the view we can transitioning
                        // from, and the name of the view it is transitioning to, in the launched activity

                        new Pair<View, String>(view.findViewById(R.id.title),
                               "TransicionTitle"));

                // Now we can start the Activity, providing the activity options as a bundle
                ActivityCompat.startActivity(MainActivity.this, i, activityOptions.toBundle());*/

        startActivity(i);
    }

    private void inicializarComponentes() {
        listView = (ListView) findViewById(R.id.listView);
        adapter = new EntryListAdapter(this, new ArrayList<Entry>());
        adapter.setNotifyOnChange(true);
        listView.setOnItemClickListener(this); //Esto le hace override al onitemclick
        listView.setAdapter(adapter);
        entradas = new ArrayList<>();
    }

    private void empezarTarea() {
        new CargarXmlTask().execute(link);
    }

    private class CargarXmlTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Mostrar progreso de carga
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Cargando...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected Boolean doInBackground(String... params) {
            RssParserSax saxparser =
                    new RssParserSax(params[0]);
            entradasAux = saxparser.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (entradasAux.isEmpty()) {
                Toast.makeText(MainActivity.this, "No hay conexion a internet", Toast.LENGTH_LONG).show();
            } else {
                entradas = entradasAux;
                if (!adapter.isEmpty()) {
                    adapter.clear();
                }
                for (int i = 0; i < entradas.size(); i++) {
                    adapter.add(entradas.get(i));
                }
            }
            // Dejar de mostrar proceso de carga
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, ConfigurationActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_refresh) {
            empezarTarea();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}