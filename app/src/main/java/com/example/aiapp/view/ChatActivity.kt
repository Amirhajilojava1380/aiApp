package com.example.aiapp.view

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.aiapp.R
import com.example.aiapp.item.MessageItem
import com.example.aiapp.item.PickPhotoButton
import com.example.aiapp.model.ChatMessage
import com.example.aiapp.utils.Content.FontText.chatGbt
import com.example.aiapp.view.ui.theme.AiAppTheme
import com.example.aiapp.viewmodel.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AiAppTheme {

            }
        }
    }
}

@SuppressLint("DefaultLocale")
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalGlideComposeApi::class
)
@Composable
fun GreetingChat(modifier: Modifier = Modifier, mainViewmodel: MainViewmodel? = null) {

    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    var textValueInput by remember { mutableStateOf("") }
    var isIconSentText by remember { mutableStateOf(false) }
    var isUserJustSentMessage by remember { mutableStateOf(true) }

    val listState = rememberLazyListState()
    val messages = remember {
        mutableStateListOf(
            ChatMessage("سلام", false),
            ChatMessage("خوبی امیر؟", false),
            ChatMessage("آره خوبم تو چطوری؟", true),
            ChatMessage("خوبه خدا رو شکر", false)
        )
    }


    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp


    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(modifier = Modifier.width(300.dp)) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { }) {
                                Icon(
                                    painter = painterResource(R.drawable.pen),
                                    contentDescription = "send",
                                    tint = Color.Black,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }

                            TextField(
                                value = "",
                                onValueChange = { },
                                modifier = Modifier
                                    .padding(10.dp)
                                    .height(54.dp)
                                    .clip(RoundedCornerShape(50))
                                    .fillMaxWidth(),
                                placeholder = {
                                    Text(
                                        text = "search",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                },
                                leadingIcon = {
                                    IconButton(onClick = { }) {
                                        Icon(
                                            painter = painterResource(R.drawable.baseline_search_24),
                                            contentDescription = "send",
                                            tint = Color.Black,
                                            modifier = Modifier.rotate(90f)
                                        )
                                    }
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedPlaceholderColor = Color.Gray,
                                    unfocusedPlaceholderColor = Color.Gray
                                )
                            )
                        }
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            items(
                                count = 10,
                                key = { it }
                            ) {
                                Text(
                                    modifier = Modifier.padding(12.dp),
                                    text = "SingUp",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Thin,
                                    color = Color.Black,
                                    fontFamily = chatGbt
                                )
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier.padding(6.dp),
                            horizontalArrangement = Arrangement.Center,

                            ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_account_circle_24),
                                contentDescription = "send",
                                tint = Color.Black,
                                modifier = Modifier
                                    .padding(6.dp)
                                    .size(25.dp)
                            )
                            Text(
                                modifier = Modifier.padding(12.dp),
                                text = "ID User",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Thin,
                                color = Color.Black,
                                fontFamily = chatGbt,
                            )

                        }
                    }
                }
            }
        ) {
            Scaffold(
                contentWindowInsets = WindowInsets.safeDrawing,
                containerColor = Color(0xFFE8E8E8),
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFFE8E8E8),
                            titleContentColor = Color(0xFFE8E8E8),
                        ),
                        title = {
                            Text(
                                text = "ChatGPT",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Thin,
                                color = Color.Black,
                                fontFamily = chatGbt
                            )
                        },
                        navigationIcon = {
                            Icon(
                                modifier = modifier
                                    .padding(start = 10.dp, end = 10.dp)
                                    .size(25.dp)
                                    .clickable {
                                        scope.launch { drawerState.open() }
                                    },
                                painter = painterResource(R.drawable.menu),
                                contentDescription = "menu"
                            )
                        },
                        scrollBehavior = null
                    )
                },
                bottomBar = {
                    BottomAppBar(
                        containerColor = Color(0xFFE8E8E8),
                        contentColor = Color(0xFFE8E8E8),
                        modifier = modifier
                            .padding(bottom = 6.dp)
                            .imePadding(),
                        actions = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                IconButton(onClick = {
                                    when {
                                        !isIconSentText && textValueInput.isNotEmpty() || selectedImage != null -> {
                                            messages.add(
                                                ChatMessage(
                                                    text = textValueInput,
                                                    isFromMe = false,
                                                    imageUri = selectedImage.toString(),
                                                    fileVoice = null
                                                )
                                            )
                                            isUserJustSentMessage = true
                                            textValueInput = ""
                                            selectedImage = null
                                        }

                                        isIconSentText -> {

                                        }

                                    }

                                }) {
                                    Icon(
                                        painter = painterResource(if (isIconSentText) R.drawable.recording else R.drawable.arrowup),
                                        contentDescription = "send",
                                        tint = Color.Black,
                                        modifier = modifier
                                            .padding(end = 5.dp)
                                            .fillMaxSize()
                                    )
                                }


                                TextField(
                                    value = textValueInput,
                                    onValueChange = { textValueInput = it },
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                        .fillMaxWidth()
                                        .weight(1f),
                                    placeholder = {
                                        Text("Ask GPT")
                                    },
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        focusedPlaceholderColor = Color.Gray,
                                        unfocusedPlaceholderColor = Color.Gray
                                    )
                                )

                                PickPhotoButton(
                                    { selectedImage = it },
                                    isIconMicrophon = false
                                )

                            }
                        }

                    )
                }
            ) { paddingValues ->
                Column(modifier = modifier.padding(paddingValues)) {

                    LazyColumn(
                        modifier = modifier.weight(1f),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.Top,
                        state = listState
                    ) {
                        items(messages.size) { index ->
                            MessageItem(messages[index],index == messages.lastIndex)
                        }
                        item {
                            val extraSpace = screenHeight * 0.3f
                            Spacer(Modifier.height(if (isUserJustSentMessage)extraSpace else 0.dp))
                        }

                    }
                    selectedImage?.let { uri ->
                        GlideImage(
                            model = uri,
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .size(130.dp)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .align(Alignment.End),
                            contentScale = ContentScale.Crop
                        )
                    }

                    LaunchedEffect(messages.size) {
                        if (!messages.lastOrNull()!!.isFromMe) {
                            isUserJustSentMessage = !isUserJustSentMessage
                            listState.animateScrollToItem(messages.lastIndex)
                            messages.add(
                                ChatMessage(
                                    text = "اگر بعد حذف Box مشکل کامل حل نشد\n" +
                                            "\n" +
                                            "دو علت دیگه می\u200Cتونه باشه:\n" +
                                            "\n" +
                                            "LazyColumn درست ارتفاع نگرفته → weight + fillMaxWidth لازم داری\n" +
                                            "\n" +
                                            "MessageItem padding زیاد داره → باعث میشه بخشی از پیام قبلی دیده بشه\n" +
                                            "\n" +
                                            "اگر خواستی پیام قبلی کامل محو بشه، عکس MessageItem یا کدش رو بفرست.\n" +
                                            "\n" +
                                            "ولی فعلاً ۹۹٪ مشکل تو همون Box(fillMaxSize) بود.",
                                    isFromMe = true,
                                )
                            )
                        } else {
                            isUserJustSentMessage = !isUserJustSentMessage
                            listState.animateScrollToItem(messages.lastIndex - 1)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    GreetingChat(mainViewmodel = null)
}



