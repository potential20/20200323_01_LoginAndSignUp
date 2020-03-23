package kr.co.tjoeun.a20200323_01_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjoeun.a20200323_01_loginandsignup.databinding.ActivityMainBinding;
import kr.co.tjoeun.a20200323_01_loginandsignup.databinding.ActivitySignUpBinding;
import kr.co.tjoeun.a20200323_01_loginandsignup.utils.ServerUtil;

public class SignUpActivity extends BaseActivity {

    ActivitySignUpBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ID / 비번 / 이름 / 폰번을 따와서 => 서버로 전달
//                회원가입 API에 전달

                String inputId = binding.idEdt.getText().toString();
                String inputPw = binding.pwEdt.getText().toString();
                String inputName = binding.nameEdt.getText().toString();
                String inputPhone = binding.phoneEdt.getText().toString();

                ServerUtil.postRequestSignUp(mContext, inputId, inputPw, inputName, inputPhone, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        Log.d("회원가입",json.toString());

//                        응용문제 : 회원가입에 성공? "이름"님 환영합니다. 토스트로 출력!
//                        실패? 서버에서 주는 message를 그대로 Toast로 출력력

                        try {
                            int code = json.getInt("code");

                            if (code == 200) {
//                                    회원가입 성공 케이스
                                JSONObject data = json.getJSONObject("data");
                                JSONObject user = data.getJSONObject("user");

                                final String name = user.getString("name");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, String.format("%s님 환영합니다.",name), Toast.LENGTH_SHORT).show();
                                    }
                                });


                            }
                            else {
//                                회원가입 실패 케이스
                                final String message = json.getString("message");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

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
