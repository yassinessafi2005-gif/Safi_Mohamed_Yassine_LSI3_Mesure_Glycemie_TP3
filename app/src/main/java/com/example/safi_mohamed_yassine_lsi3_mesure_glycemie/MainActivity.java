package com.example.safi_mohamed_yassine_lsi3_mesure_glycemie;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private SeekBar sbAge;
    private Button btnConsulter;
    private RadioButton rboui, rbnon;
    private EditText etValeur;
    private TextView textAge;
    private TextView msg;
    private int age = 0;
    private void init(){
        sbAge=findViewById(R.id.sbAge);
        rboui=findViewById(R.id.rbtoui);
        rbnon=findViewById(R.id.rbtnon);
        etValeur=findViewById(R.id.etValeur);
        textAge=findViewById(R.id.textAge);
        msg=findViewById(R.id.msg);
        btnConsulter=findViewById(R.id.btnConsulter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("information","onProgressChanged"+progress);
                textAge.setText("Votre age: "+progress+" ans");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { consulterGlycemie(); }
        });
    }
    private void consulterGlycemie(){
        String resultat ;
        String valeurString = etValeur.getText().toString().trim();
        if(valeurString.isEmpty()){
            Toast.makeText(this,"Veuillez entre la valeur mesurée",Toast.LENGTH_SHORT).show();
            return;
        }
        double valeur = Double.parseDouble(valeurString);
        boolean aJeune = rboui.isChecked();
        double min,max;
        if(aJeune){
            if(age>13){
                min=5.0;
                max=7.2;
            }
            else if(age>=6){
                min=5.0;
                max=10.0;
            }
            else{
                min=5.5;
                max=10.0;
            }
        }
        else{
            min=0.0;
            max=10.5;
        }
        if(valeur<min){
            resultat="Le niveau de glycemie est trop bas";
        }
        else if(valeur>max){
            resultat="Le niveau de glycemie est trop élevé";
        }
        else{
            resultat="Le niveau de glycemie est normal";
        }
        msg.setText(resultat);
    }
}