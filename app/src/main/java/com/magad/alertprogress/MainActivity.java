package com.magad.alertprogress;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LayoutInflater inflater;
    AlertDialog.Builder dialog;
    private View dialogView;
    Handler dly = new Handler();

    TextView tv;
    Button btn;
    Runnable runCheck, runMain;
    int progressDownload;


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        tv = findViewById(R.id.progressbar);
        btn = findViewById(R.id.btnprogress);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogShow();
            }
        });
    }

    private void dialogShow() {
        dialog = new AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialoglayout, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(android.R.mipmap.sym_def_app_icon);
        dialog.setTitle("from bioadata");
        final TextInputEditText edtname, edtaddress, edtage, edtquote;

        edtaddress = dialogView.findViewById(R.id.edtemail);
        edtname = dialogView.findViewById(R.id.edtname);
        edtage = dialogView.findViewById(R.id.edtumur);
        edtquote = dialogView.findViewById(R.id.edtquote);


        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (edtname.getText().length() <= 3 &&
                        edtage.getText().length() <= 3 &&
                        edtaddress.getText().length() <= 3 &&
                        edtquote.getText().length() <= 3) {

                    edtname.setError("antum belum menginput sebuah text");
                    edtage.setError("antum belum menginput sebuah text");
                    edtaddress.setError("antum belum menginput sebuah text");
                    edtquote.setError("antum belum menginput sebuah text");
                } else {
                    startActivity(new Intent(MainActivity.this, ResultActivity.class)
                            .putExtra("name", edtname.getText().toString())
                            .putExtra("age", edtage.getText().toString())
                            .putExtra("adress", edtaddress.getText().toString())
                            .putExtra("quote", edtquote.getText().toString())


                    );
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnsprogress(final View view) {
        progressDialog = new ProgressDialog(MainActivity.this, ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Loading");
        progressDialog.setMax(100);
        progressDialog.setMessage("proses Mengambil data");
        progressDialog.setIcon(R.mipmap.ic_launcher_round);
        progressDialog.show();
        progressDownload = 0;

        runMain = new Runnable() {
            @Override
            public void run() {
                while(progressDownload < 100) {
                    progressDownload += 20 ;
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    dly.post(runCheck);
                }
            }
        };
        runCheck = new Runnable() {
            @Override
            public void run() {
                progressDialog.setProgress(progressDownload);
                if(progressDownload >= 100) {
                    Snackbar.make(view, "Download Selesai", Snackbar.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }

            }
        };
        Thread jalan = new Thread(runMain);
        jalan.start();
    }
}
