package kr.co.tjoeun.a20200323_01_loginandsignup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import kr.co.tjoeun.a20200323_01_loginandsignup.R;
import kr.co.tjoeun.a20200323_01_loginandsignup.datas.Black;

public class BlackAdapter extends ArrayAdapter<Black> {

    Context mContext;
    List<Black> mList;
    LayoutInflater inf;

    public BlackAdapter(@NonNull Context context, int resource, @NonNull List<Black> objects) {
        super(context, resource, objects);

        mContext = context;
        mList = objects;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.black_list_item,null);
        }

        Black data = mList.get(position);
        TextView writerNameTxt = row.findViewById(R.id.writerNameTxt);
        TextView titleTxt = row.findViewById(R.id.titleTxt);
        TextView createdAtTxt = row.findViewById(R.id.createdAtTxt);
        TextView contentTxt = row.findViewById(R.id.contentTxt);

        writerNameTxt.setText(data.getWriter().getName());
        titleTxt.setText(data.getTitle());
        contentTxt.setText(data.getContent());

//        작성일시 출력. : Calendar => String 변환
        createdAtTxt.setText(data.getFormattedCreateAt());

        return row;
    }
}
