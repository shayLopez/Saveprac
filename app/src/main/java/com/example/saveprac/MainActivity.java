package com.example.saveprac;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button infob;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    TextView tVinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initcomp();
        infob.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        readRawFile();

    }



});
    }
    public void readRawFile() {
        try {
            // פתיחת הקובץ וחיבור שרשרת הצינורות
            is = getResources().openRawResource(R.raw.info);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            // הכנת משתנה הטקסט שיאגור הכל, וקריאת השורה הראשונה
            String allText = "";
            String line = br.readLine();
            // כל עוד השורה אינה ריקה (לא סוף הקובץ)
            while (line != null) {
                allText = allText + line + "\n";
                line = br.readLine();
            }
            // הצגת הטקסט שנאסף וסגירת ערוץ הקריאה
            tVinfo.setText(allText);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initcomp()
    {
        infob=findViewById(R.id.infoB);
        tVinfo=findViewById(R.id.tVinfo);
    }
}