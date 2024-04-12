package com.pereslavtsev_o_yu.task1.provider

import android.content.ContentResolver
import android.net.Uri

class Resolver(private val contentResolver: ContentResolver) {
    fun fetchSecretKey(uriToParse: String, key: String): String? {
        val uri = Uri.parse(uriToParse)
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                return it.getString(it.getColumnIndexOrThrow(key))
            }
        }
        return ""
    }
}