package com.example.kapil.androidarchitecturedesign.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {


    //converting from Date to Long type
    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }

    //converting from Long Datatype to Date
    @TypeConverter
    public static Long timestamp(Date date){
        return date == null ? null :date.getTime();
    }
}
