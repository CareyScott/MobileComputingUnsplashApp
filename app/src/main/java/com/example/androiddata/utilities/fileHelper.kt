package com.example.androiddata.utilities

import android.app.Application
import android.content.Context
import java.io.File
import java.lang.Appendable
import java.nio.charset.Charset
import kotlin.system.exitProcess

// this file is what saves and retrieves the data from the cache of the local machine
// saves data for quick launch of the app

class fileHelper {
    companion object{
     fun getTextFromResourses(context: Context, resourceId: Int): String{
         return context.resources.openRawResource(resourceId).use {
             it.bufferedReader().use{
                 it.readText()
             }
         }
     }
        fun getTextFromAssets(context: Context, fileName: String): String{
            return context.assets.open(fileName).use {
                it.bufferedReader().use{
                    it.readText()
                }
            }
        }

        fun saveTextToFile(app: Application, json: String?) {
            val file = File(app.cacheDir, "photos.json")
            file.writeText(json?: "", Charsets.UTF_8)
        }

        fun readTextFile(app: Application):String?
        {
            val file = File(app.cacheDir, "photos.json")
            return if (file.exists()){
                file.readText()
            }else null
        }
    }
}