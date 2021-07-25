package com.example.sunba

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.customdialog.*

class MycustionDialog(context: Context , myCustiomDialog: MyCustiomDialog)//생성자
    : Dialog(context),View.OnClickListener   // 상속
 {

    private var myCustiomDialog: MyCustiomDialog? = null

     //인터페이스 연결
     init {
         this.myCustiomDialog=myCustiomDialog
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.customdialog)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        like_btn.setOnClickListener(this)
        subscribe_btn.setOnClickListener(this)
    }

     override fun onClick(view: View?) {
         when(like_btn){
             like_btn ->{
                 this.myCustiomDialog?.onLikeBtnClicked()
             }
             subscribe_btn ->{
                 this.myCustiomDialog?.onSubscribeBtnClicked()
             }
         }
     }
 }