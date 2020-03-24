package kr.co.tjoeun.a20200323_01_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjoeun.a20200323_01_loginandsignup.databinding.ActivityBoardListBinding;
import kr.co.tjoeun.a20200323_01_loginandsignup.datas.Black;
import kr.co.tjoeun.a20200323_01_loginandsignup.utils.ServerUtil;

public class BoardListActivity extends BaseActivity {

    ActivityBoardListBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_board_list);
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        ServerUtil.getRequestBlackList(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                try {
                    int code = json.getInt("code");

                    if (code == 200) {

                        JSONObject data = json.getJSONObject("data");

                        JSONArray blackLists = data.getJSONArray("black_lists");

                        for (int i=0 ; i < blackLists.length() ; i++) {
                            JSONObject bl = blackLists.getJSONObject(i);
                            Black blackPost = Black.getBlackFromJson(bl);
                            Log.d("블랙신고제목",blackPost.getTitle());
                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
