package ru.kackbip.impactMapping.screens.goalCreation.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import rx.AsyncEmitter;
import rx.Observable;

/**
 * Created by ryashentsev on 17.11.2016.
 */

public class EditDate extends EditText implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
    private AsyncEmitter<Date> emitter;
    private Date date = new Date();

    public EditDate(Context context) {
        super(context);
    }

    public EditDate(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditDate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EditDate(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public Observable<Date> observeDate(){
        return Observable.<Date>fromEmitter(emitter -> this.emitter = emitter, AsyncEmitter.BackpressureMode.ERROR)
                .mergeWith(Observable.just(date));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOnClickListener(this);
        setFocusable(false);
        updateDisplay();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        date = calendar.getTime();
        updateDisplay();
        if(emitter!=null) emitter.onNext(date);
    }

    @Override
    public void onClick(View v) {
        Date date;
        try {
            date = dateFormat.parse(getText().toString());
        } catch (ParseException e) {
            date = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        DatePickerDialog dialog = new DatePickerDialog(getContext(), this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void updateDisplay() {
        setText(dateFormat.format(date));
    }
}