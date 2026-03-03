package org.connect.chat.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun CustomTextInput(
    value: String,
    onValueChange: (String) -> Unit,

    modifier: Modifier = Modifier,

    // Size
    width: Dp = 280.dp,
    height: Dp = 56.dp,

    // Border
    borderWidth: Dp = 1.dp,
    borderRadius: Dp = 8.dp,
    borderColor: Color = Color.Gray,
    focusedBorderColor: Color = Color.Blue,
    errorBorderColor: Color = Color.Red,

    // Text
    textColor: Color = Color.Black,
    focusedTextColor: Color = Color.Black,
    textSize: TextUnit = 16.sp,
    textAlign: TextAlign = TextAlign.Start,

    // Placeholder
    placeholder: String = "",
    placeholderColor: Color = Color.Gray,
    focusedPlaceholderColor: Color = Color.LightGray,
    placeholderSize: TextUnit = 14.sp,

    // Label / Hint
    label: String = "",
    labelColor: Color = Color.Gray,
    focusedLabelColor: Color = Color.Blue,
    labelSize: TextUnit = 14.sp,

    isError: Boolean = false,

    decoration: (@Composable (() -> Unit))? = null
) {

    BasicTextField(
        value = value,
        onValueChange = { newText ->
            onValueChange(newText)
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 64.dp) // margin left and right
                    .fillMaxWidth()
                    .background(color = Color(0xFFD2F3F2),
                        shape = RoundedCornerShape(size = 16.dp))
                    .border(
                        width = 2.dp,
                        color = Color(0xFFAAE9E6),
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .padding(all = 16.dp), // inner padding
                verticalAlignment = Alignment.CenterVertically
            ) {

                //add icon if required
                Spacer(modifier = Modifier.width(width = 8.dp))
                innerTextField()
            }
        }
    )
}




@Composable
fun CustomTextInput2(
    value: String,
    onValueChange: (String) -> Unit,

    modifier: Modifier = Modifier,

    // Size
    width: Dp = 280.dp,
    height: Dp = 56.dp,

    // Border
    borderWidth: Dp = 1.dp,
    borderRadius: Dp = 8.dp,
    borderColor: Color = Color.Gray,
    focusedBorderColor: Color = Color.Blue,
    errorBorderColor: Color = Color.Red,

    // Text
    textColor: Color = Color.Black,
    focusedTextColor: Color = Color.Black,
    textSize: TextUnit = 16.sp,
    textAlign: TextAlign = TextAlign.Start,

    // Placeholder
    placeholder: String = "",
    placeholderColor: Color = Color.Gray,
    focusedPlaceholderColor: Color = Color.LightGray,
    placeholderSize: TextUnit = 14.sp,

    // Label / Hint
    label: String = "",
    labelColor: Color = Color.Gray,
    focusedLabelColor: Color = Color.Blue,
    labelSize: TextUnit = 14.sp,

    isError: Boolean = false,

    decoration: (@Composable (() -> Unit))? = null
) {

    var borderColor  by remember { mutableStateOf<Color>(Color(0xFF848888)) }

    BasicTextField(
        value = value,
        onValueChange = { newText ->
            onValueChange(newText)
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        ),
        singleLine = true,
        modifier = Modifier
            .onFocusChanged { focusState ->
                borderColor = if (focusState.isFocused) {
                    Color(0xFFAAE9E6)
                } else {
                    Color(0xFF848888)
                }
            },
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 64.dp)
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .onFocusChanged({ it ->
                        borderColor = if(it.isFocused){
                             Color(0xFFAAE9E6)
                        }else{
                             Color(0xFF848888)
                        }
                    })
                    .focusable()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    )
                }
                innerTextField()
            }
        }
    )
}



@Composable
fun CustomTextInput3(
    value: String,
    onValueChange: (String) -> Unit,

    modifier: Modifier = Modifier,

    // Size
    width: Dp = 300.dp,
    height: Dp = 50.dp,

    // Border
    borderWidth: Dp = 1.dp,
    borderRadius: Dp = 8.dp,
    borderColor: Color = Color.Gray,
    focusedBorderColor: Color = Color.Blue,
    errorBorderColor: Color = Color.Red,

    // Text
    textColor: Color = Color.Black,
    focusedTextColor: Color = Color.Black,
    textSize: TextUnit = 16.sp,
    textAlign: TextAlign = TextAlign.Start,

    // Placeholder
    placeholder: String = "",
    placeholderColor: Color = Color.Gray,
    focusedPlaceholderColor: Color = Color.LightGray,
    placeholderSize: TextUnit = 14.sp,

    // Label / Hint
    label: String = "",
    labelColor: Color = Color.Gray,
    focusedLabelColor: Color = Color.Blue,
    labelSize: TextUnit = 14.sp,

    isError: Boolean = false,

    decoration: (@Composable (() -> Unit))? = null
) {

    var borderColor  by remember { mutableStateOf<Color>(Color(0xFF848888)) }

    BasicTextField(
        value = value,
        onValueChange = { newText ->
            onValueChange(newText)
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        ),
        modifier = Modifier
            .padding(10.dp)
            .width(width)
            .height(height)
            .onFocusChanged { focusState ->
                borderColor = if (focusState.isFocused) {
                    Color(0xFFAAE9E6)
                } else {
                    Color(0xFF848888)
                }
            },
        decorationBox = { innerTextField ->
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {

                    // Placeholder
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            fontSize = 18.sp,
                            color = Color.LightGray
                        )
                    }

                    // Actual input text
                    innerTextField()
                }

                // Bottom line
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(borderColor)
                        .height(1.dp)

                )
            }
        }
    )
}


