package com.example.saveprac;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button infob,entButton,rButton,lgoutB;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    TextView tVinfo;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PreferenceManager pref=new PreferenceManager(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initcomp();
        infob.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        readRawFile();

    }



});
        if (!pref.isAutoLog())
            lgoutB.setAlpha(0.0f);
        else
            lgoutB.setAlpha(1);
        lgoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref.setAutolog(false);
            }
        });

    entButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            writeToInternal( "\n"+editText.getText().toString());
        }
    });
    rButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            readFromInternal();
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
    // 1. משתנה גלובלי (צינור מסוג יציאה/כתיבה)
    FileOutputStream fosS;
    // 2. פונקציית כתיבה - מקבלת את הטקסט שצריך לשמור
    public void writeToInternal(String textToSave) {
        try {
            // יצירה או פתיחה של הקובץ במצב פרטי ונעול
            fosS = openFileOutput("savedinfo.txt", MODE_APPEND);
            // כתיבת הטקסט (חובה להשתמש ב-getBytes)
            fosS.write(textToSave.getBytes());
            // סגירת הערוץ לשמירה סופית
            fosS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 1. משתנים גלובליים
    FileInputStream fis;
    InputStreamReader isR;
    BufferedReader bR;
    // 2. פונקציית הקריאה (זהה כמעט ל-Raw)
    public void readFromInternal() {
        try {
            // ההבדל: משתמשים ב-openFileInput
            fis = openFileInput("savedinfo.txt");
            isR = new InputStreamReader(fis);
            bR = new BufferedReader(isR);
            // שאר הקוד זהה למה שלמדנו...
            String allText = "";
            String line = bR.readLine();
            while (line != null) {
                allText = allText + line + "\n";
                line = bR.readLine();
            }
            tVinfo.setText(allText);
            bR.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initcomp()
    {   lgoutB=findViewById(R.id.button5);
        rButton=findViewById(R.id.button3);
        editText=findViewById(R.id.editTextText);
        infob=findViewById(R.id.infoB);
        entButton=findViewById(R.id.button2);
        tVinfo=findViewById(R.id.tVinfo);
    }
}