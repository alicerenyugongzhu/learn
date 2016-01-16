package com.example.learn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alice on 2016/1/16.
 */
public class http extends Activity implements Runnable{
    URL url;
    String resultData;
    TextView httpText;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http);

        httpText = (TextView)findViewById(R.id.http_text);
      //  httpText.setVisibility(View.GONE);

        Button direct = (Button)findViewById(R.id.directly);
        View.OnClickListener listener0 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread().start();
            }
        };
        direct.setOnClickListener(listener0);
    }

    @Override
    public void run() {
        //String httpUrl = "http://alice.marlinl.com/http1.jsp";
        String httpUrl = "http://hk.marlinl.com";

            try {
                url = new URL(httpUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if (url != null) {
                try {
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStreamReader in = new InputStreamReader((urlConnection.getInputStream()));
                    BufferedReader buffer = new BufferedReader(in);
                    String inputLine = null;
                    while ((inputLine = buffer.readLine()) != null) {
                        resultData += inputLine + "\n";
                    }
                    in.close();
                    urlConnection.disconnect();
                    if (resultData != null) {
                        httpText.setText(resultData);
                    } else {
                        httpText.setText("No data read out");
                    }
                    httpText.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


    }
}
