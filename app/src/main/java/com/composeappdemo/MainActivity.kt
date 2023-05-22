package com.composeappdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composeappdemo.ui.HomeScreen
import com.composeappdemo.ui.Navigation
import com.composeappdemo.ui.ProfileScreen
import com.composeappdemo.ui.theme.ComposeAppDemoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.Math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation()
    }

    /**
     * Base
     */
    fun base() {
        //        setContent {
//            ComposeAppDemoTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
//                }
//            }
//        }

        setContent {
            //in this example compose places this texts on top of each other
//            Text("Hello")
//            Text("World")

            //in this example compose places this texts vertically
//            Column {
//                Text("Hello")
//                Text("World")
//            }

            //in this example compose places this texts horizontally
//            Row {
//                Text("Hello")
//                Text("World")
//            }

            // by default all compose elements get wrap_content for width and height
//            Column(modifier = Modifier.background(Color.Green), horizontalAlignment = Alignment.CenterHorizontally) {
//                Text("Hello")
//                Text("World")
//            }

            // for Arrangement we have more types SpaceAround ֊ բաժանում ա եղածները հավասար մասերի, քանի որ column ա , ուրեմն vertical իրար տակ
            // for Arrangement we have more types Bottom ֊ ներքևում իրար տակ
            // for Arrangement we have more types SpaceBetween ֊ մեկը ամենավերևում, մյուսը ամենաներքևում (2 text ի դեպքում)
            // for Arrangement we have more types SpaceBetween ֊ մեկը ամենավերևում, երկրորդը մեջտեղում , մյուսը ամենաներքևում (3 text ի դեպքում)
            // for Arrangement we have more types SpaceEvenly ֊  բոլոր item ների հեռավորությունը իրարից նույնն է
//            Column(modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Green), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
//                Text("Hello")
//                Text("World")
//                Text("World")
//            }

            //Alignment - Հավասարեցում
            //Arrangement - Պայմանավորվածություն

            //զբաղեցնում ա էկրանի կեսը, ամբողջի արժեքը։ 1
//            Column(modifier = Modifier
//                .fillMaxSize(0.5f)
//                .background(Color.Green), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
//                Text("Hello")
//                Text("World")
//                Text("World")
//            }

            //եթե չենք ուզում fraction size օգտագործենք, կարող ենք օգտագործել .dp width & height ի համար։
//            Column(modifier = Modifier
//                .width(200.dp)
//                .height(200.dp)
//                .background(Color.Green), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
//                Text("Hello")
//                Text("World")
//                Text("World")
//            }

            //ինչես նաև կարող ենք օգտագործել առանձին width or height ի համար fractonal size ը
//            Column(modifier = Modifier
//                .width(200.dp)
//                .fillMaxHeight(0.5f)
//                .background(Color.Green), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
//                Text("Hello")
//                Text("World")
//                Text("World")
//            }
        }
    }

    /**
     * Modifiers
     */
    fun modifiers() {
        //եթե մենք օգտագործում ենք width, աշխատում ա նենց, ոնց գիտենք, left top ով, իսկ եթե դնում ենք requiredWidth, դա դնում ա ընդհանուրի մեջտեղում, աջից ձախից հավասարացնելով
        //offset ը իրա 0 յական կետից նշված արժեքով x, y տեղաշարժում ա, բայց ի տարբերություն margin ի , կողքի էլեմենտների վրա չի ազդում
        //իսկ եթե ուզում ենք, որ երկու էլեմենտների միջև ինչ որ տարածություն առաջանա կարող ենք օգտագործել Spacer (margin ի նման բան ստանալու համար)
        //ուշադրություն դարձնենք, որ border(...) ից հետո նորից կանչվել ա padding(...), այսինքն մինչև border ի padding ը վերաբերում ա Columnt ին, իսկ Border ից հետո padding ը կոնտենտ ին
        //և տեսնում ենք նաև, որ Text ին նույնպես կարելի է առանձին modifier տալ, օրինակ border, padding etc..
        // Modifier.clickable{...} բացի նրանից, որ click ա անում, նաև տեսնում ենք click effect


        setContent {
            Column(modifier = Modifier
                .background(Color.Green)
                .fillMaxHeight(0.5f)
                .width(400.dp)
//                    .requiredWidth(470.dp)
                .padding(16.dp)
                .border(2.dp, Color.Magenta)
                .padding(16.dp)
                .border(2.dp, Color.Blue)
                .padding(16.dp)
                .border(2.dp, Color.Red)
                .padding(16.dp)
                .clickable {

                }) {
                Text("Hello", modifier = Modifier
                    .offset(10.dp, 10.dp)
                    .border(5.dp, Color.Gray)
                    .padding(16.dp)
                    .offset(5.dp, 5.dp)
                    .border(2.dp, Color.Yellow)
                    .padding(5.dp)
                    .clickable {

                    })
                Spacer(modifier = Modifier.height(16.dp))
                Text("World")
            }
        }
    }

    /**
     * Image Card
     */
    fun imageCard() {
        setContent {
            val painter = painterResource(id = R.drawable.image_funny)
            val description = "Funny Image"
            val title = "This is very funny image"
            Box(modifier = Modifier.fillMaxWidth(0.5f)) {
                ImageCard(painter = painter, contentDescription = description, title = title)
            }
        }
    }

    /**
     * Styling Text
     */
    fun stylingText() {
        //Font երի list կարող ենք սարքել, իրենց տեզակներով Light, Bold, etc
        val fontFamily = FontFamily(Font(R.font.kartooni, FontWeight.Thin))

//        setContent {
//            Box(modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFF101010))) {
//                Text(text = "SoftCo Android", color = Color.White, fontSize = 30.sp, fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic, textAlign = TextAlign.Center, textDecoration = TextDecoration.LineThrough)
//            }
//        }
        //compose ով աննորմալ հեշտ է տեքստի հատվածներին տարբեր style եր տալ
        setContent {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101010))) {
                Text(text = buildAnnotatedString {
//                    append("SoftCo")
                    withStyle(style = SpanStyle(color = Color.Green, fontSize = 18.sp)) {
                        append("SoftCo")
                    }
                    append("Androi")

                    withStyle(style = SpanStyle(color = Color.Green, fontSize = 16.sp)) {
                        append("d")
                    }
                }, color = Color.White, fontSize = 30.sp, fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic, textAlign = TextAlign.Center, textDecoration = TextDecoration.LineThrough)
            }
        }
    }

    /**
     * State
     */
    fun state() {
        //state describes how our given UI looks at the moment

        //ColorBox fun ով, մենք ի սկզբանե տվել ենք mutableStateOf Color.Yellow գույնով, դրել ենք click և ամեն անգամ, երբ click ենք անում, color ը mutableStateOf ի փոխվում
        // ա և դա աշխատում ա liveData ի նման. Ստացվում ա, որ նորից պետք ա ColorBox fun ը աշխատի, որ գույնը փոխի, նորից աշխատելուց էլ, վերևում հայտարարված
        // color mutableStateOf ը եթե remember չհայտարարենք, ամեն անգամ կվերցնի Color.Yellow ն որպես default, իսկ remember ով, ամեն անգամ հիշում ա, իրա նախորդ state ը
        setContent {
            Column(Modifier.fillMaxSize()) {
                val color = remember {
                    mutableStateOf(Color.Yellow)
                }

                ColorBox(Modifier
                    .weight(1f)
                    .fillMaxSize()) {
                    color.value = it
                }

                Box(Modifier
                    .background(color.value)
                    .weight(1f)
                    .fillMaxSize())

            }
        }
    }

    /**
     * Textfields, Buttons & Showing Snackbars
     */
    fun textFieldsButtonsShowingSnackbars() {
        //NOT WORKING!!!
//        setContent {
//            val scaffoldState = rememberScaffoldState()

//            Scaffold(modifier = Modifier.fillMaxSize(), sca) {
//                Column() {
//
//                }
//            }
//        }
    }

    /**
     * Lists
     */
    fun lists() {
        setContent {
            //մենք ուղղակի ավելացրել ենք տեքստեր, բայց այս գրածով scroll չի լինի
//            Column {
//                for (i in 0..100) {
//                    Text(text = "Item $i", fontSize = 16.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 16.dp))
//                }
//            }

            //ավելացնում ենք verticalScroll իրա remember ով, որպես ապահովվենք scroll ը
            //բայց հիմա ես scroll եմ անում իմ galaxy s10+ ով շատ պլավնի չի, պահ տակ կտրտում ա.
//            val scrollState = rememberScrollState()
//
//            Column(modifier = Modifier.verticalScroll(scrollState)) {
//                for (i in 0..1000) {
//                    Text(text = "Item $i", fontSize = 16.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 16.dp))
//                }
//            }

            LazyColumn {
//                items(5000) {
//                    Text(text = "Item $it", fontSize = 16.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 16.dp))
//                }

                //կարող ենք օգտագործել նաև
                itemsIndexed(listOf("this", "is", "Jetpack", "Compose")) { index, item ->
                    Text(text = "$item $index", fontSize = 16.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp))
                }
            }
        }
    }

    /**
     * ConstraintLayout
     * implementation 'androidx.constraintlayout:constraintlayout-compose:1.0.1'
     */
    fun constraintLayout() {
        setContent {
            val constraintSet = ConstraintSet {
                val greenBox = createRefFor("greenBox")
                val redBox = createRefFor("redBox")

                //dp ով
//                val guideline = createGuidelineFromTop(16.dp)

                //fraction ով
                val guideline = createGuidelineFromTop(0.3f)

                constrain(greenBox) {
                    top.linkTo(guideline)
//                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
//                    width = Dimension.percent(0.5f)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }

                constrain(redBox) {
                    top.linkTo(parent.top)
                    start.linkTo(greenBox.end)
                    end.linkTo(parent.end)
//                    width = Dimension.percent(0.5f)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
//                    width = Dimension.fillToConstraints  // equivalent to 0dp !!!
//                    height = Dimension.value(100.dp)
                }

                //Chain սարքելու համար
                createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)
            }

            ConstraintLayout(constraintSet, modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenBox")) {

                }

                Box(modifier = Modifier
                    .background(Color.Red)
                    .layoutId("redBox")) {}
            }
        }
    }

    /**
     * Full Guide to Jetpack Compose
     */
    private var i = 0
    fun fullGuideToJetpackCompose() {
        /**
         * հիմա եթե մենք նայենք ես կոդին, կտեսնենք, որ մենք ավելացնում ենք i ի արժեքը, և դրա պատճառով ունենում ենք побойный эффект
         * որովհետև դա compose fun ի scope ից դուրս ա. Դա compose state չի ու այստեղ անելու բան չունի.
         * Ստեղ շատ տարրական օրինակ ա, բայց կարող ենք պատկերացնել, որ ստեղ network call ա, այսինքն i++ ի տեղը կանչում ենք network function և քանի
         * որ մենք նայենք { this: RowScope i++ Text(text = text) } ին կտեսնենք, որ ամեն անգամ recompose ա արվում, և դրա պատճառով կստացվեր, որ
         * ամեն անգամ նորից network call ա արվելու.
         * Այսինքն հասկանում ենք, որ ամեն անգամ երբ աշխատում ա content: @Composable() (RowScope.() -> Unit), դա նշանակումա  որ նորից նկարվում ա.
         * Մենք պետք ա չանենք nonCompose բաներ -> compose կոդում
         * ինչպե՞ս խուսափենք։ ՊԵՏՔ Է ՕԳՏԱԳՈՐԾԵՆՔ effect handlers
         *
         * 1 - LaunchedEffect
         * 2 - RememberCoroutineScope
         * 3 - RememberUpdatedState
         * 4 - DisposableEffect
         * 5 - SideEffect
         * 6 - ProduceState
         * 7 - DerivedStateOf
         * 8 - SnapshotFlow
         *
         */
        setContent {
//            var text by remember {
//                mutableStateOf("")
//            }
//
//            ComposeAppDemoTheme {
//                Button(onClick = {
//                    text += "#"
//                }) {
//                    i++
//                    Text(text = text)
//                }
//            }
            // 1 - LaunchedEffect
            var text by remember {
                mutableStateOf("")
            }

            LaunchedEffect(key1 = text) {
                //առաջին հերթին տեսնում ենք, որ հիմա մենք CoroutineScope ի մեջ ենք, օրինակ կարող ենք delay() օգտագործել այստեղ
                delay(200L)
                //երկրորդ LaunchedEffect ը Composoble ա.
                //key1 ին մենք կարող ենք տալ օրինակ state ը compose ի , օրինակ text ը որը իր հերթին նշանակում է, որ երբ որ մենք նոր տեքս set անենք
                // այս coroutinScope ը cancel>relaunch կլինի արդեն նոր տեքստով

                println("Text is $text")

                //էս օրինակը շատ պարզ ա, նայենք ուրիշ օրինակ, ՏԵՍ. ներքևում LaunchedEffectViewModel


            }
        }
    }

    /**
     * SimpleAnimations
     */
    fun simpleAnimations() {
        setContent {
            var sizeState by remember {
                mutableStateOf(200.dp)
            }
//            val size by animateDpAsState(targetValue = sizeState, animationSpec = tween(durationMillis = 3000, delayMillis = 300, easing = LinearOutSlowInEasing))
//            val size by animateDpAsState(targetValue = sizeState, spring(Spring.DampingRatioMediumBouncy ) )
//            val size by animateDpAsState(targetValue = sizeState, keyframes {
//                durationMillis = 5000
//                sizeState at 0 with LinearEasing
//                sizeState * 1.5f at 1000 with FastOutLinearInEasing
//                sizeState * 2f at 5000
//            })
            val size by animateDpAsState(targetValue = sizeState, tween(durationMillis = 1000))
            val infiniteTransition = rememberInfiniteTransition()
            val color by infiniteTransition.animateColor(initialValue = Color.Red, targetValue = Color.Green, animationSpec = InfiniteRepeatableSpec(tween(durationMillis = 2000), repeatMode = RepeatMode.Reverse))

            Box(modifier = Modifier
                .size(size)
                .background(color), contentAlignment = Alignment.Center) {
                Button(onClick = { sizeState += 50.dp }) {
                    Text("Increase Size")
                }
            }
        }
    }

    /**
     * Animated Circular Progress Bar
     */
    fun onCreateForCircularProgressBar() {
        setContent {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressBar(percentage = 0.75f, number = 100)
            }
        }
    }

    /**
     * Draggable Music Knob
     */
    fun draggableMusicKnob() {
        setContent {
            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101010))) {
                Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                    .padding(30.dp)) {
                    var volume by remember {
                        mutableStateOf(0f)
                    }
                    val barCount = 20
                    MusicKnob(onValueChange = {
                        volume = it
                    }, modifier = Modifier.size(100.dp))
                    Spacer(Modifier.width(20.dp))
                    VolumeBar(Modifier
                        .fillMaxWidth()
                        .height(30.dp))
                }
            }
        }
    }

    /**
     * MeditationView
     */
    fun meditationView() {
        setContent {
            HomeScreen()
        }
    }

    /**
     * Timer
     */
    fun timer() {
        setContent {
            Surface(color = Color(0xFF101010), modifier = Modifier.fillMaxSize()) {
                Box(contentAlignment = Alignment.Center) {
                    Timer(totalTime = 100L * 1000L, handleColor = Color.Green, inactiveBarColor = Color.DarkGray, activeBarColor = Color(0xFF37b900), modifier = Modifier.size(200.dp))
                }
            }
        }
    }

    /**
     * 3D Animated Drop Down
     */
    fun _3dAnimatedDropDown() {
        setContent {
            Surface(color = Color(0xFF101010), modifier = Modifier.fillMaxSize()) {
                DropDown(text = "Hello Bet Co", modifier = Modifier.size(15.dp)) {
                    Text(text = "This is very smart text", modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(color = Color.Green))
                }
            }
        }
    }

    /**
     * Instagram Profile UI
     */
    fun instagramProfileUI() {
        setContent {
            ProfileScreen()
        }
    }

    fun navigation() {
        setContent {
            Navigation()
        }
    }
}

@Composable
fun DropDown(text: String, modifier: Modifier, initiallyOpened: Boolean = false, content: @Composable () -> Unit) {
    var isOpen by remember {
        mutableStateOf(initiallyOpened)
    }

    val alpha = animateFloatAsState(targetValue = if (isOpen) 1f else 0f, animationSpec = tween(durationMillis = 300))
    val rotateX = animateFloatAsState(targetValue = if (isOpen) 0f else -90f, animationSpec = tween(durationMillis = 300))
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Text", color = Color.White, fontSize = 16.sp)
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Open or Close Drop Down", tint = Color.White, modifier = Modifier
                .clickable {
                    isOpen = !isOpen
                }
                .scale(1f, if (isOpen) -1f else 1f))
        }
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                transformOrigin = TransformOrigin(0.5f, 0f)
                rotationX = rotateX.value
            }
            .alpha(alpha.value)) {
            content()
        }
    }
}

@Composable
fun Timer(totalTime: Long, handleColor: Color, inactiveBarColor: Color, activeBarColor: Color, modifier: Modifier, initialValue: Float = 1f, strokeWidth: Dp = 5.dp) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    var value by remember {
        mutableStateOf(initialValue)
    }

    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime >= 0L && isTimerRunning) {
            delay(100L)
            currentTime -= 100
            value = currentTime / totalTime.toFloat()
        }
    }

    Box(contentAlignment = Alignment.Center, modifier = modifier.onSizeChanged {
        size = it
    }) {
        Canvas(modifier = modifier) {
            drawArc(color = inactiveBarColor, startAngle = -215f, sweepAngle = 250f, useCenter = false, size = Size(size.width.toFloat(), size.height.toFloat()), style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round))
            drawArc(color = activeBarColor, startAngle = -215f, sweepAngle = 250f * value, useCenter = false, size = Size(size.width.toFloat(), size.height.toFloat()), style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round))
            val centerOffset = Offset(size.width / 2f, size.height / 2f)
            val beta = (250 * value + 145f) * (PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            drawPoints(listOf(Offset(center.x + a, center.y + b)), pointMode = PointMode.Points, color = handleColor, strokeWidth = (strokeWidth * 3).toPx(), cap = StrokeCap.Round)
        }
        Text(text = (currentTime / 1000L).toString(), fontSize = 44.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Button(onClick = {
            if (currentTime <= 0L) {
                currentTime = totalTime
                isTimerRunning = true
            } else {
                isTimerRunning = !isTimerRunning
            }
        }, modifier = Modifier.align(Alignment.BottomCenter), colors = ButtonDefaults.buttonColors(containerColor = if (!isTimerRunning || currentTime <= 0L) Color.Green else Color.Red)) {
            Text(text = if (isTimerRunning && currentTime >= 0L) "Stop" else if (!isTimerRunning && currentTime >= 0L) "Start" else "Restart")
        }
    }
}

@Composable
fun VolumeBar(modifier: Modifier = Modifier, activeBars: Int = 0, barCount: Int = 10) {
    BoxWithConstraints(contentAlignment = Alignment.Center, modifier = modifier) {
        val barWidth = remember {
            constraints.maxWidth / (2f * barCount)
        }

        Canvas(modifier = modifier) {
            for (i in 0 until barCount) {
                drawRoundRect(color = if (i in 0..activeBars) Color.Green else Color.Gray, topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f), size = Size(barWidth, constraints.maxWidth.toFloat()), cornerRadius = CornerRadius(0f))
            }
        }

    }
}

@Composable
fun MusicKnob(modifier: Modifier = Modifier, limitedAngle: Float = 25f, onValueChange: (Float) -> Unit) {
    var rotation by remember {
        mutableStateOf(limitedAngle)
    }
    var touchX by remember {
        mutableStateOf(0f)
    }
    var touchY by remember {
        mutableStateOf(0f)
    }
    var centerX by remember {
        mutableStateOf(0f)
    }
    var centerY by remember {
        mutableStateOf(0f)
    }

    Image(painter = painterResource(id = R.drawable.music_knob), contentDescription = "Music Knob", modifier = modifier
        .fillMaxSize()
        //երբ վերջնական պարզա թե որտեղ ա նկարվել image ը, դրանից հետո անմիջապես աշխատում ա այս fun ը
        .onGloballyPositioned {
            val windowBounds = it.boundsInWindow() // boundsInWindow -> root ի մեջ, boundsInContainer -> parent ի մեջ.
            centerX = windowBounds.size.width / 2f
            centerY = windowBounds.size.height / 2f
        }
//        .pointerInteropFilter { event ->
//            touchX = event.x
//            touchY = event.y
//            val angle = -atan2(centerX - touchX, centerY - touchY) * (180 / PI).toFloat()
//
//            when (event.action) {
//                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
//                    if (angle !in -limitedAngle..limitedAngle) {
//                        val fixedAngle = if (angle in -180f..-limitedAngle) 360f + angle else angle
//                        rotation = fixedAngle
//                        val percent = (fixedAngle - limitedAngle) / (360f - 2 * limitedAngle)
//                        onValueChange(percent)
//                    }
//                }
//            }
//            true
//        }
        .rotate(rotation))
}

@Composable
fun CircularProgressBar(percentage: Float, number: Int, fontSize: TextUnit = 28.sp, radius: Dp = 50.dp, color: Color = Color.Green, strokeWidth: Dp = 8.dp, animationDuration: Int = 1000, animDelay: Int = 0) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercentage = animateFloatAsState(targetValue = if (animationPlayed) percentage else 0f, animationSpec = tween(durationMillis = animationDuration, delayMillis = animDelay))
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(radius * 2)) {
        Canvas(modifier = Modifier.size(radius * 2)) {
            drawArc(color = color, -90f, 330 * currentPercentage.value, useCenter = false, style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round))
        }
        Text(text = (currentPercentage.value * number).toInt().toString(), color = Color.Black, fontSize = fontSize, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ColorBox(modifier: Modifier = Modifier, updateColor: (Color) -> Unit) {

    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            updateColor(Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f))
        }) {

    }
}

//contentScale = ContentScale.Crop == CenterCrop
@Composable
fun ImageCard(painter: Painter, contentDescription: String, title: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(painter = painter, contentDescription = contentDescription, contentScale = ContentScale.Crop)
            Box(modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black), startY = 300f)))
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp), contentAlignment = Alignment.BottomStart) {
                Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeAppDemoTheme {
        Greeting("Android")
    }
}

class LaunchedEffectViewModel : ViewModel() {
    /**
     * եթե flow ին collect անենք @Composoble ի մեջ, սա կլինի побойный эффект. որովհետև ամեն անգամ recompose լինելուց նորից collect ա լինելու.
     * ի՞նչ կարող ենք անել։ Օգտագործել LaunchEffect
     */
    private val _sharedFlow = MutableSharedFlow<ScreenEvent>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            _sharedFlow.emit(ScreenEvent.ShowSnackbar("Hello World"))
        }
    }

    sealed class ScreenEvent {
        data class ShowSnackbar(val message: String) : ScreenEvent()
        data class Navigate(val route: String) : ScreenEvent()

    }
}

@Composable
fun LaunchEffectFlowDemo(viewModel: LaunchedEffectViewModel) {
    /**
     * true փոխանցելուց մենք կարող ենք համոզված լինել, որ աշխատելու է մի անգամ. Այսինքն collect կլինի ընդամենը մեկ անգամ.
     */
    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collect {
            when (it) {
                is LaunchedEffectViewModel.ScreenEvent.ShowSnackbar -> {

                }

                is LaunchedEffectViewModel.ScreenEvent.Navigate -> {

                }
            }
        }
    }
}

@Composable
fun LaunchEffectAnimation(counter: Int) {
    val animatable = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = counter) {
        animatable.animateTo(counter.toFloat())
    }
}

@Composable
fun RememberCoroutineScopeDemo() {
    /**
     * Սա օգտագործում ենք մենակ callBack երի հետ.
     */
    val scope = rememberCoroutineScope()
    Button(onClick = {
        scope.launch {
            delay(1000L)
            println("Hello World!")
        }
    }) {

    }
}

@Composable
fun RememberUpdatedStateDemo(onTimeOut: () -> Unit) {
//    LaunchedEffect(key1 = true) {
//        delay(3000L)
//        onTimeOut()
//    }

    /**
     * որն է՞ այս կոդի խնդիրը
     * Խնդիրը այն է, որ եթե մենք կանչենք այս fun ը տարբեր onTimeOut ով, LaunchedEffect(true) ի պատճառով կանչվելու է միայն ու միայն առաջին անգամ.
     * դրա համար էլ մենք ունենք rememberUpdatedState
     */
    val updatedOnTimeout by rememberUpdatedState(newValue = onTimeOut)
    //պետք է փոփոխենք վերևի կոդը
    LaunchedEffect(key1 = true) {
        delay(3000L)
        updatedOnTimeout()
    }
}

@Composable
fun DisposableEffectDemo() {
    /**
     * որն է՞ այս կոդի խնդիրը
     * այստեղ կա երկու խնդիր
     * 1 ֊ observer ը հայտարարել և լսում ենք Composable ի մեջ, ամեն անգամ recompose լինելուց նորից աշխատելու է.
     *
     */
    val lifeCycleOwner = LocalLifecycleOwner.current
    val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_PAUSE) {
            println("DisposableEffectDemo: on Pause called")
        }
    }

    /**
     * օգտագործում ենք նման դեպքում DisposableEffect. Այն ամեն անգամ relaunch կլինի երբ lifeCycleOwner ը փոխվի, և արդեն իր մեջ կարող ենք տանել observer ը
     */
    DisposableEffect(key1 = lifeCycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                println("DisposableEffectDemo: on Pause called")
            }
        }

        //պետք է implement ենք onDespose fun ը.
        lifeCycleOwner.lifecycle.addObserver(observer)
        /**
         * այս քայլից հետո մենք համոզված կարող ենք լինել, որ վերցրեցինք event ները. բայց սա էլ համոզված չենք, որ կմաքրվի
         * դրա համար, պետք է կանչենք lifeCycleOwner.lifecycle.removeObserver(observer). Դա պետք է կանչվի երբ որ composable ը լքում է composition ը.
         * և onDispose ը կկանչվի հենց այդ, մեր ուզած պահին, որտեղ էլ որ կարող ենք արդեն կանչել lifeCycleOwner.lifecycle.addObserver(observer)
         * և արդյունքում ոչ մի leak չենք ունենա.
         *
         * Այսինքն արդյունքում, եթե մենք ունենք composable ի կարիք, որը պետք է լինելու ինչ որ cleanup ի ՝ օգտագործում ենք DisposableEffect
         */

        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun SideEffectDemo(nonComposeCounter: Int) {
    /**
     * Called after every successful recomposition
     */
    SideEffect {
        print("Called after every successful recomposition")
    }
}

@Composable
fun ProduceStateDemo(countUpTo: Int): State<Int> {
    /**
     * returns this produce state
     */

    return produceState(initialValue = 0) {
        while (value < countUpTo) {
            delay(1000L)
            value++
        }
    }

//    return flow {
//        var value = 0
//        while (value < countUpTo) {
//            delay(1000L)
//            value++
//            emit(value)
//        }
//    }.collectAsState(initial = 0)
}

@Composable
fun DerivedStateOfDemo() {
//    var counter by remember {
//        mutableStateOf(0)
//    }
//
//    var counterText = "This counter is $counter"
//    Button(onClick = { counter++ }) {
//        Text(text = counterText)
//    }

    /**
     * վերևի օրինակում ամեն անգամ երբ որ փոխենք counter ի արժեքը , փոխվելու է counterText ը, այսինքն ստեղծվելու է նոր string և նորից նկարվի
     * devidedStateOf ը cache է անում այդ string ը , և երբ փոխվում է, ասում է, որ cache ս փոխվել է․
     */

//    var counter by remember {
//        mutableStateOf(0)
//    }
//
//    val counterText by derivedStateOf {
//        "This counter is $counter"
//    }
//
//    Button(onClick = { counter++ }) {
//        Text(text = counterText)
//    }


}

@Composable
fun SnapshotFlowDemo() {
//    val scaffoldState = rememberScaffoldState()
}