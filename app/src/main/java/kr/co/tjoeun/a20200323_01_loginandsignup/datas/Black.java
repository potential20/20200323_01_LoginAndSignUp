package kr.co.tjoeun.a20200323_01_loginandsignup.datas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Black implements Serializable {

    private int id;
    private String phoneNum;
    private String title;
    private String content;
    private Calendar createdAt;
    private User writer;

//    calendar => 양식으로 가공해서 string으로 반환.
    public String getFormattedCreateAt() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 d일 a h시 m분");
        return sdf.format(this.createdAt.getTime());

    }

    public static Black getBlackFromJson(JSONObject json) {

        Black black = new Black();

        try {
//            기본적인 파싱
            black.id = json.getInt("id");
            black.phoneNum = json.getString("phone_num");
            black.title = json.getString("title");
            black.content = json.getString("content");

//            작성자정보 파싱.
            JSONObject writer = json.getJSONObject("writer");
            black.writer = User.getUserFromJson(writer);

//            작성일시 => Calendar로 저장.
//            String => Calendar 변환?
//            Calendar => String 변환. SimpleDateFormat

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            black.createdAt = Calendar.getInstance();

            black.createdAt.setTime(sdf.parse(json.getString("created_at"))); // Date => Cal에 담자.



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return black;
    }

    public Black() {
    }

    public Black(int id, String phoneNum, String title, String content, Calendar createdAt, User writer) {
        this.id = id;
        this.phoneNum = phoneNum;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.writer = writer;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
}
