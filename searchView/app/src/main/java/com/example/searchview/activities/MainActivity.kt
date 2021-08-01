package com.example.searchview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.searchview.utils.Constants
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.example.searchview.R
import com.example.searchview.retrofit.RetrofitManager
import com.example.searchview.utils.RESPONSE_STATUS
import com.example.searchview.utils.SEARCH_TYPE
import com.example.searchview.utils.onMyTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_button_search.*

class MainActivity : AppCompatActivity() {

    private var currentSearchTYPE: SEARCH_TYPE = SEARCH_TYPE.PHOTO;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(Constants.TAG, "onCreate() called")

        // 라디오 그룹 가져오기
        search_term_radio_group.setOnCheckedChangeListener { _, checkedId ->

            // 스위치 문

            when (checkedId) {
                R.id.photo_search_radio_btn -> {
                    Log.d(TAG, "사진 검색기능 클릭")
                    search_term_text_layout.hint = "사진검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(
                        R.drawable.ic_photo_library_black_24dp, resources.newTheme()
                    )
                    this.currentSearchTYPE = SEARCH_TYPE.PHOTO
                }
                R.id.user_search_radio_btn -> {
                    Log.d(TAG, "사용자 버튼 검색")
                    search_term_text_layout.hint = "사용자검색"
                    search_term_text_layout.startIconDrawable =
                        resources.getDrawable(R.drawable.ic_person_black_24dp, resources.newTheme())
                }
            }
            Log.d(TAG, "클릭 체인지")
        }


        // 텍스트가 변경이 되었을때
        search_term_edit_text.onMyTextChanged {
            // 입력된 글자가 있다면 검색 보튼 show-up
            if (it.toString().count() > 0) {
                frame_search_btn.visibility = View.VISIBLE

                // 스크롤을 올린다.
                main_scrollview.scrollTo(0, 200)

                search_term_text_layout.helperText = " "
            } else {
                frame_search_btn.visibility = View.INVISIBLE
                search_term_text_layout.helperText = "검색어를 입력해 주세요요"
            }

            if (it.toString().count() == 12) {
                Log.d(TAG, "에러 발상")
                Toast.makeText(this, "검색어는 12자 까지만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        btn_search.setOnClickListener {
            Log.d(TAG, "검색 버튼 클릭! : $currentSearchTYPE")

            this.handleSearchButtonUi()

            val userSearchInput = search_term_edit_text.text.toString()

            // 검색 api 호출
            RetrofitManager.instance.searchPhotos(
                searchTerm = search_term_edit_text.text.toString(),
                completion = { responseState, responseDataArrayList ->

                    when (responseState) {
                        RESPONSE_STATUS.OKAY -> {
                            Log.d(TAG, "api 호출 성공 : ${responseDataArrayList?.size}")

                            val intent = Intent(this, PhotoCollectionActivity::class.java)

                            val bundle = Bundle()

                            bundle.putSerializable("photo_array_list", responseDataArrayList)

                            intent.putExtra("array_bundle", bundle)

                            intent.putExtra("search_term", userSearchInput)

                            startActivity(intent)

                        }
                        RESPONSE_STATUS.FAIL -> {
                            Toast.makeText(this, "api 호출 에러입니다.", Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "api 호출 실패 : $responseDataArrayList")
                        }

                        RESPONSE_STATUS.NO_CONTENT -> {
                            Toast.makeText(this, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    btn_progress.visibility = View.INVISIBLE
                    btn_search.text = "검색"
                    search_term_edit_text.setText("")

                })
        }

    }

    private fun handleSearchButtonUi() {
        btn_progress.visibility = View.VISIBLE
        btn_search.text = ""

        Handler().postDelayed({
            btn_progress.visibility = View.VISIBLE
            btn_search.text = "검색"
        }, 1500)
    }
}