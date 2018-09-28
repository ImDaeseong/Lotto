package com.im.daeseong.lottoplayer.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DbHandler {

    private static final String TAG = DbHandler.class.getSimpleName();

    //테이블 이름
    public static final String TABLE_NAME = "Lotto";

    //컬럼 이름
    public static final String COLUMN_RINDEX = "rIndex";
    public static final String COLUMN_DATA = "Date";
    public static final String COLUMN_PART1 = "Part1";
    public static final String COLUMN_PART2 = "Part2";
    public static final String COLUMN_PART3 = "Part3";
    public static final String COLUMN_PART4 = "Part4";
    public static final String COLUMN_PART5 = "Part5";
    public static final String COLUMN_PART6 = "Part6";
    public static final String COLUMN_BONUS = "Bonus";

    private DbHelper dbHelper = null;


    private static DbHandler instance = null;
    public static DbHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DbHandler(context);
        }
        return instance;
    }

    private DbHandler(Context context) {
        dbHelper = DbHelper.getInstance(context);
    }

    public void addLotto(Lotto lotto){

        if(dbHelper == null){
            return;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put(COLUMN_RINDEX, lotto.getrIndex());
            values.put(COLUMN_DATA,   lotto.getDate());
            values.put(COLUMN_PART1,  lotto.getPart1());
            values.put(COLUMN_PART2,  lotto.getPart2());
            values.put(COLUMN_PART3,  lotto.getPart3());
            values.put(COLUMN_PART4,  lotto.getPart4());
            values.put(COLUMN_PART5,  lotto.getPart5());
            values.put(COLUMN_PART6,  lotto.getPart6());
            values.put(COLUMN_BONUS,  lotto.getBonus());
            db.insert(TABLE_NAME, null, values);

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {
            if(db != null){
                db.close();
            }
        }
    }

    public void deleteLotto(int rIndex){

        if(dbHelper == null){
            return;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "Delete FROM Lotto where rIndex = " + rIndex;

        try{

            db.execSQL(query);

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {
            if(db != null){
                db.close();
            }
        }
    }

    public ArrayList<Lotto> getLotto() {

        if(dbHelper == null){
            return null;
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if(db == null){
            return null;
        }

        ArrayList<Lotto> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY rIndex DESC";
        Cursor cursor = db.rawQuery(query, null);

        try{

            while (cursor.moveToNext()){
                int rIndex = cursor.getInt(0);
                String Date = cursor.getString(1);
                int Part1 = cursor.getInt(2);
                int Part2 = cursor.getInt(3);
                int Part3 = cursor.getInt(4);
                int Part4 = cursor.getInt(5);
                int Part5 = cursor.getInt(6);
                int Part6 = cursor.getInt(7);
                int Bonus = cursor.getInt(8);
                Lotto lotto = new Lotto(rIndex, Date, Part1, Part2, Part3, Part4, Part5, Part6, Bonus);
                list.add(lotto);
            }

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {

            if(cursor != null){
                cursor.close();
                cursor = null;
            }

            if(db != null){
                db.close();
            }
        }
        return list;
    }

    public int getLottoRowCount(){

        int nTotalcount = 0;

        if(dbHelper == null){
            return nTotalcount;
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if(db == null){
            return nTotalcount;
        }

        String query = "SELECT count(*) FROM Lotto";
        Cursor cursor = db.rawQuery(query, null);

        try {

            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                nTotalcount = Integer.parseInt(cursor.getString(0));
            }

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {

            if(cursor != null){
                cursor.close();
                cursor = null;
            }

            if(db != null){
                db.close();
            }
        }
        return nTotalcount;
    }

    public int getPartTop(int nPart) {

        int Part = 0;
        int cnt = 0;
        String query = "";

        if(dbHelper == null){
            return Part;
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if(db == null){
            return Part;
        }

        if(nPart == 1) {
            query = "SELECT Part1, count(*) as cnt FROM Lotto GROUP BY Part1 order by cnt desc limit 1";
        } else if(nPart == 2) {
            query = "SELECT Part2, count(*) as cnt FROM Lotto GROUP BY Part2 order by cnt desc limit 1";
        } else if(nPart == 3) {
            query = "SELECT Part3, count(*) as cnt FROM Lotto GROUP BY Part3 order by cnt desc limit 1";
        } else if(nPart == 4) {
            query = "SELECT Part4, count(*) as cnt FROM Lotto GROUP BY Part4 order by cnt desc limit 1";
        } else if(nPart == 5) {
            query = "SELECT Part5, count(*) as cnt FROM Lotto GROUP BY Part5 order by cnt desc limit 1";
        } else if(nPart == 6) {
            query = "SELECT Part6, count(*) as cnt FROM Lotto GROUP BY Part6 order by cnt desc limit 1";
        }
        Cursor cursor = db.rawQuery(query, null);

        try {

            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                Part = Integer.parseInt(cursor.getString(0));
                cnt = Integer.parseInt(cursor.getString(1));
                cursor.close();
            } else {
                Part = 0;
                cnt = 0;
            }

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {

            if(cursor != null){
                cursor.close();
                cursor = null;
            }

            if(db != null){
                db.close();
            }
        }
        return Part;
    }

    public ArrayList<LottoTop> getLottoTop() {

        if(dbHelper == null){
            return null;
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if(db == null){
            return null;
        }

        ArrayList<LottoTop> sortlist = new ArrayList<>();
        ArrayList<LottoTop> list = new ArrayList<>();

        try{

            setLottoArray(list, "SELECT Part1, count(*) as cnt FROM Lotto GROUP BY Part1 order by cnt desc limit 10");
            setLottoArray(list, "SELECT Part2, count(*) as cnt FROM Lotto GROUP BY Part2 order by cnt desc limit 10");
            setLottoArray(list, "SELECT Part3, count(*) as cnt FROM Lotto GROUP BY Part3 order by cnt desc limit 10");
            setLottoArray(list, "SELECT Part4, count(*) as cnt FROM Lotto GROUP BY Part4 order by cnt desc limit 10");
            setLottoArray(list, "SELECT Part5, count(*) as cnt FROM Lotto GROUP BY Part5 order by cnt desc limit 10");
            setLottoArray(list, "SELECT Part6, count(*) as cnt FROM Lotto GROUP BY Part6 order by cnt desc limit 10");

            DescendingObj descending = new DescendingObj();
            Collections.sort(list, descending);

            for(int i = 0; i < list.size(); i++){
                sortlist.add(list.get(i));
                list.remove(i--);
            }
            sortlist.addAll(sortlist.size(),list);

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {
            if(db != null){
                db.close();
            }
        }

        return sortlist;
    }

    private void setLottoArray(ArrayList<LottoTop> list, String query) {

        if(dbHelper == null){
            return;
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        try{

            while (cursor.moveToNext()){
                int nNumber = cursor.getInt(0);
                int nCount = cursor.getInt(1);
                LottoTop lotto = new LottoTop(nNumber, nCount);
                list.add(lotto);
            }

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {

            if(cursor != null){
                cursor.close();
                cursor = null;
            }

            if(db != null){
                db.close();
            }
        }
    }

    //내림 차순 정렬
    class DescendingObj implements Comparator<LottoTop> {
        @Override
        public int compare(LottoTop o1, LottoTop o2) {
            return ((Integer)o2.getCount()).compareTo((Integer)o1.getCount());
        }
    }

    //오름 차순 정렬
    class AscendingObj implements Comparator<LottoTop> {
        @Override
        public int compare(LottoTop o1, LottoTop o2) {
            return ((Integer)o1.getCount()).compareTo((Integer)o2.getCount());
        }
    }

}
