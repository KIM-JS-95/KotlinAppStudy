package com.example.univofseoul_meal.Utils

import android.text.TextUtils
import android.widget.TextView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

object WebCrawlingUtil {

    fun getMenuMap(address: String) {

        doAsync {
            val document = Jsoup.connect(address).get()

            println(document)

            //조식, 중식, 석식 메뉴 모두 가져옴.
            val menu = document.select("li.tch-lnc-wrap").select("dd.tch-lnc")

            println(menu)

            var result=""

            //즉각적으로 ui에 띄워주려면 이 스레드 사용해야함.(화면에 띄우는 건 여기에)
//            uiThread {
//                if(menu ==null){
//                    result = "이 시간은 운영하지 않습니다."
//                }
//                else{
//                    result = menu
//                }
//            }
        }

    }

    //html을 가지고 태그없애고 줄바꿈있는 텍스트로 만듬
    fun makeLineText(element : Element) : String{

        val list = element.html().split("<br>").toTypedArray()

        var result = ""
        list.forEach {
            if(it == " ") return@forEach
            result+=it+"\n"
        }
        return result
    }
    //MainActivity에서 건물을 누르면 그에 맞는 string 액티비티에서 보내주는데
    //받은 string을 이 함수에 넣어서 링크를 산출해내면 된다.

    fun getLink(address : String) : String{

        //학관 1층
        val address0 = "https://school.cbe.go.kr/sugok-m/M01030701/"

        val addressMap = mutableMapOf<String, String>()

        addressMap["address0"] = address0

        return addressMap[address]!!//결과물로 링크가 반환된다.
    }
}