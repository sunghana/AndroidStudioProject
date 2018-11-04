package com.sungwon.project_01;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class searchActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    Button button2;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;


    public static final int LOAD_SUCCESS = 101;
    private static final String TAG = "imagesearchexample";

    private String SEARCH_URL = "https://secure.flickr.com/services/rest/?method=flickr.photos.search";
    private String API_KEY = "&api_key=ad54e3e30601486e52b69be88929bb28";   //플릭커api에서 받은 키
    private String PER_PAGE = "&per_page=50";
    private String SORT = "&sort=interestingness-desc";
    private String FORMAT = "&format=json";
    private String CONTECT_TYPE = "&content_type=1";
    private String SEARCH_TEXT;
    private String REQUEST_URL;

    private ProgressDialog progressDialog;
    private final MyHandler mHandler = new MyHandler(this);
    private List<HashMap<String,String>> photoinfoList = null;
    private ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();

    private int search_more_count=0;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);
        imageView5 = (ImageView)findViewById(R.id.imageView5);
        imageView6 = (ImageView)findViewById(R.id.imageView6);
        imageView7 = (ImageView)findViewById(R.id.imageView7);
        imageView8 = (ImageView)findViewById(R.id.imageView8);
        imageView9 = (ImageView)findViewById(R.id.imageView9);

        photoinfoList = new ArrayList<HashMap<String,String>>();

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString( "id", photoinfoList.get(search_more_count).get("id"));
                bundle.putString( "title", photoinfoList.get(search_more_count).get("title"));
                bundle.putString( "secret", photoinfoList.get(search_more_count).get("secret"));
                bundle.putString( "server", photoinfoList.get(search_more_count).get("server"));
                bundle.putString( "farm", photoinfoList.get(search_more_count).get("farm"));


                Intent intObj = new Intent(searchActivity.this,DetailActivity.class);
                intObj.putExtras(bundle);
                startActivity(intObj);

            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString( "id", photoinfoList.get(search_more_count+1).get("id"));
                bundle.putString( "title", photoinfoList.get(search_more_count+1).get("title"));
                bundle.putString( "secret", photoinfoList.get(search_more_count+1).get("secret"));
                bundle.putString( "server", photoinfoList.get(search_more_count+1).get("server"));
                bundle.putString( "farm", photoinfoList.get(search_more_count+1).get("farm"));


                Intent intObj = new Intent(searchActivity.this,DetailActivity.class);
                intObj.putExtras(bundle);
                startActivity(intObj);

            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString( "id", photoinfoList.get(search_more_count+2).get("id"));
                bundle.putString( "title", photoinfoList.get(search_more_count+2).get("title"));
                bundle.putString( "secret", photoinfoList.get(search_more_count+2).get("secret"));
                bundle.putString( "server", photoinfoList.get(search_more_count+2).get("server"));
                bundle.putString( "farm", photoinfoList.get(search_more_count+2).get("farm"));


                Intent intObj = new Intent(searchActivity.this,DetailActivity.class);
                intObj.putExtras(bundle);
                startActivity(intObj);

            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString( "id", photoinfoList.get(search_more_count+3).get("id"));
                bundle.putString( "title", photoinfoList.get(search_more_count+3).get("title"));
                bundle.putString( "secret", photoinfoList.get(search_more_count+3).get("secret"));
                bundle.putString( "server", photoinfoList.get(search_more_count+3).get("server"));
                bundle.putString( "farm", photoinfoList.get(search_more_count+3).get("farm"));


                Intent intObj = new Intent(searchActivity.this,DetailActivity.class);
                intObj.putExtras(bundle);
                startActivity(intObj);

            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString( "id", photoinfoList.get(search_more_count+4).get("id"));
                bundle.putString( "title", photoinfoList.get(search_more_count+4).get("title"));
                bundle.putString( "secret", photoinfoList.get(search_more_count+4).get("secret"));
                bundle.putString( "server", photoinfoList.get(search_more_count+4).get("server"));
                bundle.putString( "farm", photoinfoList.get(search_more_count+4).get("farm"));


                Intent intObj = new Intent(searchActivity.this,DetailActivity.class);
                intObj.putExtras(bundle);
                startActivity(intObj);

            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString( "id", photoinfoList.get(search_more_count+5).get("id"));
                bundle.putString( "title", photoinfoList.get(search_more_count+5).get("title"));
                bundle.putString( "secret", photoinfoList.get(search_more_count+5).get("secret"));
                bundle.putString( "server", photoinfoList.get(search_more_count+5).get("server"));
                bundle.putString( "farm", photoinfoList.get(search_more_count+5).get("farm"));


                Intent intObj = new Intent(searchActivity.this,DetailActivity.class);
                intObj.putExtras(bundle);
                startActivity(intObj);

            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString( "id", photoinfoList.get(search_more_count+6).get("id"));
                bundle.putString( "title", photoinfoList.get(search_more_count+6).get("title"));
                bundle.putString( "secret", photoinfoList.get(search_more_count+6).get("secret"));
                bundle.putString( "server", photoinfoList.get(search_more_count+6).get("server"));
                bundle.putString( "farm", photoinfoList.get(search_more_count+6).get("farm"));


                Intent intObj = new Intent(searchActivity.this,DetailActivity.class);
                intObj.putExtras(bundle);
                startActivity(intObj);

            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString( "id", photoinfoList.get(search_more_count+7).get("id"));
                bundle.putString( "title", photoinfoList.get(search_more_count+7).get("title"));
                bundle.putString( "secret", photoinfoList.get(search_more_count+7).get("secret"));
                bundle.putString( "server", photoinfoList.get(search_more_count+7).get("server"));
                bundle.putString( "farm", photoinfoList.get(search_more_count+7).get("farm"));


                Intent intObj = new Intent(searchActivity.this,DetailActivity.class);
                intObj.putExtras(bundle);
                startActivity(intObj);

            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString( "id", photoinfoList.get(search_more_count+8).get("id"));
                bundle.putString( "title", photoinfoList.get(search_more_count+8).get("title"));
                bundle.putString( "secret", photoinfoList.get(search_more_count+8).get("secret"));
                bundle.putString( "server", photoinfoList.get(search_more_count+8).get("server"));
                bundle.putString( "farm", photoinfoList.get(search_more_count+8).get("farm"));


                Intent intObj = new Intent(searchActivity.this,DetailActivity.class);
                intObj.putExtras(bundle);
                startActivity(intObj);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {  //버튼을 누르면 editText에 써있는 문자열을 요청문자열에 추가한후 getJson을 호출한다
            @Override
            public void onClick(View view) {
                search_more_count=0;
                String searchStr = editText.getText().toString();
                SEARCH_TEXT = "&text='"+searchStr+"'";
                REQUEST_URL = SEARCH_URL + API_KEY + PER_PAGE + SORT + FORMAT + CONTECT_TYPE + SEARCH_TEXT;
                progressDialog = new ProgressDialog( searchActivity.this );
                progressDialog.setMessage("Please wait.....");
                progressDialog.show();

                getJSON();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_more_count+=9;
                String searchStr = editText.getText().toString();
                SEARCH_TEXT = "&text='"+searchStr+"'";
                REQUEST_URL = SEARCH_URL + API_KEY + PER_PAGE + SORT + FORMAT + CONTECT_TYPE + SEARCH_TEXT;
                progressDialog = new ProgressDialog( searchActivity.this );
                progressDialog.setMessage("Please wait.....");
                progressDialog.show();

                getJSON();
            }
        });
    }
    private class MyHandler extends Handler {    //검색결과 화면을 바꿔주는 핸들러
        private final WeakReference<searchActivity> weakReference;
        public MyHandler(searchActivity search_Activity) {
            weakReference = new WeakReference<searchActivity>(search_Activity);
        }

        @Override
        public void handleMessage(Message msg) {

            searchActivity search_Activity = weakReference.get();
            if (search_Activity != null) {
                switch (msg.what) {
                    case LOAD_SUCCESS:
                        search_Activity.progressDialog.dismiss();
                }
            }
        }
    }
    public void getJSON(){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                String result;
                try {
                    Log.d("imagesearchexample", REQUEST_URL);   //thread를 실행하면서 요청문자열로 REST를 요청한다
                    URL url = new URL(REQUEST_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setReadTimeout(3000);
                    httpURLConnection.setConnectTimeout(3000);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.connect();
                    int responseStatusCode = httpURLConnection.getResponseCode();

                    InputStream inputStream;    //JSON응답을 저장한다
                    if (responseStatusCode == HttpURLConnection.HTTP_OK) {

                        inputStream = httpURLConnection.getInputStream();
                    } else {
                        inputStream = httpURLConnection.getErrorStream();
                    }
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }

                    bufferedReader.close();
                    httpURLConnection.disconnect();

                    result = sb.toString().trim();

                }catch (Exception e) {
                    result = e.toString();
                }
                if (jsonParser(result)){

                }
                for(int i=1;i<10;i++){
                    Log.d("abcd", i+search_more_count+"qqqqqqqqqqqqqqqq");
                    getImage(i+search_more_count);
                }

            }
        });
        thread.start();
    }
    public void getImage(final int n){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String farm = photoinfoList.get(n-1).get("farm");
                    String server = photoinfoList.get(n-1).get("server");
                    String id = photoinfoList.get(n-1).get("id");
                    String secret = photoinfoList.get(n-1).get("secret");
                    String thumbnailPhotoURL = "http://farm" + farm + ".staticflickr.com/" + server + "/"
                            + id + "_" + secret + "_t.jpg";
                    Log.d("abcd"+search_more_count, thumbnailPhotoURL);

                    URL url = new URL(thumbnailPhotoURL);
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

                    bitmapList.add(bitmap);

                    int resizeWidth = 300;
                    Bitmap re_bitmap = Bitmap.createScaledBitmap(bitmap, resizeWidth, 300, false);
                    if (re_bitmap != bitmap) {
                        bitmap.recycle();
                    }

                    if(n-search_more_count==1) {
                        imageView1.setImageBitmap(re_bitmap);
                        imageView1.refreshDrawableState();
                    }
                    else if(n-search_more_count==2) {
                        imageView2.setImageBitmap(re_bitmap);
                    }
                    else if(n-search_more_count==3)
                        imageView3.setImageBitmap(re_bitmap);
                    else if(n-search_more_count==4)
                        imageView4.setImageBitmap(re_bitmap);
                    else if(n-search_more_count==5)
                        imageView5.setImageBitmap(re_bitmap);
                    else if(n-search_more_count==6)
                        imageView6.setImageBitmap(re_bitmap);
                    else if(n-search_more_count==7)
                        imageView7.setImageBitmap(re_bitmap);
                    else if(n-search_more_count==8)
                        imageView8.setImageBitmap(re_bitmap);
                    else if(n-search_more_count==9)
                        imageView9.setImageBitmap(re_bitmap);
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }
                Message message = mHandler.obtainMessage(LOAD_SUCCESS);
                mHandler.sendMessage(message);
            }
        });
        thread.start();
    }
    public boolean jsonParser(String jsonString){   //json을 파싱한다
        if (jsonString == null ) return false;

        jsonString = jsonString.replace("jsonFlickrApi(", "");  //앞뒤 쓸데없는것들을 없앤다
        jsonString = jsonString.replace(")", "");

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject photos = jsonObject.getJSONObject("photos");
            JSONArray photo = photos.getJSONArray("photo");

            photoinfoList.clear();
            Log.d("abcd", photo.length()+"wwwwwwwwwwwwwwwww");

            for (int i = 0; i < photo.length(); i++) {
                JSONObject photoInfo = photo.getJSONObject(i);

                String id = photoInfo.getString("id");
                String secret = photoInfo.getString("secret");
                String server = photoInfo.getString("server");
                String farm = photoInfo.getString("farm");
                String title = photoInfo.getString("title");

                HashMap<String, String> photoinfoMap = new HashMap<String, String>();
                photoinfoMap.put("id", id);
                photoinfoMap.put("secret", secret);
                photoinfoMap.put("server", server);
                photoinfoMap.put("farm", farm);
                photoinfoMap.put("title", title);

                photoinfoList.add(photoinfoMap);

            }

            return true;
        } catch (JSONException e) {

            Log.d(TAG, e.toString() );
        }

        return false;
    }
}
