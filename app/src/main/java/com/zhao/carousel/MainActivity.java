package com.zhao.carousel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout carouse;
    private String url="http://www.zyloushi.com/extended/index.php";
    private ArrayList<ProjectInfo> thumbs = new ArrayList<>();
    private LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(getBaseContext());
        carouse= (LinearLayout) findViewById(R.id.carouse);
        getData(url);
    }

    private void getData(String url) {
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray array = new JSONObject(result).getJSONArray("lunbo2");
                    if (thumbs != null) {
                        thumbs.clear();
                    }
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        ProjectInfo info = new ProjectInfo(object.getString("aid"), object.getString("thumb"));
                        thumbs.add(info);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if (thumbs == null || thumbs.size() == 0) {
                    return;
                }else {
                    carouse.addView(new CarousePager(getBaseContext(), thumbs, inflater, true).initView());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

}
