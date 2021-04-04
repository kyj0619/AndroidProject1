package com.example.androidproject1;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

public class MonthItemView extends AppCompatTextView {

    private MonthItem item;

    public MonthItemView(Context context){
        super(context);
    }

    public MonthItemView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public MonthItem getItem()
    {
        return item;
    }

    public void setItem(MonthItem item){
        this.item = item;

        int day = item.getDay();
        if (day != 0) {
            setText(String.valueOf(day));
            setGravity(Gravity.CENTER);
            setTextColor(Color.BLACK);
            setTextSize(20);
        }else{
            setText("");    //날짜 값을 받아서 값이 있을때 setText로 값을 받고 없을시 빈칸으로 받음
        }
    }
}
