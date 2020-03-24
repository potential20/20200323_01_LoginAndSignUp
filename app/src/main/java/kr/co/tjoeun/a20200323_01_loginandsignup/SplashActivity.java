package kr.co.tjoeun.a20200323_01_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import kr.co.tjoeun.a20200323_01_loginandsignup.utils.ContextUtil;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

//        토큰이 저장되어 있고 & 자동로그인이 체크되어 있다면
//        => 메인액티비티로 이동.
//        2초 정도 지연 되도록!
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!ContextUtil.getUserToken(mContext).equals("") && ContextUtil.isAutoLoginCheck(mContext)) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);

//            스플래쉬화면은 종료시키자.
                    finish();
                }
                else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }

                finish();

            }
        },3000
        );

//        그렇지 않다면 => 로그인액티비티로 이동.



    }
}
