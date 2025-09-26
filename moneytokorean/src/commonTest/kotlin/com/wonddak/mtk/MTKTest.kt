package com.wonddak.mtk

import kotlin.test.Test
import kotlin.test.assertEquals


class MTKTest {

    @Test
    fun `0원`() {
        assertEquals("영원", MTK.toKorean(0))
    }

    @Test
    fun `한자리`() {
        assertEquals("일원", MTK.toKorean(1))
        assertEquals("구원", MTK.toKorean(9))
    }

    @Test
    fun `십 단위`() {
        assertEquals("십원", MTK.toKorean(10))
        assertEquals("이십원", MTK.toKorean(20))
        assertEquals("구십원", MTK.toKorean(90))
    }

    @Test
    fun `백 단위`() {
        assertEquals("백원", MTK.toKorean(100))
        assertEquals("백 이십원", MTK.toKorean(120))
        assertEquals("백 이십 일원", MTK.toKorean(121))
        assertEquals("이백원", MTK.toKorean(200))
        assertEquals("구백원", MTK.toKorean(900))
    }

    @Test
    fun `천 단위`() {
        assertEquals("천원", MTK.toKorean(1000))
        assertEquals("이천원", MTK.toKorean(2000))
        assertEquals("구천원", MTK.toKorean(9000))
    }

    @Test
    fun `만 단위`() {
        assertEquals("만원", MTK.toKorean(10000))
        assertEquals("십만원", MTK.toKorean(100000))
        assertEquals("백만원", MTK.toKorean(1_000_000))
        assertEquals("천만원", MTK.toKorean(10_000_000))
    }

    @Test
    fun `억 단위`() {
        assertEquals("일억원", MTK.toKorean(100_000_000))
        assertEquals("십억원", MTK.toKorean(1_000_000_000))
    }

    @Test
    fun `복합 단위`() {
        assertEquals("십 이만 삼백 이십원", MTK.toKorean(120_320))
        assertEquals("구천 구백 구십 구만 구천 구백 구십 구원", MTK.toKorean(99_999_999))
    }

    @Test
    fun `문자열 입력`() {
        assertEquals("십 이만 삼백 이십원", MTK.toKorean("120,320"))
    }

    @Test
    fun `옵션 - 띄어쓰기`() {
        assertEquals("십만 원", MTK.toKorean(100000, useSpacing = false, spaceBeforeWon = true))
    }

    @Test
    fun `너무 큰수 `() {
        assertEquals("숫자가 너무 큽니다", MTK.toKorean("7657567888887098097777777"))
    }
}