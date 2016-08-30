package com.example.uran.testapplication;

import android.os.SystemClock;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements TextWatcher{

    EditText editText;
    TextView textView;
    ImageView imageView;
    String[] tableauChampion = {"azir", "quinn", "ahri", "fizz", "rengar", "viktor"};
    int i = 0;
    int resId;
    int resId2;
    int pos;
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.inputText);
        editText.addTextChangedListener(this);
        chronometer = (Chronometer)findViewById(R.id.chronometer);
        chronometer.start();

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String str = editText.getText().toString();
        textView = (TextView)findViewById(R.id.result);

        if(Arrays.asList(tableauChampion).contains(str)){
            i++;
            if (i == 6){
                chronometer.stop();
                long score = SystemClock.elapsedRealtime() - chronometer.getBase();
                int hours = (int) (score / 3600000);
                int minutes = (int) (score - hours * 3600000) / 60000;
                int scoreInSeconds = (int) (score - hours * 3600000 - minutes * 60000) / 1000;
                new AlertDialog.Builder(this).setTitle("Score").setMessage("Felicitation ! Vous avez trouvé tous les personnages en " + scoreInSeconds + " secondes").setNeutralButton("Terminer", null).show();
            }
            textView.setText("Vous en avez trouvé " + i);
            resId = this.getResources().getIdentifier(str, "drawable", this.getPackageName());
            resId2 = this.getResources().getIdentifier("imageMark"+i, "id", this.getPackageName());
            pos = Arrays.asList(tableauChampion).indexOf(str);
            tableauChampion[pos] = "null";
            imageView = (ImageView)findViewById(resId2);
            imageView.setImageResource(resId);
            editText.setText("");
        }



    }
}
