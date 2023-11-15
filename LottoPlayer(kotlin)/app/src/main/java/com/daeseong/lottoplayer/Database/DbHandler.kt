package com.daeseong.lottoplayer.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import java.util.*
import kotlin.collections.ArrayList

class DbHandler {

    private val tag = DbHandler::class.java.simpleName

    //테이블 이름
    val TABLE_NAME = "Lotto"

    //컬럼 이름
    val COLUMN_RINDEX = "rIndex"
    val COLUMN_DATA = "Date"
    val COLUMN_PART1 = "Part1"
    val COLUMN_PART2 = "Part2"
    val COLUMN_PART3 = "Part3"
    val COLUMN_PART4 = "Part4"
    val COLUMN_PART5 = "Part5"
    val COLUMN_PART6 = "Part6"
    val COLUMN_BONUS = "Bonus"

    companion object {

        private var dbHelper: DbHelper? = null

        private var instance: DbHandler? = null
        fun getInstance(context: Context): DbHandler {
            if (instance == null) {

                dbHelper =  DbHelper(context)
                //dbHelper =  DbHelper.getInstance(context)

                instance = DbHandler()
            }
            return instance as DbHandler
        }
    }

    fun addLotto(lotto: Lotto) {

        if (dbHelper == null) {
            return
        }
        val db = dbHelper!!.writableDatabase

        try {
            val values = ContentValues()
            values.put(COLUMN_RINDEX, lotto.getrIndex())
            values.put(COLUMN_DATA, lotto.getDate())
            values.put(COLUMN_PART1, lotto.getPart1())
            values.put(COLUMN_PART2, lotto.getPart2())
            values.put(COLUMN_PART3, lotto.getPart3())
            values.put(COLUMN_PART4, lotto.getPart4())
            values.put(COLUMN_PART5, lotto.getPart5())
            values.put(COLUMN_PART6, lotto.getPart6())
            values.put(COLUMN_BONUS, lotto.getBonus())
            db!!.insert(TABLE_NAME, null, values)
        } catch (e: SQLiteException) {
        } catch (e: Exception) {
        } finally {
            db?.close()
        }
    }

    fun isExistData(rIndex: Int): Boolean {
        var bFindData = false

        if (dbHelper == null) {
            return bFindData
        }

        val db = dbHelper!!.readableDatabase ?: return bFindData
        val query = "SELECT rIndex FROM Lotto where rIndex = $rIndex"
        var cursor: Cursor? = db.rawQuery(query, null)

        try {
            if (cursor!!.moveToFirst()) {
                bFindData = true
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            db?.close()
        }

        return bFindData
    }

    fun getData(rIndex: Int): Array<String?>? {

        val sArray = arrayOfNulls<String>(9)

        if (dbHelper == null) {
            return null
        }

        val db = dbHelper!!.readableDatabase ?: return null
        val query = "SELECT * FROM Lotto where rIndex = $rIndex"
        var cursor = db.rawQuery(query, null)

        try {
            while (cursor!!.moveToNext()) {
                sArray[0] = cursor.getInt(0).toString()
                sArray[1] = cursor.getString(1)
                sArray[2] = cursor.getInt(2).toString()
                sArray[3] = cursor.getInt(3).toString()
                sArray[4] = cursor.getInt(4).toString()
                sArray[5] = cursor.getInt(5).toString()
                sArray[6] = cursor.getInt(6).toString()
                sArray[7] = cursor.getInt(7).toString()
                sArray[8] = cursor.getInt(8).toString()
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            db?.close()
        }

        return sArray
    }

    fun deleteLotto(rIndex: Int) {

        if (dbHelper == null) {
            return
        }

        val db = dbHelper!!.writableDatabase
        val query = "Delete FROM Lotto where rIndex = $rIndex"

        try {
            db!!.execSQL(query)
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            db?.close()
        }
    }

    fun getLotto(): ArrayList<Lotto>? {

        if (dbHelper == null) {
            return null
        }

        val db = dbHelper!!.readableDatabase ?: return null
        val list: ArrayList<Lotto> = ArrayList()
        val query = "SELECT * FROM $TABLE_NAME ORDER BY rIndex DESC"
        var cursor = db.rawQuery(query, null)

        try {
            while (cursor!!.moveToNext()) {
                val rIndex = cursor.getInt(0)
                val sDate = cursor.getString(1)
                val nPart1 = cursor.getInt(2)
                val nPart2 = cursor.getInt(3)
                val nPart3 = cursor.getInt(4)
                val nPart4 = cursor.getInt(5)
                val nPart5 = cursor.getInt(6)
                val nPart6 = cursor.getInt(7)
                val nBonus = cursor.getInt(8)
                val lotto = Lotto(rIndex, sDate, nPart1, nPart2, nPart3, nPart4, nPart5, nPart6, nBonus)
                list.add(lotto)
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            db?.close()
        }

        return list
    }

    fun getLottoRowCount(): Int {

        var nTotalcount = 0

        if (dbHelper == null) {
            return nTotalcount
        }

        val db = dbHelper!!.readableDatabase ?: return nTotalcount
        val query = "SELECT count(*) FROM Lotto"
        var cursor = db.rawQuery(query, null)

        try {
            if (cursor!!.moveToFirst()) {
                cursor.moveToFirst()
                nTotalcount = cursor.getString(0).toInt()
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            db?.close()
        }

        return nTotalcount
    }

    fun getPartTop(nPart: Int): Int {

        var Part = 0
        var cnt = 0
        var query = ""

        if (dbHelper == null) {
            return Part
        }

        val db = dbHelper!!.readableDatabase ?: return Part
        when (nPart) {
            1 -> {
                query =
                    "SELECT Part1, count(*) as cnt FROM Lotto GROUP BY Part1 order by cnt desc limit 1"
            }
            2 -> {
                query =
                    "SELECT Part2, count(*) as cnt FROM Lotto GROUP BY Part2 order by cnt desc limit 1"
            }
            3 -> {
                query =
                    "SELECT Part3, count(*) as cnt FROM Lotto GROUP BY Part3 order by cnt desc limit 1"
            }
            4 -> {
                query =
                    "SELECT Part4, count(*) as cnt FROM Lotto GROUP BY Part4 order by cnt desc limit 1"
            }
            5 -> {
                query =
                    "SELECT Part5, count(*) as cnt FROM Lotto GROUP BY Part5 order by cnt desc limit 1"
            }
            6 -> {
                query =
                    "SELECT Part6, count(*) as cnt FROM Lotto GROUP BY Part6 order by cnt desc limit 1"
            }
        }

        var cursor = db.rawQuery(query, null)
        try {
            if (cursor!!.moveToFirst()) {
                cursor.moveToFirst()
                Part = cursor.getString(0).toInt()
                cnt = cursor.getString(1).toInt()
                cursor.close()
            } else {
                Part = 0
                cnt = 0
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            db?.close()
        }

        return Part
    }

    fun getLottoTop(): ArrayList<LottoTop>? {

        if (dbHelper == null) {
            return null
        }

        val db = dbHelper!!.readableDatabase ?: return null
        val sortlist: ArrayList<LottoTop> = ArrayList()
        val list: ArrayList<LottoTop> = ArrayList()

        try {

            setLottoArray(
                list,
                "SELECT Part1, count(*) as cnt FROM Lotto GROUP BY Part1 order by cnt desc limit 10"
            )
            setLottoArray(
                list,
                "SELECT Part2, count(*) as cnt FROM Lotto GROUP BY Part2 order by cnt desc limit 10"
            )
            setLottoArray(
                list,
                "SELECT Part3, count(*) as cnt FROM Lotto GROUP BY Part3 order by cnt desc limit 10"
            )
            setLottoArray(
                list,
                "SELECT Part4, count(*) as cnt FROM Lotto GROUP BY Part4 order by cnt desc limit 10"
            )
            setLottoArray(
                list,
                "SELECT Part5, count(*) as cnt FROM Lotto GROUP BY Part5 order by cnt desc limit 10"
            )
            setLottoArray(
                list,
                "SELECT Part6, count(*) as cnt FROM Lotto GROUP BY Part6 order by cnt desc limit 10"
            )

            val descending = DescendingObj()
            Collections.sort(list, descending)

            var i = 0
            while (i < list.size) {
                sortlist.add(list[i])
                list.removeAt(i--)
                i++
            }
            sortlist.addAll(sortlist.size, list)

        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            db?.close()
        }

        return sortlist
    }

    private fun setLottoArray(list: ArrayList<LottoTop>, query: String) {

        if (dbHelper == null) {
            return
        }

        val db = dbHelper!!.readableDatabase
        var cursor = db!!.rawQuery(query, null)

        try {
            while (cursor!!.moveToNext()) {
                val nNumber = cursor.getInt(0)
                val nCount = cursor.getInt(1)
                val lotto = LottoTop(nNumber, nCount)
                list.add(lotto)
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            db?.close()
        }
    }

    //내림 차순 정렬
    internal class DescendingObj : Comparator<LottoTop?> {

        override fun compare(o1: LottoTop?, o2: LottoTop?): Int {
            return o2!!.count.compareTo(o1!!.count)
        }
    }

    //오름 차순 정렬
    internal class AscendingObj : Comparator<LottoTop?> {

        override fun compare(o1: LottoTop?, o2: LottoTop?): Int {
            return o1!!.count.compareTo(o2!!.count)
        }
    }
}