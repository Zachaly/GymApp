package com.example.gymapp.application.service

import android.content.Context
import java.io.File

interface FileService{
    fun readTextFile(fileName: String) : String
    fun readBinaryFile(fileName: String) : ByteArray
    fun saveTextFile(fileName: String, content: String)
    fun saveBinaryFile(fileName: String, content: ByteArray)
}

class FileServiceImpl(private val _context: Context) : FileService {
    override fun readTextFile(fileName: String): String {
        return File(_context.filesDir, fileName).readText()
    }

    override fun readBinaryFile(fileName: String): ByteArray {
        return File(_context.filesDir, fileName).readBytes()
    }

    override fun saveTextFile(fileName: String, content: String) {
        try {
            File(_context.filesDir, fileName).writeText(content)
        }
        catch (ex: Exception){
            println(ex.message)
        }
    }

    override fun saveBinaryFile(fileName: String, content: ByteArray) {
        try {
            File(_context.filesDir, fileName).writeBytes(content)
        }
        catch (ex: Exception){
            println(ex.message)
        }
    }
}