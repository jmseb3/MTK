package io.github.jmseb3

object MTK {

    private val digitNames = arrayOf("", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구")

    private val unitWithin = arrayOf("천", "백", "십", "")

    private val bigUnits = arrayOf("", "만", "억", "조", "경", "해")

    /**
     * 들어온 돈의 값을 한글로 읽습니다.
     *
     * @param input 숫자 값 (Long)
     * @param useSpacing 그룹(만, 억, 조 등) 사이에 공백을 넣을지 여부
     *                   true: "십이만 삼백 이십"
     *                   false: "십이만삼백이십"
     * @param spaceBeforeWon "원" 앞에 공백을 넣을지 여부
     *                   true: "이십 원"
     *                   false: "이십원"
     * @param innerSplit 그룹 내부에서 천/백/십/일을 분리해 공백을 넣을지 여부
     *                   true: 자리별 공백 (예: 구천 구백 구십 구)
     *                   false: 붙여쓰기 (예: 구천구백구십구)
     */
    fun toKorean(
        input: Long,
        useSpacing: Boolean = true,
        spaceBeforeWon: Boolean = false,
        innerSplit: Boolean = true
    ): String = toKorean(input.toString(), useSpacing, spaceBeforeWon, innerSplit)

    /**
     * 들어온 돈의 값을 한글로 읽습니다.
     *
     * @param input 돈의 값
     * @param useSpacing 그룹 사이에 공백 넣을지 (e.g. "십이만 삼백 이십")
     * @param spaceBeforeWon "원" 앞에 공백 넣을지 (e.g. "... 이십 원")
     * @param innerSplit 그룹 내부에서 천/백/십/일 을 분리해 공백을 넣을지 여부.
     *                   false: 기존 동작 (그룹 내부 붙여쓰기, ex: 구천구백구십구)
     *                   true: 그룹 내부를 두 덩어리로 나눠 공백 삽입 (ex: 구천 구백 구십 구)
     */
    fun toKorean(
        input: Int,
        useSpacing: Boolean = true,
        spaceBeforeWon: Boolean = false,
        innerSplit: Boolean = true
    ): String = toKorean(input.toString(), useSpacing, spaceBeforeWon, innerSplit)

    /**
     * 들어온 돈의 값을 한글로 읽습니다.
     *
     * @param input 돈 문자열 (쉼표 가능) (e.g. 123,456)
     * @param useSpacing 그룹 사이에 공백 넣을지 (e.g. "십이만 삼백 이십")
     * @param spaceBeforeWon "원" 앞에 공백 넣을지 (e.g. "... 이십 원")
     * @param innerSplit 그룹 내부에서 천/백/십/일 을 분리해 공백을 넣을지 여부.
     *                   false: 기존 동작 (그룹 내부 붙여쓰기, ex: 구천구백구십구)
     *                   true: 그룹 내부를 두 덩어리로 나눠 공백 삽입 (ex: 구천 구백 구십 구)
     */
    fun toKorean(
        input: String,
        useSpacing: Boolean = true,
        spaceBeforeWon: Boolean = false,
        innerSplit: Boolean = true
    ): String {
        val num = input.replace(",", "").trim()
        if (num.isEmpty() || !num.all { it.isDigit() }) return ""

        // 0 처리
        if (num.all { it == '0' }) return buildString { append("영"); if (spaceBeforeWon) append(" "); append("원") }

        // 오른쪽 끝부터 4자리씩 그룹화
        val groups = num.reversed().chunked(4).map { it.reversed() }.reversed()

        // "해" 이상의 숫자는 변환 불가
        if (groups.size > bigUnits.size) return "숫자가 너무 큽니다"

        val parts = mutableListOf<String>()

        for ((idx, grp) in groups.withIndex()) {
            // 4자리 맞춤
            val groupVal = grp.padStart(4, '0').map { it - '0' }

            val chunk = if (innerSplit) {
                buildString {
                    for (i in 0 until 4) {
                        val d = groupVal[i]
                        if (d == 0) continue
                        val isHighUnit = i != 3
                        if (d == 1 && isHighUnit) append(unitWithin[i])
                        else append(digitNames[d]).append(unitWithin[i])
                        append(" ")
                    }
                }.trim()
            } else {
                buildString {
                    for (i in 0 until 4) {
                        val d = groupVal[i]
                        if (d == 0) continue
                        val isHighUnit = i != 3
                        if (d == 1 && isHighUnit) append(unitWithin[i])
                        else append(digitNames[d]).append(unitWithin[i])
                    }
                }
            }

            if (chunk.isNotEmpty()) {
                val bigUnit = bigUnits[groups.size - 1 - idx]
                if (chunk == "일" && bigUnit == "만") parts.add(bigUnit)
                else parts.add(chunk + bigUnit)
            }
        }

        print("<<$parts")
        val joined = if (useSpacing) parts.joinToString(" ") else parts.joinToString("")

        return buildString {
            append(joined)
            if (spaceBeforeWon) append(" ")
            append("원")
        }
    }
}