package kr.co.tjoeun.a20200323_01_loginandsignup;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public Context mContext = this;

    public abstract void setupEvents() ;
    public abstract void setValues();
}
