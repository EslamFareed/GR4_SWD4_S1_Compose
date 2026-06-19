@file:OptIn(ExperimentalMaterial3Api::class)

package com.depi.ghr4_swd4_s1_compose

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyApp(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splash"){
        composable("home"){
            HomeScreen()
        }
        composable("splash"){
            SplashScreen(navController)
        }
    }
}


@Composable
fun SplashScreen(navController: NavHostController){
    val alpha = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(2000)
        )
        delay(3000)
        navController.navigate("home"){
            popUpTo("splash"){inclusive = true}
        }
    }
    Box(
        modifier = Modifier.fillMaxSize().alpha(alpha.value),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("My App", fontSize = 32.sp)
            CircularProgressIndicator()
        }
    }
}


@Composable
fun HomeScreen(){

    val context = LocalContext.current

    val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    Scaffold(
        topBar = { TopAppBar(title = {Text("Home")}) }
    ) { paddingValues -> Column(modifier = Modifier.padding(paddingValues)) {
        Button(onClick = {
            prefs.getString("token","")
            prefs.edit().putString("token","token value").apply()
        }){}
    } }
}





/*
@Composable
fun HomeScreen(){

    var seconds by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if(isRunning){
            while (true){
                delay(1000)
                seconds++
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Seconds = $seconds", fontSize = 40.sp)
        Button(onClick = { isRunning = true }) { Text("Start") }
        Button(onClick = { isRunning = false }) { Text("Stop") }
    }
}
*/
/*
//
//@Composable
//fun HomeScreen(){
//
//    var checked by remember { mutableStateOf(false) }
//    val scope = rememberCoroutineScope()
//
//    LaunchedEffect(Unit) {
//        Log.d("Test","LaunchedEffect")
//    }
//
//    SideEffect {
//        Log.d("Test","SideEffect")
//    }
//
//    val job = remember { mutableStateOf<Job?>(null) }
//
//    Scaffold() { paddingValues ->
//        Column(modifier = Modifier.padding(paddingValues)) {
//            Checkbox(checked, onCheckedChange = {checked = !checked})
//
//            Button(onClick = {
//                // Job
//                job.value = scope.launch {
//                    // Run Task
//                }
//            }) {
//                Text("Download")
//            }
//            Button(onClick = {
//               job.value?.cancel()
//            }) {
//                Text("Cancel")
//            }
//        }
//    }
//}
*/

/*
@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(millis: Long) : String{
    val instant = Instant.ofEpochMilli(millis)
    val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return localDate.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(){

    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var showAlertDialog by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    var showTimePickerDialog by remember { mutableStateOf(false) }
    val timeState = rememberTimePickerState()

    var showDatePickerDialog by remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                Toast.makeText(context,"Login Failed",Toast.LENGTH_SHORT).show()
            }) { Text("Show Toast") }
            Button(onClick = {
                scope.launch {
                   val result = snackBarHostState.showSnackbar(
                        "Login Failed",
                        withDismissAction = true,
                        actionLabel = "Undo"
                    )
                }
            }) { Text("Show SnackBar") }
            Button(onClick = { showAlertDialog = true }) { Text("Show Alert Dialog") }

            if(showAlertDialog) {
                AlertDialog(
                    icon = {Icon(Icons.Default.Close, contentDescription = "")},
                    title = { Text("Title") },
                    text = { Text("This is alert Dialog") },
                    onDismissRequest = {  }, // click outside dialog and click back press
                    confirmButton = {
                        Button(onClick = { showAlertDialog = false }) { Text("OK") }
                    },
                    dismissButton = {
                        Button(onClick = { showAlertDialog = false }) { Text("Cancel") }
                    }
                )
            }


            Button(onClick = { showDialog = true }) { Text("Show Dialog") }
            if (showDialog){
                Dialog(
                    onDismissRequest = {}
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(16.dp)
                    ){
                        Column {
                            Text("Custom Dialog")
                            Button(onClick = { showDialog = false }) { Text("Close Dialog") }
                        }
                    }
                }
            }


            Button(onClick = { showTimePickerDialog = true }) { Text("Show Time Picker Dialog") }
             if (showTimePickerDialog){
                AlertDialog(
                    onDismissRequest = {},
                    confirmButton = {
                        Button(onClick = {
                            val hour = timeState.hour
                            val minute = timeState.minute
                            showTimePickerDialog = false
                        }){Text("OK")}
                    },
                    text = {
                        TimePicker(timeState)
                    }
                )
            }


            Button(onClick = { showDatePickerDialog = true }) { Text("Show Date Picker Dialog") }
            if (showDatePickerDialog){
                DatePickerDialog(
                    onDismissRequest = {},
                    confirmButton = {
                        Button(onClick = {
                            Log.d("SelectedDate",formatDate(dateState.selectedDateMillis!!))
                            showDatePickerDialog = false
                        }) { Text("Select") }
                    }
                ) {
                    DatePicker(dateState)
                }
            }

        }
    }
}



 */

/*

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = viewModel()){
    val email = viewModel.email.value
    val password = viewModel.password.value
    val currentState = viewModel.currentState.value

    if (currentState == LoginState.Success){
        navController.navigate("home"){
            popUpTo("login"){
                inclusive = true
            }
        }
    }
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = email, onValueChange = {viewModel.changeEmail(it)})
            TextField(value = password, onValueChange = {viewModel.changePassword(it)})

            when (currentState) {
                LoginState.Loading,
                LoginState.Success -> CircularProgressIndicator()

                else -> {
                    Button(onClick = { viewModel.login() }, enabled = false)
                    { Text("Login") }
                }
            }
        }
    }
}

class LoginViewModel : ViewModel(){
    val email = mutableStateOf("")
    val password = mutableStateOf("")

    fun changeEmail(newText:String){
        email.value = newText
    }
    fun changePassword(newText:String){
        password.value = newText
    }

    val currentState = mutableStateOf(LoginState.Initial)

    fun login(){
        viewModelScope.launch {
            currentState.value = LoginState.Loading
            try {
                val response = RetrofitInstance.api.login(LoginRequest(email = email.value, password = password.value))
                if (response.isSuccessful){
                    currentState.value = LoginState.Success
                }else{
                    currentState.value = LoginState.Error
                }
            }catch (e: Exception){
                currentState.value = LoginState.Error
            }
        }
    }
}

enum class LoginState{
    Initial,Loading,Success,Error
}



@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()){
    val currentState = viewModel.currentState.value
    val products = viewModel.products.value
    val messageError = viewModel.messageError.value

    when(currentState){
        HomeState.Loading -> HomeLoading()
        HomeState.Error -> HomeError(messageError)
        HomeState.Success -> HomeProducts(products)
    }
}

@Composable
fun HomeLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun HomeError(messageError: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(messageError)
    }
}

@Composable
fun HomeProducts(products: List<ProductModel>) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Home") }) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(products) { item ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            item.images[0],
                            contentDescription = "",
                            modifier = Modifier.size(100.dp)
                        )
                        Column {
                            Text(item.title)
                            Text(item.description)
                            Text(item.price.toString())
                        }
                    }
                }
            }
        }
    }
}

class HomeViewModel : ViewModel(){
    val currentState = mutableStateOf(HomeState.Loading)
    val products = mutableStateOf(listOf<ProductModel>())
    val messageError = mutableStateOf("")
    init {
        loadProducts()
    }
    fun loadProducts(){
        viewModelScope.launch {
            currentState.value = HomeState.Loading
            try {
                val response = RetrofitInstance.api.getProducts()
                if (response.isSuccessful){
                    products.value = response.body()!!
                    currentState.value = HomeState.Success
                }else{
                    messageError.value = "Error, Please try again later"
                    currentState.value = HomeState.Error
                }
            }catch (e: Exception){
                messageError.value = "Error, ${e.message.toString()}"
                currentState.value = HomeState.Error
            }
        }
    }
}
enum class HomeState{
    Loading,Success,Error
}



 */


/*
@Preview(showSystemUi = true)
@Composable
fun MyApp(){

    val navController = rememberNavController()

    MaterialTheme {
        NavHost(navController, startDestination = "main"){
            composable("main"){
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){

    var currentIndex by remember { mutableStateOf(0) }

    Scaffold (
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentIndex == 0,
                    onClick = {
                        currentIndex = 0
                    },
                    icon = {Icon(Icons.Default.Home, contentDescription = "Home")},
                    label = {Text("Home")}
                )
                NavigationBarItem(
                    selected = currentIndex == 1,
                    onClick = {
                        currentIndex = 1
                    },
                    icon = {Icon(Icons.Default.Search, contentDescription = "Search")},
                    label = {Text("Search")}
                )
                NavigationBarItem(
                    selected = currentIndex == 2,
                    onClick = {
                        currentIndex = 2
                    },
                    icon = {Icon(Icons.Default.Person, contentDescription = "Profile")},
                    label = {Text("Profile")}
                )
            }
        }


    ){ paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TabRow(currentIndex) {
                Tab(
                    selected = currentIndex == 0,
                    onClick = {currentIndex = 0},
                    text = {Text("Home")}
                )
                Tab(
                    selected = currentIndex == 1,
                    onClick = {currentIndex = 1},
                    text = {Text("Search")}
                )
                Tab(
                    selected = currentIndex == 2,
                    onClick = {currentIndex = 2},
                    text = {Text("Profile")}
                )
            }
            when(currentIndex){
                0 -> HomeScreen()
                1 -> SearchScreen()
                2 -> ProfileScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(){
    Scaffold (
        topBar = { TopAppBar(title = {Text("Home")}) }
    ){ paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

@Composable
fun ProfileScreen(){
    Scaffold (
        topBar = { TopAppBar(title = {Text("Profile")}) }
    ){ paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

@Composable
fun SearchScreen(){
    Scaffold (
        topBar = { TopAppBar(title = {Text("Search")}) }
    ){ paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}


*/
/*
@Composable
fun MyApp(){

    val colors = if(isSystemInDarkTheme()){
        darkColorScheme(
            primary = Color(0xFFFF9100),
            secondary = Color(0xFF000000),
        )
    }else{
        lightColorScheme(
            primary = Color(0xFF651FFF),
            secondary = Color(0xFFFFFFFF),
        )
    }

    val navController = rememberNavController()

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(
            bodySmall = TextStyle(
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        )
    ) {
        NavHost(navController, startDestination = "home"){
            composable("home"){
                HomeScreen(navController)
            }
            composable("profile/{userId}"){  backStackEntry ->
                // profile/123
                val userId = backStackEntry.arguments?.getString("userId")
                ProfileScreen(navController, userId = userId!!)
            }
//            composable("profile"){
//                ProfileScreen(navController)
//            }
        }
    }

    /*
    val names = listOf("Ali","Omar","Mohamed","Ibrahim")

    LazyRow {
        items(names){ name ->
            Card (
                modifier = Modifier
                    .padding(8.dp)
            ){
                Text("Item $name", modifier = Modifier.padding(16.dp),fontSize = 20.sp)
            }
        }
    }
    LazyColumn {
//        items(items.size){ index ->
//            Text("Item ${items[index]}", fontSize = 20.sp)
//        }

        items(names){ name ->
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ){
                Text("Item $name", modifier = Modifier.padding(16.dp),fontSize = 20.sp)
            }
        }
    }

     */
}


@Composable
fun HomeScreen(navController: NavHostController){
    val userId = "123"
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Home")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                navController.navigate("profile/$userId")
            }) { Text("Go To Profile") }
        }
    }
}

@Composable
fun ProfileScreen(navController: NavHostController,userId:String){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Profile")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                    }
                }
            )

        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(userId, fontSize = 30.sp, color = Color.Black)
        }
    }
}


*/

/*
class HomeViewModel : ViewModel(){
    val passwordText = mutableStateOf("")
    fun changePasswordText(newText:String){
        passwordText.value = newText
    }

    val showPassword = mutableStateOf(false)
    fun changeShowPassword () {
        showPassword.value = !showPassword.value
    }


    val emailError = mutableStateOf(false)
    val emailText = mutableStateOf("")
    fun changeEmailText(newText:String){
        emailText.value = newText

        if (newText.contains("@") && newText.contains(".com")){
            emailError.value = false
        }else{
            emailError.value = true
        }
    }


    var counter = mutableStateOf(0)

    fun increment(){
        counter.value++
    }



    var checked = mutableStateOf(false)

    fun changeChecked(){
        checked.value = !(checked.value)
    }
}


@Composable
fun HomeScreen( viewModel: HomeViewModel = viewModel() ){
//    var counter by remember { mutableStateOf(0) }

    val email = viewModel.emailText.value
    val emailError = viewModel.emailError.value
    val password = viewModel.passwordText.value
    val showPassword = viewModel.showPassword.value

    Scaffold(
        containerColor = MaterialTheme.colorScheme.secondary,
        topBar = {
            TopAppBar(
                actions = {
                    IconButton (onClick = {}){
                        Icon(Icons.Default.Search, contentDescription = "")
                    }
                    IconButton (onClick = {}){
                        Icon(Icons.Default.Notifications, contentDescription = "")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                title = {Text("Home")}
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {Icon(Icons.Default.Search, contentDescription = "") }
        },

    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = email,
                onValueChange = {newText -> viewModel.changeEmailText(newText)},
                isError = emailError,
                supportingText = {
                    if (emailError) {
                        Text("Email format is invalid", color = Color.Red) // @ + .com
                    } else {
                        null
                    }
                }
            )

            OutlinedTextField(
                value = password,
                onValueChange = { newText -> viewModel.changePasswordText(newText) },
                visualTransformation =
                        if (showPassword) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        }
                    ,
                trailingIcon = { IconButton(onClick = {
                    viewModel.changeShowPassword()
                }) {
                    Icon(imageVector = if (showPassword) {
                        Icons.Default.Check
                    } else {
                        Icons.Default.Close
                    }, contentDescription = "")
                } },
                label = {Text("Password")},
            )
        }
    }
}



*/

/*
@Composable
fun MyScreen2(){

//    var isMale by remember { mutableStateOf(false) }

    var gender by remember { mutableStateOf("female") }

    Column(
        modifier = Modifier.padding(20.dp).fillMaxSize()
    ) {
        Text("Gender")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = { gender = "male" })
        ) {
            RadioButton(
                selected = gender == "male",
                onClick = {
                    gender = "male"
                }
            )
            Text("Male")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = gender == "female",
                onClick = {
                    gender = "female"
                }
            )
            Text("Female")
        }
    }
}


@Composable
fun MyScreen1(){
    val scrollState = rememberScrollState()
    val rowScrollState = rememberScrollState()

    Column(
        modifier = Modifier.padding(8.dp).fillMaxSize().verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth().horizontalScroll(rowScrollState)
        ) {
            repeat(5){ index ->
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        modifier = Modifier.size(100.dp,200.dp),
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "My Image"
                    )
                    Text("Image $index")
                }
            }
        }

        repeat(10){ index ->
            Column {
                Image(
                    modifier = Modifier.size(100.dp,200.dp),
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "My Image"
                )
                Text("Image $index" )
            }
        }
    }
}


@Composable
fun MyScreen(){

//    var counter = 0

    var counter by remember { mutableStateOf(0) }
    var checked by remember { mutableStateOf(false) }
    
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier.size(100.dp,200.dp),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentScale = ContentScale.FillBounds,
            contentDescription = "My Image"
        )

        AsyncImage(
            modifier = Modifier.size(200.dp,100.dp),
            model = "https://img.freepik.com/free-photo/beautiful-lake-mountains_395237-44.jpg",
            contentDescription = "MY Image"
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
            label = {Text("Email")},
            placeholder = {Text("example@gmail.com")},
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.LightGray,

            )
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(mask = '*'),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
            trailingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            label = {Text("Password")},
            placeholder = {Text("Aa1234@!")},
            isError = false,
            supportingText = {Text("Please enter valid password")}
        )

        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }
        )

        MyText("Counter = $counter")
        Button(
            onClick = {
                counter++
            }
        ) {
            Text("+")
        }
    }
}

@Composable
fun MyText(word:String){
    Text(text = word, fontSize = 20.sp, color = Color.Red)
}

@Composable
fun MyBtn(){
    Button(
        onClick = {}
    ) {
        Text("Click Here")
    }
}

 */
