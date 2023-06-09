package com.example.recyclerviewanimator

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MainActivity : AppCompatActivity() {
    var fromPosition = -1
    var toPosition = -1
    lateinit var studentAdapter: StudentAdapter
    private val itemTouchHelper by lazy {
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, ACTION_STATE_DRAG) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    toPosition = target.adapterPosition
                    studentDataList.get(toPosition).isOverlapped=true
                    studentAdapter.notifyItemChanged(toPosition)
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                }

                override fun getMovementFlags(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ): Int {
                    return super.getMovementFlags(recyclerView, viewHolder)
                    //to fixed the position or movement
                }

                override fun onSelectedChanged(
                    viewHolder: RecyclerView.ViewHolder?,
                    actionState: Int
                ) {
                    super.onSelectedChanged(viewHolder, actionState)
                    when (actionState) {
                        ItemTouchHelper.ACTION_STATE_DRAG -> {
                            fromPosition = viewHolder?.adapterPosition ?: 0

                        }

                        ItemTouchHelper.ACTION_STATE_IDLE -> {
                            //Execute when the user dropped the item after dragging.
                            if (fromPosition != -1 && toPosition != -1
                                && fromPosition != toPosition
                            ) {
                                moveItem(fromPosition, toPosition);
//                                studentAdapter.notifyItemMoved(fromPosition, toPosition)
                                fromPosition = -1;
                                toPosition = -1;
                            }

                        }
                    }
                }

                override fun clearView(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ) {
                    super.clearView(recyclerView, viewHolder)
//                viewHolder.itemView.alpha = 1.0f
                }
            }

        ItemTouchHelper(simpleItemTouchCallback)
    }

    fun moveItem(oldPos: Int, newPos: Int) {
        var temp = studentDataList.get(oldPos);
        var newITem = studentDataList.get(newPos);
        val newColr = studentDataList.get(newPos).color
        newITem.color = temp.color
        temp.color = newColr
        studentDataList.get(toPosition).isOverlapped=false
        studentDataList.set(oldPos, newITem);
        studentDataList[newPos] = temp;
        studentAdapter.notifyItemChanged(oldPos);
        studentAdapter.notifyItemChanged(newPos);

    }

    private var studentDataList: ArrayList<studentData> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        studentAdapter = StudentAdapter(studentDataList, this@MainActivity)
        val manager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        recyclerView.layoutManager = manager
        recyclerView.setAdapter(studentAdapter)
        StudentDataPrepare()
    }

    private fun StudentDataPrepare() {
        var data: studentData? = studentData(
            "sai",
            25,
            1,
            Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))
        )
        if (data != null) {
            studentDataList.add(data)
        }
        data = studentData(
            "sai",
            25,
            1,
            Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))
        )
        studentDataList.add(data)
        data = studentData(
            "raghu",
            20,
            1,
            Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))
        )
        studentDataList.add(data)
        data = studentData(
            "raj",
            28,
            1,
            Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))
        )
        studentDataList.add(data)
        data = studentData(
            "amar",
            15,
            1,
            Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))
        )
        studentDataList.add(data)
        data = studentData(
            "bapu",
            19,
            2,
            Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))
        )
        studentDataList.add(data)
        data = studentData(
            "chandra",
            52,
            2,
            Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))
        )
        studentDataList.add(data)
        data = studentData(
            "deraj",
            30,
            2,
            Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))
        )
        studentDataList.add(data)
        data = studentData(
            "eshanth",
            28,
            2,
            Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))
        )
        studentDataList.add(data)
        Collections.sort(studentDataList, object : java.util.Comparator<studentData> {
            override fun compare(o1: studentData, o2: studentData): Int {
                return o1.name.compareTo(o2.name)
            }
        })
    }
}