package com.aceofhigh.diaryapp.presentation.screens.auth

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.aceofhigh.diaryapp.util.Constants.CLIENT_ID
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarState
import com.stevdzasan.onetap.OneTapSignInState
import com.stevdzasan.onetap.OneTapSignInWithGoogle

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticationScreen(
    loadingState: Boolean,
    onButtonClick: () -> Unit,
    oneTapSignInState: OneTapSignInState,
    messageBarState: MessageBarState
) {
    Scaffold(
        content = {
            ContentWithMessageBar(messageBarState = messageBarState) {
                AuthenticationContent(
                    loadingState = loadingState,
                    onButtonClick = onButtonClick
                )
            }
        }
    )

    OneTapSignInWithGoogle(
        state = oneTapSignInState,
        clientId = CLIENT_ID,
        onTokenIdReceived = { tokenId ->
            Log.d("Auth", tokenId)
            messageBarState.addSuccess("Successfully Authenticated!")
        },
        onDialogDismissed = { message ->
            Log.d("Auth", message)
            messageBarState.addError(Exception(message))

        }
    )
}