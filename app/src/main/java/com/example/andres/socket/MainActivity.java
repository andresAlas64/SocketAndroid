package com.example.andres.socket;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    private static Socket s;
    private static PrintWriter printWriter;

    String message = "";
    private static String ip = "192.168.1.124";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.et1);
    }

    public void send_text(View v) { // ENVIAR EL TEXTO
        message = et1.getText().toString();
        myTask mt = new myTask();
        mt.execute();

        Toast.makeText(getApplicationContext(), "Datos enviados", Toast.LENGTH_LONG).show();
    }

    class myTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                s = new Socket(ip,5000);

                printWriter = new PrintWriter(s.getOutputStream());

                printWriter.write(message);

                printWriter.flush();

                printWriter.close();

                s.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}