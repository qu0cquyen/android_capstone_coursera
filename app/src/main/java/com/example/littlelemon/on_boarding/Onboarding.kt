package com.example.littlelemon.on_boarding

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.NavigationDirections
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.highlightColor1
import com.example.littlelemon.ui.theme.primaryColor2
import com.example.littlelemon.ui.theme.secondaryColor1


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
        modifier = modifier, text = value, style = MaterialTheme.typography.h1
    )
}


@Composable
fun OnboardingTextField(
    placeHolder: String,
    value: String,
    valueUpdated: (String) -> Unit,
    valueError: Boolean,
    errorMessage: String,
) {
    var txt by remember { mutableStateOf(value) }

    Column(
    ) {
        Text(placeHolder)
        BasicTextField(maxLines = 2,
            textStyle = MaterialTheme.typography.body1,
            value = txt,
            onValueChange = { valueChanged ->
                valueUpdated?.invoke(valueChanged);
                txt = valueChanged
            },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = if (valueError) 0.dp else 20.dp)
                        .border(
                            width = 2.dp,
                            color = if (valueError) Color.Red else highlightColor1,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(all = 16.dp) // Content padding
                ) {
                    // Place holder
                    if (txt.isEmpty()) {
                        Text(
                            text = placeHolder, color = highlightColor1
                        )
                    }
                    innerTextField()
                }

            })
        if (valueError) {
            Text(
                // Margin
                modifier = Modifier.padding(bottom = 20.dp), text = errorMessage, color = Color.Red
            )
        }
    }

}

@Composable
fun OnboardingResgierButton(registerPressed: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(2.dp, secondaryColor1),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        onClick = registerPressed,
        colors = ButtonDefaults.buttonColors(backgroundColor = primaryColor2)
    ) {
        Text(text = "Register", style = MaterialTheme.typography.body1)
    }
}

@Composable
fun OnboardingContent(navController: NavHostController) {
    val context = LocalContext.current

    val colModifier = Modifier.padding(horizontal = 15.dp)

    var firstName by remember { mutableStateOf("") }
    var invalidFirstName by remember { mutableStateOf(false) }
    var firstNameErrorMessage by remember { mutableStateOf("") }

    var lastName by remember { mutableStateOf("") }
    var invalidLastName by remember { mutableStateOf(false) }
    var lastNameErrorMessage by remember { mutableStateOf("") }

    var emailAddress by remember { mutableStateOf("") }
    var invalidEmailAddress by remember { mutableStateOf(false) }
    var emailAddressErrorMessage by remember { mutableStateOf("") }

    Column(
        modifier = colModifier
    ) {
        OnboardingLabel(
            "Personal information", modifier = Modifier.padding(vertical = 30.dp)
        )
        OnboardingTextField(
            "First name",
            firstName,
            { value -> firstName = value },
            invalidFirstName,
            firstNameErrorMessage,
        )
        OnboardingTextField(
            "Last name",
            lastName,
            { value -> lastName = value },
            invalidLastName,
            lastNameErrorMessage
        )
        OnboardingTextField(
            "Email address",
            emailAddress,
            { value -> emailAddress = value },
            invalidEmailAddress,
            emailAddressErrorMessage
        )
        Spacer(
            modifier = Modifier.weight(1.0F)
        )

        if (!invalidFirstName && firstName.isNotBlank() && !invalidLastName && lastName.isNotBlank() && !invalidEmailAddress && emailAddress.isNotBlank()) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Registration successful!",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
        }

        if (invalidFirstName || invalidLastName || invalidEmailAddress) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Registration unsuccessful. Please enter all data.",
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        }


        OnboardingResgierButton(registerPressed = {
            invalidFirstName = firstName.isBlank()
            invalidLastName = lastName.isBlank()
            invalidEmailAddress = emailAddress.isBlank() || (!emailAddress.contains("@"))



            firstNameErrorMessage = if (invalidFirstName) {
                "First name is required"
            } else {
                ""
            }

            lastNameErrorMessage = if (invalidLastName) {
                "Last name is required"
            } else {
                ""
            }

            emailAddressErrorMessage = (if (invalidEmailAddress) {
                if (emailAddress.isBlank()) {
                    "Email address is required"
                } else {
                    "Email address should contain @"
                }
            } else {
                ""
            }).toString()

            // Save info to SharedPrefs
            if (!invalidFirstName && !invalidLastName && !invalidEmailAddress) {
                val sharedPrefs =
                    context.getSharedPreferences("app-prefs", Context.MODE_PRIVATE).edit()

                sharedPrefs.putBoolean("on-board", false)
                sharedPrefs.putString("first-name", firstName)
                sharedPrefs.putString("last-name", lastName)
                sharedPrefs.putString("email-address", emailAddress)
                sharedPrefs.apply()
                navController.navigate("Home")
            }

            println("On register Pressed")

        })
    }
}


@Composable
fun OnboardingView(navController: NavHostController) {


    val colModifier = Modifier
        .fillMaxSize()
        .padding(bottom = 15.dp)

    Column(
        modifier = colModifier
    ) {

        OnboardingHeader()
        OnboardingTitle()
        OnboardingContent(navController)


    }
}
