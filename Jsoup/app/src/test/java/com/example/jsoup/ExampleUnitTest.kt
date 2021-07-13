package com.example.jsoup

import com.example.univofseoul_meal.Utils.WebCrawlingUtil
import org.jsoup.Jsoup
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun add(){
        val address = "https://school.cbe.go.kr/sugok-m/M01030701/"

        val document = Jsoup.connect(address).get()

        val menu = document.select("li.tch-lnc-wrap").select("dd.tch-lnc")

        WebCrawlingUtil.getMenuMap(address=address)


        print(menu)
    }

}

