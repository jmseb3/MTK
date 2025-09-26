# MTK - Korean Number to Words Converter

`MTK`는 숫자를 한국어 금액 표기로 변환하는 Kotlin 라이브러리입니다.  
예를 들어 `123456`을 입력하면 `"십이만 삼백 오십육원"`과 같이 읽을 수 있습니다.

---

## 주요 기능

- Long, Int, String 타입 숫자를 한글로 변환
- `만`, `억`, `조` 등 큰 단위 지원
- 숫자 그룹 및 자리 단위 공백 옵션 지원
- "원" 앞 공백 옵션 지원
- 0 처리 및 큰 숫자 처리 지원

---

## 설치

Gradle:

```kotlin
dependencies {
    implementation("io.github.jmseb3:MTK:1.0.0")
}
```

## 사용예시

```kotlin
import io.github.jmseb3.MTK

fun main() {
    val amount = 123456L

    val korean1 = MTK.toKorean(amount)
    println(korean1) // 십이만 삼백 오십육원

    val korean2 = MTK.toKorean(amount, useSpacing = false)
    println(korean2) // 십이만삼백오십육원

    val korean3 = MTK.toKorean(amount, innerSplit = false)
    println(korean3) // 십이만삼백오십육원

    val korean4 = MTK.toKorean(amount, spaceBeforeWon = true)
    println(korean4) // 십이만 삼백 오십육 원
}
```