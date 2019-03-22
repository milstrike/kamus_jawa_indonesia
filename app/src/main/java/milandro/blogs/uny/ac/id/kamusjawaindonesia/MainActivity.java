package milandro.blogs.uny.ac.id.kamusjawaindonesia;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView Menu1, Menu2, MenuTentang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Menu1 = (ImageView) findViewById(R.id.jawaindonesia);
        Menu2 = (ImageView) findViewById(R.id.indonesiajawa);
        MenuTentang = (ImageView) findViewById(R.id.menutentang);

        AssetManager assetManager = this.getAssets();
        InputStream is1= null;
        InputStream is2= null;
        InputStream is3= null;

        try {
            is1 = assetManager.open("img/jawa-indonesia.png");
            is2 = assetManager.open("img/indonesia-jawa.png");
            is3 = assetManager.open("img/tentang.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap1 = BitmapFactory.decodeStream(is1);
        Bitmap bitmap2 = BitmapFactory.decodeStream(is2);
        Bitmap bitmap3 = BitmapFactory.decodeStream(is3);
        Menu1.setImageBitmap(bitmap1);
        Menu2.setImageBitmap(bitmap2);
        MenuTentang.setImageBitmap(bitmap3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void goToJawa(View V){
        Intent i = new Intent(MainActivity.this, JawaIndonesiaActivity.class);
        startActivity(i);
    }

    public void goToIndonesia(View V){
        Intent i = new Intent(MainActivity.this, IndonesiaJawaActivity.class);
        startActivity(i);
    }

    public void goToTentang(View V){
        Intent i = new Intent(MainActivity.this, TentangActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setMessage("Keluar dari aplikasi Kamus Jawa Indonesia?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(1);
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }


}
