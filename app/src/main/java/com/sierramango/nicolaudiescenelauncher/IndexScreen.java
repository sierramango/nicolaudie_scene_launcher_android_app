package com.sierramango.nicolaudiescenelauncher;

import android.icu.util.TimeUnit;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;
import java.net.*;

import android.os.StrictMode;

public class IndexScreen extends AppCompatActivity {

    static TextView message;

    Button connect;

    TextView out;

    TextView socket;

    static EditText ip;

    //static int a = 1;

    static String char_line = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_screen);

        connect = findViewById(R.id.connect);

        ip = findViewById(R.id.ip);

        message = findViewById(R.id.message);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }


    public void main(View view) {
        message.setText("Connecting to device");

        String pingResult = "";

        String pingCmd = "ping -c 1 -w 1 " + ip.getText().toString();

        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);

            BufferedReader in = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            String inputLine;
            message.setText("Connecting");
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                pingResult += inputLine;
                if (pingResult.contains("1 received, 0% packet loss"))
                {message.setText("Device found");}

                else {message.setText("Device not found");}
            }
            in.close();
            if (pingResult.contains("1 received, 0% packet loss"))
            {
                android.content.Intent intent = new android.content.Intent(this, ControlScreen.class);
                intent.putExtra("ip", ip.getText().toString());
                Thread.sleep(1000);
                startActivity(intent);
            }


        } catch (IOException e) {
            message.setText("Device not found");
            System.out.println(e);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }





}
