package com.sungwon.project_01;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    String thumbnailPhotoURL;
    Bitmap bit;
    String id_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = (ImageView)findViewById(R.id.detail_image);
        button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File root = Environment.getExternalStorageDirectory();
                File file = new File(root.getAbsolutePath()+"/DCIM/Camera/"+id_+".png");
                try
                {
                    file.createNewFile();
                    FileOutputStream ostream = new FileOutputStream(file);
                    bit.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                    ostream.close();
                    
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));

            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String id = bundle.getString("id");
        id_ = id;
        String title = bundle.getString("title");
        String secret = bundle.getString("secret");
        String server = bundle.getString("server");
        String farm = bundle.getString("farm");

        thumbnailPhotoURL = "http://farm"+farm+".staticflickr.com/"+server+"/"
                +id+"_"+secret+"_b.jpg";
        showImage si = new showImage();
        si.execute();

    }

    public Bitmap drawImage(){
        try {

            URL url = new URL(thumbnailPhotoURL);
            Log.d("asdf", thumbnailPhotoURL);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.connect();

            int responseStatusCode = httpURLConnection.getResponseCode();

            InputStream inputStream;
            if (responseStatusCode == HttpURLConnection.HTTP_OK) {

                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);

            bufferedInputStream.close();
            httpURLConnection.disconnect();

            return bitmap;
        } catch (Exception e) {
            Log.d("asdf", e.toString());
        }
        return null;
    }
    private class showImage extends AsyncTask<String, Void, Bitmap>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog( DetailActivity.this );
            progressDialog.setMessage("Please wait.....");
            progressDialog.show();
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            Bitmap bitmap = drawImage();
            bit = bitmap;
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }

    }
}
