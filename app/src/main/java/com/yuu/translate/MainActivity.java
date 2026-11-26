package com.yuu.translate;
import android.app.*; import android.os.*; import android.content.*; import android.provider.Settings; import android.widget.*;
import com.google.mlkit.nl.translate.*;
public class MainActivity extends Activity {
 Translator translator;
 public void onCreate(Bundle b){super.onCreate(b); LinearLayout l=new LinearLayout(this); l.setOrientation(1); l.setPadding(32,48,32,32); setContentView(l);
 TextView t=new TextView(this); t.setText("YuuTranslate\nChinese → English\n\nDownload the model, enable accessibility, then open your app. Tap EN to translate the current screen; tap × to clear. Screen text is processed locally."); t.setTextSize(20); l.addView(t);
 Button d=new Button(this); d.setText("Download offline model"); l.addView(d); translator=Translation.getClient(new TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.CHINESE).setTargetLanguage(TranslateLanguage.ENGLISH).build()); d.setOnClickListener(v->{d.setEnabled(false); translator.downloadModelIfNeeded().addOnSuccessListener(x->{d.setText("Model ready");d.setEnabled(true);}).addOnFailureListener(e->{d.setText("Retry download");d.setEnabled(true);});});
 Button a=new Button(this); a.setText("Enable accessibility service"); l.addView(a); a.setOnClickListener(v->startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))); }
 protected void onDestroy(){if(translator!=null)translator.close();super.onDestroy();}
}
