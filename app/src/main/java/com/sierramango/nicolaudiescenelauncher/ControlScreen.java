package com.sierramango.nicolaudiescenelauncher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;
import java.net.*;

import android.os.StrictMode;

public class ControlScreen extends AppCompatActivity {

    static TextView message;

    static String char_line = "Stick_3A";

    static String ip_address;

    public ControlScreen() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_screen);


        message = findViewById(R.id.message);

        //String ip_address;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ip_address= null;
            } else {
                ip_address = extras.getString("ip");
                //message.setText(ip_address);

            }
        } else {
            ip_address= (String) savedInstanceState.getSerializable("ip");
        }

        Socket clientSocket = null;
        try {
            clientSocket = new Socket(ip_address, 2431);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            is = clientSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println(0);
        pw.println();
        pw.flush();
        final byte[] buffer = new byte[55];

        final InputStream finalIs = is;
        final Socket finalClientSocket = clientSocket;
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    int read;
                    while ((read = finalIs.read(buffer)) != -1) {
                        String output = new String(buffer, 0, read, "UTF-8");
                        output = output.replace("Stick_3A#", "");
                        //output = output.substring(0, output.length() - 31);
                        output = output.substring(0, output.length() - 28);
                        final String finalOutput;
                        finalOutput = output;
                        runOnUiThread(new Runnable() {
                            public void run() {
                                message.setText(finalOutput);
                                System.out.print(finalOutput);
                            }
                        });
                        System.out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;
                try {
                    finalClientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            } ;thread.start();

    }



    public void trigger(View v) throws IOException {

        String char_line = "";
        String cmd = "";

        char_line = v.getTag().toString();

        if (char_line.equals("prev")) cmd = "Stick_3Ae\07\01\00\00\00\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("next")) cmd = "Stick_3Ae\02\01\00\00\00\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("1")) cmd = "Stick_3Am\00\01\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("2")) cmd = "Stick_3Am\00\02\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("3")) cmd = "Stick_3Am\00\03\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("4")) cmd = "Stick_3Am\00\04\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("5")) cmd = "Stick_3Am\00\05\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("6")) cmd = "Stick_3Am\00\06\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("7")) cmd = "Stick_3Am\00\07\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("8")) cmd = "Stick_3Am\00\10\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("9")) cmd = "Stick_3Am\00\11\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("10")) cmd = "Stick_3Am\00\12\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("11")) cmd = "Stick_3Am\00\13\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("12")) cmd = "Stick_3Am\00\14\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("13")) cmd = "Stick_3Am\00\15\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("14")) cmd = "Stick_3Am\00\16\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("15")) cmd = "Stick_3Am\00\17\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("16")) cmd = "Stick_3Am\00\20\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("17")) cmd = "Stick_3Am\00\21\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("18")) cmd = "Stick_3Am\00\22\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("19")) cmd = "Stick_3Am\00\23\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("20")) cmd = "Stick_3Am\00\24\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("21")) cmd = "Stick_3Am\00\25\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("22")) cmd = "Stick_3Am\00\26\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("23")) cmd = "Stick_3Am\00\27\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("24")) cmd = "Stick_3Am\00\30\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("25")) cmd = "Stick_3Am\00\31\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("26")) cmd = "Stick_3Am\00\32\00\00\01\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("dimm_0")) cmd = "Stick_3Ae\10\10\01\00\00\00\00\00\00\00\00\00\00\00\00";
        if (char_line.equals("dimm_100")) cmd = "Stick_3Ae\01\06\00\00\01\00\00\00\00\00\00\00\00\00\00";

        //StringBuilder responseString = new StringBuilder();

        byte[] buffer = new byte[23];

        Socket socket = new Socket (ip_address, 2431);

        PrintWriter out = new PrintWriter (socket.getOutputStream(), true);

        InputStream is = socket.getInputStream();

        //new InputStreamReader(socket.getInputStream(), "UTF-8");

        out.println(cmd);
        out.flush();

        out.close();
        socket.close();


    }


}
