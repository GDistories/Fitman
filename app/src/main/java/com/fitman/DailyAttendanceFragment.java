package com.fitman;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fitman.database.Attendance.AttendanceDao;
import com.fitman.database.User.UserDao;
import com.fitman.views.CalendarCardView;
import com.fitman.views.CustomDate;
import com.fitman.views.DateUtil;

import java.util.ArrayList;
import java.util.List;


public class DailyAttendanceFragment extends BaseFragment {
    TextView mCurrentYearMonDat_tv;
    CalendarCardView calendarCardView;
    List<String> mAlreadySignDate = new ArrayList<>();
    private CustomDate mCustomDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        AttendanceDao attendanceDao = new AttendanceDao(getContext());
        mCurrentYearMonDat_tv = getView().findViewById(R.id.sign_calendar_card_current_day_tv);
        calendarCardView = getView().findViewById(R.id.sign_calendar_card_view);
        Button sign_btn = getView().findViewById(R.id.btn_sign);
        CustomDate customDate = new CustomDate();

        if(attendanceDao.isAttendanceExist(getUsername(), customDate.toString()))
        {
            Integer count = attendanceDao.getMonthAttendanceCount(getUsername(), customDate.getYearMonth());
            sign_btn.setText(getString(R.string.already_signin) + "\n" + getString(R.string.attendance_string_1) + " " + count.toString() + " " + getString(R.string.attendance_string_2));
            sign_btn.setEnabled(false);
            sign_btn.setAllCaps(false);
        }
        else
        {
            sign_btn.setText(R.string.sign_in_today);
            sign_btn.setEnabled(true);
            sign_btn.setAllCaps(true);
        }

        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLogin()){
                    Toast.makeText(getContext(), getString(R.string.not_login), Toast.LENGTH_SHORT).show();
                    return;
                }
                String username = getUsername();
                String attendanceDate = mCustomDate.getYear() + "-" + mCustomDate.getMonth() + "-" + mCustomDate.getDay();
                String attendanceMonth = mCustomDate.getYear() + "-" + mCustomDate.getMonth();
                int result = attendanceDao.insertAttendance(username, attendanceDate, attendanceMonth);
                if (result == 1) {
                    //????????????
                    mAlreadySignDate.add(attendanceDate);
                    Toast.makeText(getContext(), getString(R.string.sign_in_success), Toast.LENGTH_SHORT).show();
                    Integer count = attendanceDao.getMonthAttendanceCount(getUsername(), customDate.getYearMonth());
                    sign_btn.setText(getString(R.string.already_signin) + "\n" + getString(R.string.attendance_string_1) + " " + count.toString() + " " + getString(R.string.attendance_string_2));
                    sign_btn.setEnabled(false);
                    sign_btn.setAllCaps(false);
                    initData();
                } else if (result == -2) {
                    //???????????????????????????
                    Toast.makeText(getContext(), getString(R.string.attendance_already_exist), Toast.LENGTH_SHORT).show();
                }
                else {
                    //????????????
                    Toast.makeText(getContext(), getString(R.string.sign_in_fail), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //??????????????????????????????
        mCustomDate = new CustomDate();
        calendarCardView.setNewMonth(mCustomDate);

        calendarCardView.setOnCellClickListener(new CalendarCardView.OnCellClickListener() {
            @Override
            public void clickDate(CustomDate date) {
            }
            @Override
            public void changeDate(CustomDate date) {
            }
            @Override
            public void currentTotalDay(int totalday) {
            }
            @Override
            public void touchToLastMonth(boolean isLastMounth) {
                if (isLastMounth){
                    leftSlide();//???????????????
                } else {
                    rightSlide();//???????????????
                }
            }
        });

        getView().findViewById(R.id.sign_calendar_card_left_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftSlide();
            }
        });

        getView().findViewById(R.id.sign_calendar_card_right_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightSlide();
            }
        });

        getView().findViewById(R.id.sign_calendar_today_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomDate = new CustomDate();
                initData();
            }
        });

        initData();
    }

    // ??????????????????????????????
    public void leftSlide() {
        if (mCustomDate.month == 1) {
            mCustomDate.month = 12;
            mCustomDate.year -= 1;
        } else {
            mCustomDate.month -= 1;
        }

        initData();
    }

    // ??????????????????????????????
    public void rightSlide() {
        if (mCustomDate.month == 12) {
            mCustomDate.month = 1;
            mCustomDate.year += 1;
        } else {
            mCustomDate.month += 1;
        }

        initData();
    }

    //???????????????????????????
    public void initData() {
        mAlreadySignDate.clear();
        getData();//?????????????????????
        initAlreadySignData(mAlreadySignDate);
    }

    public void getData(){
        AttendanceDao attendanceDao = new AttendanceDao(getContext());
        List<String> list = attendanceDao.getAttendance(getUsername());

        for (String s : list) {
            Log.e("TAG", s);
        }
        mAlreadySignDate.addAll(list);
    }

    //????????????
    public void setSignCalendarDate(){
        //??????????????????????????????????????????
        calendarCardView.setSignDateList(mAlreadySignDate);
        //??????????????????????????????
        calendarCardView.setNewMonth(mCustomDate);
        mCurrentYearMonDat_tv.setText(mCustomDate.year + getString(R.string.year_slash) + mCustomDate.month);
    }

    public void initAlreadySignData(List<String> list){
        mAlreadySignDate.addAll(list);
        //????????????
        setSignCalendarDate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_attendance, container, false);
    }
}