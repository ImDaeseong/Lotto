package com.daeseong.lottoplayer.Database

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CopyDBfile(context: Context) {

    private val tag = CopyDBfile::class.java.simpleName
    private val db_name = "LottoDB.db"

    init {
        val dbPath = "/data/data/${context.applicationContext.packageName}/databases/"

        try {

            context.assets.open(db_name).use { myInput ->

                val file = File(dbPath, db_name)
                if (!file.exists()) {
                    file.parentFile?.mkdirs()
                    file.createNewFile()

                    FileOutputStream(file).use { myOutput ->
                        val buffer = ByteArray(1024)
                        var length: Int
                        while (myInput.read(buffer).also { length = it } > 0) {
                            myOutput.write(buffer, 0, length)
                        }
                    }
                }
            }

            Log.e(tag, "use 함수로 인해 자동으로 닫힘")

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
