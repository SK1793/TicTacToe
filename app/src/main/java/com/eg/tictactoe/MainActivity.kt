package com.eg.tictactoe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buClick(view: View) {

        val btnSelected: Button = view as Button
        var cellId: Int = 0
        val btnCount:Int=0

        when (btnSelected.id) {
            R.id.bu1 -> cellId = 1
            R.id.bu2 -> cellId = 2
            R.id.bu3 -> cellId = 3
            R.id.bu4 -> cellId = 4
            R.id.bu5 -> cellId = 5
            R.id.bu6 -> cellId = 6
            R.id.bu7 -> cellId = 7
            R.id.bu8 -> cellId = 8
            R.id.bu9 -> cellId = 9
            R.id.p1Winbtn->cellId=10
            R.id.p2Winbtn->cellId=11

        }




        playgame(cellId, btnSelected)


    }



    var activeplayer: Int = 1

    var p1 = ArrayList<Int>()
    var p2 = ArrayList<Int>()

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    fun playgame(cellId: Int, btnSelected: Button) {

        if (activeplayer == 1) {
            btnSelected.text = "X"
            btnSelected.setBackgroundColor(R.color.blue)
            p1.add(cellId)
            activeplayer = 2
            autoplay()
        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundColor(R.color.orange)
            p2.add(cellId)
            activeplayer = 1
        }


        btnSelected.isEnabled = false

        winner()
    }

    fun winner(){
        var winner = -1
        // row1
        if (p1.contains(1) && p1.contains(2) && p1.contains(3)) {
            winner = 1
        }
        if (p2.contains(1) && p2.contains(2) && p2.contains(3)) {
            winner = 2
        }

        //row2
        if (p1.contains(4) && p1.contains(5) && p1.contains(6)) {
            winner = 1
        }
        if (p2.contains(4) && p2.contains(5) && p2.contains(6)) {
            winner = 2
        }

        //row3
        if (p1.contains(7) && p1.contains(8) && p1.contains(9)) {
            winner = 1
        }
        if (p2.contains(7) && p2.contains(8) && p2.contains(9)) {
            winner = 2
        }


        //col1
        if (p1.contains(1) && p1.contains(4) && p1.contains(7)) {
            winner = 1
        }
        if (p2.contains(1) && p2.contains(4) && p2.contains(7)) {
            winner = 2
        }

        //col2
        if (p1.contains(2) && p1.contains(5) && p1.contains(8)) {
            winner = 1
        }
        if (p2.contains(2) && p2.contains(5) && p2.contains(8)) {
            winner = 2
        }

        //col3
        if (p1.contains(3) && p1.contains(6) && p1.contains(9)) {
            winner = 1
        }
        else if (p2.contains(3) && p2.contains(6) && p2.contains(9)) {
            winner = 2
        }



        if (winner == 1) {
         p1WinCount+=1
            Toast.makeText(this, "player 1 Wins!", Toast.LENGTH_LONG).show()
            restart()
        } else if (winner==2) {
            p2WinCount+=1
            Toast.makeText(this, "player 2 Wins!", Toast.LENGTH_LONG).show()
            restart()
        }


    }



   fun autoplay(){
        var emptycell=ArrayList<Int>()

        for (cellId:Int in 1..9) {
            if (!(p1.contains(cellId) || p2.contains(cellId))) {
                emptycell.add(cellId)
            }
        }
            val r = Random()
            val rIndex=r.nextInt(emptycell.size)
            val cellId:Int=emptycell[rIndex]


        val btnSelected:Button?

       btnSelected= when(cellId){
           1->bu1
           2->bu2
           3->bu3
           4->bu4
           5->bu5
           6->bu6
           7->bu7
           8->bu8
           9->bu9
           else ->(bu1)
       }

       playgame(cellId,btnSelected)

resetBtn.setOnClickListener {
Toast.makeText(this,"No One Wins!! -'--(*-*)",Toast.LENGTH_LONG).show()
restart()
       }



   }
    var p1WinCount=0
    var p2WinCount=0

    fun restart(){
       activeplayer=1
        p1.clear()
        p2.clear()
        for(cellId in 1..9){

            val btnSelected:Button? = when(cellId){
                1->bu1
                2->bu2
                3->bu3
                4->bu4
                5->bu5
                6->bu6
                7->bu7
                8->bu8
                9->bu9
                else ->(bu1)
            }
            btnSelected!!.text=""
            btnSelected.setBackgroundResource(R.color.white)
               btnSelected.isEnabled = true
        }

        Toast.makeText(this,"p1:$p1WinCount p2:$p2WinCount",Toast.LENGTH_LONG).show()
         p1Winbtn.text="p1:$p1WinCount"
         p2Winbtn.text="p2:$p2WinCount"

    }

}
