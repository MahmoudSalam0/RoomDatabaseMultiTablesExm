package com.mah.roomdatabasemultitablesexm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;

public class Converter {

    @TypeConverter
    public Long toLong(Date date){
         return date == null ? null : date.getTime();
    }
    @TypeConverter
    public Date toDate(Long date){
        return new Date(date);
    }
    @TypeConverter
    public static byte[] bitAsByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);

        return stream.toByteArray();
    }
    @TypeConverter
    public static Bitmap byteAsBitMap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}
