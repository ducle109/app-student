
package jp.com.studentproject;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Espresso_Demo extends AppCompatActivity {
    private TextView testEspresso;
    private Button btnEspresso;
    private Button btnChangeText;
    private Button btnYes;
    private Button btnNo;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espresso__demo);
        testEspresso = (TextView) findViewById(R.id.test_espresso);
        btnEspresso  = (Button) findViewById(R.id.btn_espresso);
        btnChangeText  = (Button) findViewById(R.id.btnChange_text);
        btnYes  = (Button) findViewById(R.id.btnYes);


        btnEspresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog();
            }
        });

        btnChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testEspresso.setText("hi!");
            }
        });
    }
    public void customDialog() {
        dialog = new Dialog(Espresso_Demo.this, R.style.Dialog);
        dialog.setTitle("notification");
        dialog.setContentView(R.layout.custom_dialog_espresso);
        btnNo  = (Button) dialog.findViewById(R.id.btnNo);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}