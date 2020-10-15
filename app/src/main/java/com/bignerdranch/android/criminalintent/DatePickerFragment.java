package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {
    private static final String ARG_DATE = "DialogDate";
    public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";

    private DatePicker mDatePicker;
    private Button mButtonOk;

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Date date = (Date) getActivity().getIntent().getSerializableExtra(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);

        mButtonOk = (Button) v.findViewById(R.id.ok_date);
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                Date date = new GregorianCalendar(year, month, day).getTime();
                Intent intent = new Intent();
                intent.putExtra(ARG_DATE, date);
                getActivity().setResult(Activity.RESULT_OK, intent);
                //sendResult(Activity.RESULT_OK, date);
                getActivity().finish();
            }
        });

//        return new AlertDialog.Builder(getActivity())
//                .setView(v)
//                .setTitle(R.string.date_picker_title)
//                .setPositiveButton(android.R.string.ok,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                int year = mDatePicker.getYear();
//                                int month = mDatePicker.getMonth();
//                                int day = mDatePicker.getDayOfMonth();
//                                Date date = new GregorianCalendar(year, month, day).getTime();
//                                sendResult(Activity.RESULT_OK, date);
//                            }
//                        })
//                .create();
        return v;
    }

    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
