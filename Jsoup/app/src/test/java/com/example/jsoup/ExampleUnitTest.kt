package com.example.jsoup


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
        val address = "https://www.epicgames.com/store/ko/free-games"

        val document = Jsoup.connect(address).get()

        val menu = document.select("div.css-ro5hlk-DiscoverPage__storeContent").select("div.css-shu77l")
        //WebCrawlingUtil.getMenuMap(address=address)


        print(menu)
    }

}

