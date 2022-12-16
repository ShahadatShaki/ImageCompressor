package com.acoder.imagecompressor.Utility

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class ConstantsKotlin : Constants() {


    fun getFileFromUri(context: Context, imageUri: Uri): File? {
        return try {
            val path = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File(path, "picture_${Random().nextInt()}.jpg")

            try {
                if (!file.exists()) {
                    file.createNewFile()
                }
            } catch (e: IOException) {
            }
            val fos = FileOutputStream(file)
            val `is` = context!!.contentResolver.openInputStream(imageUri)
            val buffer = ByteArray(1024)
            var len = 0
            len = `is`!!.read(buffer)
            while (len != -1) {
                fos.write(buffer, 0, len)
                len = `is`.read(buffer)
            }
            fos.close()

            file
        } catch (e: Exception) {
            showFailedToast(context, e.localizedMessage)
            null
        }
    }

    companion object {
        private val TAG = "shaki-tag"

        fun compressImage(context: Context, actualImageFile: File): File? {
            var file: File? = null
            val fileLen = (actualImageFile.length() / 1024).toInt()
            var convertQuality = 75
            if (fileLen > 10000) {
                convertQuality = 30
            } else if (fileLen > 3000) convertQuality = 50
            try {

                GlobalScope.launch {
                    file = Compressor.compress(context, actualImageFile) {
                        resolution(1280, 720)
                        quality(convertQuality)
                    }
                }

            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Unable to process the image, Please change or recapture the image",
                    Toast.LENGTH_LONG
                ).show()
                e.printStackTrace()
            }
            return file
        }


    }


}