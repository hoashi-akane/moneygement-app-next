package com.example.moneygement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class DispCalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_calendar);



        // CalendarViewにリスナーを設定
        ((CalendarView) findViewById(R.id.calendarview)).setOnDateChangeListener(listener);
    }

    /**
     * カレンダーの日付部分タップ時のリスナー
     */
    CalendarView.OnDateChangeListener listener = new CalendarView.OnDateChangeListener() {

        /**
         * 日付部分タップ時に実行される処理
         * @param view 押下されたカレンダーのインスタンス
         * @param year タップされた日付の「年」
         * @param month タップされた日付の「月」※月は0月から始まるから、+1して調整が必要
         * @param dayOfMonth タップされた日付の「日」
         */
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

            Intent intent = new Intent(DispCalendarActivity.this, InputSavingAmountActivity.class);
            String checkDay = Integer.toString(year) + '年' + month + '月' + dayOfMonth + '日';

            intent.putExtra("checkday",checkDay);
            startActivity(intent);
            // TODO:


      // 引数の year, month, dayOfMonthを利用して、DBから予定表を取得
            // ListViewに予定表リストを突っ込む
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        Button button = (Button)findViewById(R.id.topbutton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DispCalendarActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }

}
