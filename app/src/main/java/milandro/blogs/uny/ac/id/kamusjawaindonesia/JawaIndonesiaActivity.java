package milandro.blogs.uny.ac.id.kamusjawaindonesia;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by milstrike on 23/02/2016.
 */
public class JawaIndonesiaActivity extends AppCompatActivity {

    private ListView listView;
    private TextView cariJawa;
    private RelativeLayout LayoutTampilCari, mainlayout;
    private TextView textjawa, textindonesia;
    private LinearLayout tutupLayoutTampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jawa);

        listView = (ListView) findViewById(R.id.listviewjawa);
        cariJawa = (TextView) findViewById(R.id.searchJawa);
        mainlayout = (RelativeLayout) findViewById(R.id.mainLayout);
        LayoutTampilCari = (RelativeLayout) findViewById(R.id.tampilArtiLayout);
        tutupLayoutTampil = (LinearLayout) findViewById(R.id.tutupArtiLayout);
        textjawa = (TextView) findViewById(R.id.tampilJawa);
        textindonesia = (TextView) findViewById(R.id.tampilIndonesia);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> daftarJawa = databaseAccess.getKamusJawa();
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftarJawa);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = (listView.getItemAtPosition(position).toString());
                getArti(selectedFromList);
            }
        });

        cariJawa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listChanger(cariJawa.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    public void listChanger(String keyword){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> daftarJawa = databaseAccess.getSearchJawa(keyword);
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftarJawa);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = (listView.getItemAtPosition(position).toString());
                getArti(selectedFromList);
            }
        });
    }

    public void getArti(String jawa){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        String hasilArti = databaseAccess.getSelectedJawa(jawa);
        databaseAccess.close();

        textjawa.setText(jawa);
        textindonesia.setText(hasilArti);

        tampilLayoutArti();

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

    }

    public void tampilLayoutArti(){
        mainlayout.setEnabled(false);
        LayoutTampilCari.setVisibility(View.VISIBLE);
    }

    public void dismisstampilLayoutArti(){
        LayoutTampilCari.setVisibility(View.GONE);
        mainlayout.setEnabled(true);
    }

    public void closeInAction(View V){
        dismisstampilLayoutArti();
    }

}
