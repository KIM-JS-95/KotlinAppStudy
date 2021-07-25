package com.example.sunba

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sunba.databinding.ActivityMainBinding
import com.example.sunba.databinding.ActivitySecondBinding
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() , MyCustiomDialog{

    lateinit var binding: ActivitySecondBinding

    var isLiked:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener { backfun()}

        isLiked = false
        binding.likeBtn.setOnClickListener {like()}
    }

    private fun backfun() {
       finish()
    }


    private fun like(){
        if(isLiked==false) {
            val animator = ValueAnimator.ofFloat(0.34f, 0.7f).setDuration(3000)
            animator.addUpdateListener { animation: ValueAnimator ->
                like_btn.setProgress(
                    animation.getAnimatedValue() as Float
                )
            }
            animator.start()
            isLiked=true
        }
        else{
            val animator = ValueAnimator.ofFloat(0.8f, 1f).setDuration(3000)
            animator.addUpdateListener { animation: ValueAnimator ->
                like_btn.setProgress(
                    animation.getAnimatedValue() as Float
                )
            }
            animator.start()
            isLiked=false
        }
    }

    fun onDialogBtnClicked(view: View) {
        val mycustionDialog = MycustionDialog(this, this)
        mycustionDialog.show()
    }


    // 구독 버튼
    override fun onSubscribeBtnClicked() {
        TODO("Not yet implemented")
        Toast.makeText(this, "구독 버튼 클릭" , Toast.LENGTH_SHORT).show()
    }

    // 좋아요
    override fun onLikeBtnClicked() {
        TODO("Not yet implemented")
        Toast.makeText(this, "좋아요 버튼 클릭" , Toast.LENGTH_SHORT).show()
    }
}