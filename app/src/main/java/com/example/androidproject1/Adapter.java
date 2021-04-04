package com.example.androidproject1;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.Calendar;

public class Adapter extends BaseAdapter {
    Calendar cal;
    Context mContext;
    MonthItem[] items;      //객체 생성
    int curYear;
    int curMonth;

    Adapter(Context context){
        super();
        mContext = context;
        init();
    }

    Adapter(Context context, AttributeSet attrs){
        super();
        mContext = context;
        init();
    }

    public void init(){
        cal = Calendar.getInstance();       //캘린더 객체로 가져오기
        items = new MonthItem[7*6];         //배열 7행6열로 생성

        calculate();                        //달력 생성
    }

    public void calculate(){
        for(int i=0; i<items.length; i++){
            items[i] = new MonthItem(0);    //items[] 값 0으로 초기화
        }

        cal.set(Calendar.DAY_OF_MONTH, 1); //1일로 설정

        int startDay = cal.get(Calendar.DAY_OF_WEEK); //이달 시작하는 요일 구하기
        int lastDay = cal.getActualMaximum(Calendar.DATE);  //달의 마지막 날짜를 계산하는 메소드

        int cnt = 1;
        for(int i=startDay-1; i<startDay-1+lastDay; i++){
            items[i] = new MonthItem(cnt);      // 시작날부터 마지막 날짜까지 값 설정 (1일 ~ 마지막날)
            cnt++;
        }

        curYear = cal.get(Calendar.YEAR);
        curMonth = cal.get(Calendar.MONTH);
    }

    public void getPreviousMonth(){     //전달로 이동후 caculate()호출
        cal.add(Calendar.MONTH,-1);
        calculate();
    }

    public void getNextMonth(){
        cal.add(Calendar.MONTH, 1);     //다음달로 이동후 caculate()호출
        calculate();
    }

    @Override
    public int getCount()
    {
        return items.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MonthItemView view = new MonthItemView(mContext);
        MonthItem item = items[position];
        view.setItem(item);     //MonthItemView 클래스에서 setItem함수를 이용해 값일 있을때 입력받거나 없을때 빈칸을 입력받음
        return view;        //뷰값 반환
    }

    @Override
    public Object getItem(int position)
    {
        return items[position];
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    public int getCurYear()
    {
        return curYear;
    }

    public int getCurMonth()
    {
        return curMonth;
    }

}

