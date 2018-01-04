package com.example.iut.a11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    boolean gameWin = true;
    int max, min, Ran, gameCount, guessNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public int randomCreate(int max, int min){
        Random Ran = new Random();
        return (Ran.nextInt(max)+min);
    }


    public void buttonClick1(View view){
        if (gameWin == true) {
            max = 100;
            min = 1;
            Ran = randomCreate(max, min);
            gameCount = 0;
            gameWin = false;
        }


        TextView txtOutput = (TextView) findViewById(R.id.textView1);
        EditText txtInput = (EditText) findViewById(R.id.editText1);
        Button txtButton = (Button) findViewById(R.id.button1);


        AlertDialog.Builder endGame = new AlertDialog.Builder(this);
        endGame.setTitle("你猜對了\n");
        endGame.setMessage("隨機的數字是 \""+ Ran  + "\"\n而你猜了 " + gameCount+"次");

        endGame.setPositiveButton("重新開始", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                finish();
                startActivity(getIntent());
            }
        });
        endGame.setNegativeButton("關閉", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {

                finish();
            }
        });

        if ("".equals(txtInput.getText().toString().trim()))
            txtOutput.setText("錯誤輸入  \n請輸入在這個範圍值得數字 " + min + " ~ " + max);
        else {
            String keyinStr = txtInput.getText().toString();
            int keyinInt = Integer.parseInt(keyinStr);
            if((keyinInt >= min) && (keyinInt <= max)){
                gameCount += 1;

                if (keyinInt == Ran){
                    txtOutput.setText("");
                    gameWin = true;
                    endGame.show();
                }
                else if (keyinInt < Ran) {
                    min = keyinInt;
                    txtOutput.setText("猜得好喔\n數字是 " + min + " ~ " + max + " 再猜一次八");
                }
                else if (keyinInt > Ran) {
                    max = keyinInt;
                    txtOutput.setText("猜得好喔\n數字是 " + min + " ~ " + max + " 再猜一次八");
                }
                else {
                    txtOutput.setText("異常錯誤");
                }
            }
            else
                txtOutput.setText("輸入值錯誤 \n請輸入這個範圍的數字 " + min + " ~ " + max);
        }
        txtInput.setText(null);
    }
}
