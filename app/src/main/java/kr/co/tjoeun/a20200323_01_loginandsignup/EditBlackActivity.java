package kr.co.tjoeun.a20200323_01_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjoeun.a20200323_01_loginandsignup.databinding.ActivityEditBlackBinding;
import kr.co.tjoeun.a20200323_01_loginandsignup.utils.ServerUtil;

public class EditBlackActivity extends BaseActivity {

    ActivityEditBlackBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_black);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        binding.writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                입력길이가 너무 짧은 경우, 더 입력하라고 Toast

                String phoneNumStr = binding.phoneEdt.getText().toString();

                if (phoneNumStr.length() < 10) {

                    Toast.makeText(mContext, "폰번은 최소 열자리여야 합니다.", Toast.LENGTH_SHORT).show();
                    return; // 메쏘드 강제 종료
                }

                String titleStr = binding.titleEdt.getText().toString();

                if (titleStr.length() <= 5) {
                    Toast.makeText(mContext, "제목은 5글자를 넘겨야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String contentStr = binding.contentEdt.getText().toString();
                if (contentStr.length() < 10) {
                    Toast.makeText(mContext, "내용은 최소 10글자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

//                서버에 게시글 등록

                ServerUtil.postRequestWriteBlack(mContext, phoneNumStr, titleStr, contentStr, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        Log.d("게시글등록응답",json.toString());

                        try {
                            int code = json.getInt("code");
                            if (code == 200) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, "게시글 작성이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    @Override
    public void setValues() {

    }
}
