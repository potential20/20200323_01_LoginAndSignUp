package kr.co.tjoeun.a20200323_01_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjoeun.a20200323_01_loginandsignup.adapters.BlackAdapter;
import kr.co.tjoeun.a20200323_01_loginandsignup.databinding.ActivityBoardListBinding;
import kr.co.tjoeun.a20200323_01_loginandsignup.datas.Black;
import kr.co.tjoeun.a20200323_01_loginandsignup.utils.ServerUtil;

public class BoardListActivity extends BaseActivity {

    List<Black> blacks = new ArrayList<>();
    BlackAdapter blackAdapter = null;

    ActivityBoardListBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_board_list);
        setupEvents();
        setValues();

    }

    @Override
    protected void onResume() {
        super.onResume();

        ServerUtil.getRequestBlackList(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                try {
                    int code = json.getInt("code");

                    if (code == 200) {

//                        기존에 불러둔 게시글을 모두 삭제. => 새로 불러옴
//                        blacks에 들어있는 객체를 모두 삭제.

                        blacks.clear();

                        JSONObject data = json.getJSONObject("data");

                        JSONArray blackLists = data.getJSONArray("black_lists");

                        for (int i=0 ; i < blackLists.length() ; i++) {
                            JSONObject bl = blackLists.getJSONObject(i);
                            Black blackPost = Black.getBlackFromJson(bl);
                            Log.d("블랙신고제목",blackPost.getTitle());

//                            파싱 끝난 블랙신고글들을 배열에 담아둠.
                            blacks.add(blackPost);
                        }

//                        모두 담긴 게시글들 => 어댑터가 새로고침.
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                blackAdapter.notifyDataSetChanged();
                            }
                        });


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void setupEvents() {

        binding.postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditBlackActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {

        blackAdapter = new BlackAdapter(mContext, R.layout.black_list_item,blacks);
        binding.postListView.setAdapter(blackAdapter);



    }
}
