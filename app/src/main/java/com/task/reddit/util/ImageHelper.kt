package com.task.reddit.util

import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

object ImageHelper {

    fun saveImage(bitmap: Bitmap, title: String): String {
        var outStream: FileOutputStream? = null

        val sdCard: File = Environment.getExternalStorageDirectory()
        val path = sdCard.absolutePath + "/Download"
        val dir = File(path)

        dir.mkdirs()
        val outFile = File(dir, "$title.jpg")

        try {
            outStream = FileOutputStream(outFile)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)

        try {
            outStream?.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        try {
            outStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return path
    }

}