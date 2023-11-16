package com.daeseong.lottoplayer.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import java.util.*
import kotlin.collections.ArrayList
class DbHandler private constructor(context: Context) {

    private val tag = DbHandler::class.java.simpleName

    // Table name
    private val TABLE_NAME = "Lotto"

    // Column names
    private val COLUMN_RINDEX = "rIndex"
    private val COLUMN_DATA = "Date"
    private val COLUMN_PART1 = "Part1"
    private val COLUMN_PART2 = "Part2"
    private val COLUMN_PART3 = "Part3"
    private val COLUMN_PART4 = "Part4"
    private val COLUMN_PART5 = "Part5"
    private val COLUMN_PART6 = "Part6"
    private val COLUMN_BONUS = "Bonus"

    private val dbHelper: DbHelper = DbHelper.getInstance(context)!!

    companion object {
        private var instance: DbHandler? = null

        @Synchronized
        fun getInstance(context: Context): DbHandler {
            if (instance == null) {
                instance = DbHandler(context.applicationContext)
            }
            return instance!!
        }
    }

    fun addLotto(lotto: Lotto) {
        val db = dbHelper.writableDatabase

        try {
            val values = ContentValues()
            values.put(COLUMN_RINDEX, lotto.rIndex)
            values.put(COLUMN_DATA, lotto.date)
            values.put(COLUMN_PART1, lotto.part1)
            values.put(COLUMN_PART2, lotto.part2)
            values.put(COLUMN_PART3, lotto.part3)
            values.put(COLUMN_PART4, lotto.part4)
            values.put(COLUMN_PART5, lotto.part5)
            values.put(COLUMN_PART6, lotto.part6)
            values.put(COLUMN_BONUS, lotto.bonus)
            db.insert(TABLE_NAME, null, values)
        } catch (e: SQLiteException) {
        } finally {
            db.close()
        }
    }

    fun isExistData(rIndex: Int): Boolean {
        var bFindData = false

        val db = dbHelper.readableDatabase ?: return bFindData
        val query = "SELECT rIndex FROM Lotto where rIndex = $rIndex"
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                bFindData = true
            }
        } catch (e: SQLiteException) {
        } finally {
            cursor?.close()
            db.close()
        }

        return bFindData
    }

    fun getData(rIndex: Int): Array<String?>? {
        val sArray = arrayOfNulls<String>(9)

        val db = dbHelper.readableDatabase ?: return null
        val query = "SELECT * FROM Lotto where rIndex = $rIndex"
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)
            while (cursor.moveToNext()) {
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
        } finally {
            cursor?.close()
            db.close()
        }

        return sArray
    }

    fun deleteLotto(rIndex: Int) {
        val db = dbHelper.writableDatabase
        val query = "DELETE FROM Lotto WHERE rIndex = $rIndex"

        try {
            db.execSQL(query)
        } catch (e: SQLiteException) {
        } finally {
            db.close()
        }
    }

    fun getLotto(): ArrayList<Lotto>? {
        val db = dbHelper.readableDatabase ?: return null
        val list: ArrayList<Lotto> = ArrayList()
        val query = "SELECT * FROM $TABLE_NAME ORDER BY rIndex DESC"
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)
            while (cursor.moveToNext()) {
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
        } finally {
            cursor?.close()
            db.close()
        }

        return list
    }

    fun getLottoRowCount(): Int {
        var nTotalcount = 0

        val db = dbHelper.readableDatabase ?: return nTotalcount
        val query = "SELECT COUNT(*) FROM $TABLE_NAME"
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                nTotalcount = cursor.getInt(0)
            }
        } catch (e: SQLiteException) {
        } finally {
            cursor?.close()
            db.close()
        }

        return nTotalcount
    }

    fun getPartTop(nPart: Int): Int {
        var part = 0
        var cnt = 0
        var query = ""

        val db = dbHelper.readableDatabase ?: return part

        when (nPart) {
            1 -> query = "SELECT Part1, COUNT(*) AS cnt FROM $TABLE_NAME GROUP BY Part1 ORDER BY cnt DESC LIMIT 1"
            2 -> query = "SELECT Part2, COUNT(*) AS cnt FROM $TABLE_NAME GROUP BY Part2 ORDER BY cnt DESC LIMIT 1"
            3 -> query = "SELECT Part3, COUNT(*) AS cnt FROM $TABLE_NAME GROUP BY Part3 ORDER BY cnt DESC LIMIT 1"
            4 -> query = "SELECT Part4, COUNT(*) AS cnt FROM $TABLE_NAME GROUP BY Part4 ORDER BY cnt DESC LIMIT 1"
            5 -> query = "SELECT Part5, COUNT(*) AS cnt FROM $TABLE_NAME GROUP BY Part5 ORDER BY cnt DESC LIMIT 1"
            6 -> query = "SELECT Part6, COUNT(*) AS cnt FROM $TABLE_NAME GROUP BY Part6 ORDER BY cnt DESC LIMIT 1"
        }

        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                part = cursor.getInt(0)
                cnt = cursor.getInt(1)
            } else {
                part = 0
                cnt = 0
            }
        } catch (e: SQLiteException) {
            // 예외 처리
        } finally {
            cursor?.close()
            db.close()
        }

        return part
    }

    fun getLottoTop(): ArrayList<LottoTop>? {
        val db = dbHelper.readableDatabase ?: return null
        val sortList: ArrayList<LottoTop> = ArrayList()
        val list: ArrayList<LottoTop> = ArrayList()

        try {
            setLottoArray(
                list,
                "SELECT Part1, COUNT(*) AS cnt FROM $TABLE_NAME GROUP BY Part1 ORDER BY cnt DESC LIMIT 10"
            )
            setLottoArray(
                list,
                "SELECT Part2, COUNT(*) AS cnt FROM $TABLE_NAME GROUP BY Part2 ORDER BY cnt DESC LIMIT 10"
            )
            setLottoArray(
                list,
                "SELECT Part3, COUNT(*) AS cnt FROM $TABLE_NAME GROUP BY Part3 ORDER BY cnt DESC LIMIT 10"
            )
            setLottoArray(
                list,
                "SELECT Part4, COUNT(*) AS cnt FROM $TABLE_NAME GROUP BY Part4 ORDER BY cnt DESC LIMIT 10"
            )
            setLottoArray(
                list,
                "SELECT Part5, COUNT(*) AS cnt FROM $TABLE_NAME GROUP BY Part5 ORDER BY cnt DESC LIMIT 10"
            )
            setLottoArray(
                list,
                "SELECT Part6, COUNT(*) AS cnt FROM $TABLE_NAME GROUP BY Part6 ORDER BY cnt DESC LIMIT 10"
            )

            val descending = DescendingObj()
            Collections.sort(list, descending)

            var i = 0
            while (i < list.size) {
                sortList.add(list[i])
                list.removeAt(i--)
                i++
            }
            sortList.addAll(sortList.size, list)

        } catch (e: SQLiteException) {
        } finally {
            db.close()
        }

        return sortList
    }

    private fun setLottoArray(list: ArrayList<LottoTop>, query: String) {
        val db = dbHelper.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db!!.rawQuery(query, null)
            while (cursor.moveToNext()) {
                val nNumber = cursor.getInt(0)
                val nCount = cursor.getInt(1)
                val lotto = LottoTop(nNumber, nCount)
                list.add(lotto)
            }
        } catch (e: SQLiteException) {
        } finally {
            cursor?.close()
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