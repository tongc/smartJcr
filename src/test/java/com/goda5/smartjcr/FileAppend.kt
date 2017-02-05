package com.goda5.smartjcr

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.RandomAccessFile
import java.nio.channels.FileChannel
import java.nio.file.Files.write
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.stream.IntStream


fun main(args: Array<String>) {
    var start = System.currentTimeMillis()
    val file = File("O:/file.txt")
    file.createNewFile()
    IntStream.range(0, 10000).forEach {
        write(Paths.get("O:/file.txt"), "testtesttest\n".toByteArray(), StandardOpenOption.APPEND)
    }
    println(System.currentTimeMillis() - start)

    file.createNewFile()
    start = System.currentTimeMillis()
    IntStream.range(0, 10000).forEach {
        write(Paths.get("O:/file.txt"), "testtesttest\n".toByteArray(), StandardOpenOption.WRITE)
    }
    println(System.currentTimeMillis() - start)

    file.createNewFile()
    start = System.currentTimeMillis()
    val bufferedWriter = BufferedWriter(FileWriter("O:/file.txt", true))
    IntStream.range(0, 10000).forEach {
        bufferedWriter.write("testtesttest\n")
    }
    println(System.currentTimeMillis() - start)

    val rwChannel = RandomAccessFile("O:/file1.txt", "rw").channel
    start = System.currentTimeMillis()
    val wrBuf = rwChannel.map(FileChannel.MapMode.READ_WRITE, 0, "testtesttest\n".length * 10000L)
    IntStream.range(0, 10000).forEach {
        wrBuf.put("testtesttest\n".toByteArray())
    }
    rwChannel.close()
    println(System.currentTimeMillis() - start)
}