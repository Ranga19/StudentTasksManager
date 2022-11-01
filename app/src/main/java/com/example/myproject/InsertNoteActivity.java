package com.example.myproject;

import static com.example.myproject.Notification.desExtra;
import static com.example.myproject.Notification.titleExtra;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.db.AppDatabase;
import com.example.myproject.db.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import soup.neumorphism.NeumorphFloatingActionButton;

public class InsertNoteActivity extends AppCompatActivity {
    String priority="1";
    EditText et1,et2;
    public static String a="";
    public static String b="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_note);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        NeumorphFloatingActionButton btn=(NeumorphFloatingActionButton) findViewById(R.id.saveBtn);
        createNotificationChannel();
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                saveNewUser(et1.getText().toString(),et2.getText().toString());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    scheduleNotification();
                }
            }
        });
        ImageView redProirity=(ImageView)findViewById(R.id.redBtnClick);
        ImageView yellowProirity=(ImageView)findViewById(R.id.yellowBtnClick);
        ImageView greenProirity=(ImageView)findViewById(R.id.greenBtnClick);

        redProirity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority="1";
                redProirity.setImageResource(R.drawable.done);
                greenProirity.setImageResource(0);
                yellowProirity.setImageResource(0);
            }
        });
        yellowProirity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority="2";
                redProirity.setImageResource(0);
                greenProirity.setImageResource(0);
                yellowProirity.setImageResource(R.drawable.done);
            }
        });
        greenProirity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority="3";
                redProirity.setImageResource(0);
                greenProirity.setImageResource(R.drawable.done);
                yellowProirity.setImageResource(0);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveNewUser(String firstName, String lastName){
        AppDatabase db=AppDatabase.getDbInstance(this.getApplicationContext());
        User user=new User();
        user.titleCol=firstName;
        user.desCol=lastName;
        DatePicker datePicker=findViewById(R.id.datePicker);
        TimePicker timePicker=findViewById(R.id.timePicker);
        String day = String.valueOf(datePicker.getDayOfMonth());
        String month = String.valueOf(datePicker.getMonth());
        String year = String.valueOf(datePicker.getYear());
        String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Sep","Oct","Nov","Dec"};
        String schedule=day+"th "+months[Integer.parseInt(month)]+" "+year+", "+timePicker.getHour()+":"+timePicker.getMinute();
        user.dateCol=schedule;
        user.priorityCol=priority;
        db.userDao().insertUser(user);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void scheduleNotification() {
        Intent intent= new Intent(getApplicationContext(),Notification.class);
        String title=et1.getText().toString();
        String des=et2.getText().toString();
        intent.putExtra(titleExtra,title);
        intent.putExtra(desExtra,des);
        a=intent.getStringExtra(titleExtra);
        b=intent.getStringExtra(desExtra);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(
                getApplicationContext(),
                1,
                intent,
                PendingIntent.FLAG_IMMUTABLE);
        long time=getTime();
        AlarmManager alarmManager= (AlarmManager) getSystemService(this.ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                time,
                pendingIntent
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private long getTime() {
        DatePicker datePicker=(DatePicker) findViewById(R.id.datePicker);
        TimePicker timePicker=(TimePicker) findViewById(R.id.timePicker);
        int minute=timePicker.getMinute();
        int hour=timePicker.getHour();
        int day=datePicker.getDayOfMonth();
        int month=datePicker.getMonth();
        int year=datePicker.getYear();
        Calendar calendar=Calendar.getInstance();
        calendar.set(year,month,day,hour,minute);
        return calendar.getTimeInMillis();
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            String name="ranganath";
            String description="this is a channel description";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel("1",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}