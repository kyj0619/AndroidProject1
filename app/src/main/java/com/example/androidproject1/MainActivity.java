package com.example.androidproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    GridView Today;
    TextView year_month;
    Adapter adapter;    //각각 필요한 객체를 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] items = {"일", "월", "화", "수", "목", "금", "토"};
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, items);      //요일 값들을 받음

        GridView gridview = (GridView) findViewById(R.id.day_of_the_week);
        gridview.setAdapter(adapt);

        Today = findViewById(R.id.day); //그리드뷰 id받아옴
        adapter = new Adapter(this); //어댑터 객체 생성
        Today.setAdapter(adapter);      //GridView에 어댑터 연결

        year_month = findViewById(R.id.year_month); //텍스트뷰 id 받아옴
        getMonthText();     //현재 년월 출력

        Button monthPrevious = findViewById(R.id.previous);
        Button monthNext = findViewById(R.id.next);     //버튼 id들 받아옴

        Today.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MonthItem item = (MonthItem) adapter.getItem(position); //그리드뷰 값클릭시 그위치의 값을 받아옴
                int curYear = adapter.getCurYear();
                int curMonth = adapter.getCurMonth()+1;
                int curDate = item.getDay();        //년월일을 받음
                String today = Integer.toString(curYear)+"."+Integer.toString(curMonth)+"."+Integer.toString(curDate);      //정수 문자열로 변환
                Toast.makeText(MainActivity.this, today, Toast.LENGTH_SHORT).show();        //toast로 문자열 팝업시키기

            }
        });


        // 전달 버튼 이벤트 리스너
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getPreviousMonth(); //Adapter setPreviousMonth()함수값 받기
                adapter.notifyDataSetChanged();
                getMonthText();
            }
        });

        // 다음달 버튼 이벤트 리스너
        monthNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getNextMonth();    //Adapter getNextMonth()함수값 받기
                adapter.notifyDataSetChanged(); //intent로 실행하려 했으나 오류가 계속나서 notifyDataSetChanged()라는 함수가 어댑터 데이터를 리셋하고 다시 뷰를 생성한다 해서 사용
                getMonthText();
            }
        });
    }

    public void getMonthText(){
        int curYear = adapter.getCurYear();
        int curMonth = adapter.getCurMonth()+1;
        year_month.setText(curYear+"년 "+(curMonth)+"월"); //textview인 year_month에 년월 값 입력
    }


}