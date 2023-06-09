package com.example.recyclerviewanimator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private var studentDataList: ArrayList<studentData> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    val    recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
     var   studentAdapter = StudentAdapter(studentDataList, this@MainActivity)
        val manager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager= manager
        recyclerView.setAdapter(studentAdapter)
        StudentDataPrepare()
    }

    private fun StudentDataPrepare() {
        var data: studentData? = studentData("sai", 25)
        if (data != null) {
            studentDataList.add(data)
        }
        data = studentData("sai", 25)
        studentDataList.add(data)
        data = studentData("raghu", 20)
        studentDataList.add(data)
        data = studentData("raj", 28)
        studentDataList.add(data)
        data = studentData("amar", 15)
        studentDataList.add(data)
        data = studentData("bapu", 19)
        studentDataList.add(data)
        data = studentData("chandra", 52)
        studentDataList.add(data)
        data = studentData("deraj", 30)
        studentDataList.add(data)
        data = studentData("eshanth", 28)
        studentDataList.add(data)
        Collections.sort(studentDataList, object :  java.util.Comparator<studentData> {
            override fun compare(o1: studentData, o2: studentData): Int {
                return o1.name.compareTo(o2.name)
            }
        })
    }
}