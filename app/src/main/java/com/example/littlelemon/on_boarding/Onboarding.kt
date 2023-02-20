package com.example.littlelemon.on_boarding

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.highlightColor1
import com.example.littlelemon.ui.theme.primaryColor2
import com.example.littlelemon.ui.theme.secondaryColor1
import com.example.littlelemon.ui.theme.secondaryColor2


@Composable
fun OnboardingHeader() {
    val imageModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 20.dp)
        .height(50.dp)
    Image(
        painter = painterResource(id = R.drawable.logo),
        modifier = imageModifier,
        contentScale = ContentScale.Fit,
        contentDescription = stringResource(id = R.string.app_logo)
    )
}

@Composable
fun OnboardingTitle() {
    val labelModifier = Modifier
        .fillMaxWidth()
        .background(Color(73, 94, 87))
        .padding(horizontal = 15.dp, vertical = 25.dp)
    Text(
        modifier = labelModifier,
        text = "Let's get to know you",
        color = Color.White,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body1
    )
}

/**
 * Modifier is optional param
 */
@Composable
fun OnboardingLabel(value: String, modifier: Modifier = Modifier) {

    Text(
        modifier = modifier,
        text = value,
        style = MaterialTheme.typography.h1
    )
}


@Composable
fun OnboardingTextField(value: String) {
    var txt by remember { mutableStateOf("") }

    Column(
    ) {
        Text(value)
        BasicTextField(
            maxLines = 2,
            textStyle = MaterialTheme.typography.body1,
            value = txt, onValueChange = { newText ->
                txt = newText
            },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp) // Margin
                        .border(
                            width = 2.dp,
                            color = highlightColor1,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(all = 16.dp) // Content padding
                ) {
                    // Place holder
                    if (txt.isEmpty()) {
                        Text(
                            text = value,
                            color = highlightColor1
                        )
                    }
                    innerTextField()
                }

            }


        )
    }

}

@Composable
fun OnboardingResgierButton() {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(2.dp, secondaryColor1),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        onClick = {
            print("Hello There")
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = primaryColor2)
    ) {
        Text(text = "Register", style = MaterialTheme.typography.body1)
    }
}

@Composable
fun OnboardingContent() {
    val colModifier = Modifier
        .padding(horizontal = 15.dp)

    Column(
        modifier = colModifier
    ) {
        OnboardingLabel(
            "Personal information",
            modifier = Modifier
                .padding(vertical = 30.dp)
        )
        OnboardingTextField("First name")
        OnboardingTextField("Last name")
        OnboardingTextField("Email address")
        Spacer(
            modifier = Modifier.weight(1.0F)
        )
        OnboardingResgierButton()
    }
}


@Composable
@Preview(showBackground = true)
fun OnboardingView() {
    val colModifier = Modifier
        .fillMaxSize()
        .padding(bottom = 15.dp)
    Column(
        modifier = colModifier
    ) {

        OnboardingHeader()
        OnboardingTitle()
        OnboardingContent()


    }
}