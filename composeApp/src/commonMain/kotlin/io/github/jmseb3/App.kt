package io.github.jmseb3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var inputText by remember { mutableStateOf("") }
        val showDeleteIcon by remember(inputText) {
            derivedStateOf { inputText.isNotEmpty() }
        }
        var useSpacing by remember {
            mutableStateOf(true)
        }
        var spaceBeforeWon by remember {
            mutableStateOf(true)
        }
        var innerSplit by remember {
            mutableStateOf(true)
        }
        val convertText by remember(inputText, useSpacing, spaceBeforeWon, innerSplit) {
            derivedStateOf {
                MTK.toKorean(
                    input = inputText,
                    useSpacing = useSpacing,
                    spaceBeforeWon = spaceBeforeWon,
                    innerSplit = innerSplit
                )
            }
        }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            SwitchContent(
                checked = useSpacing,
                onCheckedChange = {
                    useSpacing = it
                },
                title = "그룹 사이 공백 여부"
            )
            SwitchContent(
                checked = spaceBeforeWon,
                onCheckedChange = {
                    spaceBeforeWon = it
                },
                title = "'원'앞에 공백 추가 여부"
            )
            SwitchContent(
                checked = innerSplit,
                onCheckedChange = {
                    innerSplit = it
                },
                title = "그룹 사이 공백 추가 여부"
            )
            TextField(
                modifier = Modifier.width(600.dp),
                value = inputText,
                onValueChange = {
                    inputText = it
                },
                supportingText = {
                    Text(convertText)
                },
                trailingIcon = if (showDeleteIcon) {
                    {
                        IconButton(
                            onClick = {
                                inputText = ""
                            }
                        ) {
                            Icon(Icons.Filled.Clear, null)
                        }
                    }
                } else {
                    null
                }
            )

        }
    }
}

@Composable
private fun SwitchContent(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    title: String
) {
    Row(
        modifier = Modifier.width(600.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(title)
    }
}