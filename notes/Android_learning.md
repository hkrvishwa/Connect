# Android notes

## What is Android?

Android is a mobile operating system developed by Google, primarily used on smartphones and tablets.

Android is based on the Linux kernel and is used by many device manufacturers, including Samsung, OnePlus, Xiaomi, Motorola, and Google

- Android OS architecture

- Applications – Includes apps such as Phone, Camera, Messages, and third-party apps.

- Application Framework – Provides services like Activity Manager, Window Manager, and Notification Manager.

- Android Runtime (ART) – Executes application code and manages memory.

- Native Libraries – Provides libraries for graphics, media, databases, and security.

- Hardware Abstraction Layer (HAL) – Connects Android software with device hardware.

- Linux Kernel – Manages memory, processes, networking, security, and hardware drivers.

- Hardware – Includes CPU, RAM, camera, sensors, display, GPS, and other physical components.

ART is the overall runtime environment, while JIT and AOT are compilation techniques used with ART

JIT means compiling code while the application is running

Android App

↓

DEX Bytecode

↓

ART

↙ ↘

AOT JIT

↓ ↓

Compiled code Runtime

optimization

↘ ↙

Machine Code

↓

CPU

### APK — Android Package AAB — Android App Bundle

### settings.gradle = project structure , include modules

### Project build.gradle = project-wide build configuration, repo,plugins

### App build.gradle = app-specific build configuration

### gradle.properties = Gradle properties/options , jvm , memory,gradle cache

### Gradle Wrapper = fixed/controlled Gradle version

## ACTIVTY

The Activity Lifecycle manages the Activity from creation to destruction.

The main lifecycle methods are onCreate() → onStart() → onResume() → onPause() → onStop() → onDestroy()

A and B are two Activity  
A.onPause() → B.onCreate() → B.onStart() → B.onResume() → A.onStop()

## Android Back Stack

Back Stack – A stack that keeps track of the Activities/screens the user has visited, so pressing Back can return to the previous screen.

### Standard

– Each new Activity is added to the top of the back stack.

### SingleTop

– If the Activity is already at the top, a new instance is not created.
-\>If Activity is NOT at the top: → A new instance is created → onCreate() is called

-\>If Activity IS already at the top: → No new instance is created → the existing Activity receives the new Intent through onNewIntent()

### SingleTask

– If an existing instance is found, Activities above it are removed and the existing Activity receives the onNewIntent().

### SingleInstance

– The Activity is placed in a separate task and runs alone in that task.

### SingleInstancePerTask

– Similar to singleInstance, but the same Activity can have instances in different tasks.

onSaveInstanceState() – Called before the Activity is destroyed/recreated, such as during screen rotation, to save temporary UI data in a Bundle.

## SaveInstanceState

onRestoreInstanceState() – Called after onStart() when the Activity is recreated, to restore the data that was previously saved.

onSaveInstanceState() → onDestroy() → onCreate() → onStart() → onRestoreInstanceState() → onResume()

## Fragment
Fragment A: onPause() → onStop() → onDestroyView()

Fragment B: onCreate() → onCreateView() → onStart() → onResume()

When Back is pressed, Fragment B: onPause() → onStop() → onDestroyView()

Fragment A: onCreateView() → onStart() → onResume()

All fragment lifecycle  
onAttach() → onCreate() → onCreateView() → onViewCreated() → onStart() → onResume()

When the Fragment is no longer active:

onPause() → onStop() → onDestroyView() → onDestroy() → onDetach()

## Services

A Service in Android is a component that performs work in the background without providing a user interface.

The basic Service lifecycle is:onCreate() → onStartCommand() → onDestroy()

Started Service – A service started using startService() that continues running independently until it is stopped.receive intent data onStartCommand

### Started Service

: onCreate() → onStartCommand() → onDestroy()

START_STICKY – If Android kills the Service because of resource pressure, Android tries to recreate the Service later; however, the original Intent may not be redelivered, so intent can be null.

START_NOT_STICKY – If Android kills the Service, Android does not recreate it automatically unless there is a new request to start it.

### Bound Service

: onCreate() → onBind() → onUnbind() → onDestroy()

A Bound Service is a service that allows an Activity, Fragment, or another component to connect to the service and interact with it. The client can call methods on the service and receive results from it

IBinder = interface → Binder = implementation → MyBinder = your custom Binder → ServiceConnection = Activity's connection listener.

## <u>Foreground Service</u>

<span class="mark">A Foreground Service is a Service used for work that the user can clearly notice while it is running. It must show an ongoing notification to the user.</span>

### <span class="mark">Lifecycle</span>

<span class="mark">onCreate() → onStartCommand() → startForeground() → Service runs → stopForeground() → onDestroy(</span>)

## <u>IntentService</u>

IntentService was a convenient Android component for handling background work one request at a time on a worker thread, so you didn't have to manually create a thread.

Intent : IntentService → onHandleIntent() → Background thread → Service stops automatically

## BroadcastReceiver

is an Android component that receives and responds to broadcast messages from the system or other apps

## ContentProvider

is an Android component used to share and manage data between different applications.

It provides a standard interface for accessing data such as contacts, media, or app-specific databases.

Other apps can access the data using a ContentResolver.

The main operations are query(), insert(), update(), and delete().

It also provides controlled access and permissions so apps can securely share their data.

## Density-independent pixel

dp (density-independent pixel) – A unit used for UI dimensions such as width, height, and margins. It keeps the physical size of UI elements relatively consistent across different screen densities.

sp (scale-independent pixel) – Similar to dp, but mainly used for text size. It also respects the user's font-size/accessibility setting.

### mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi

These are Android's screen-density buckets.

mdpi – <u>Baseline density</u>, approximately 160 dpi. (Dots Per Inch.)

hdpi – High density, approximately 240 dpi.

xhdpi – Extra-high density, approximately 320 dpi.

xxhdpi – Extra-extra-high density, approximately 480 dpi.

xxxhdpi – Extra-extra-extra-high density, approximately 640 dpi.

## px = dp × (density / 160)
suppose width is 100 dpmdpi → 100 dp → 100 px

hdpi → 100 dp → 150 px

xhdpi → 100 dp → 200 px

xxhdpi → 100 dp → 300 px

xxxhdpi → 100 dp → 400 px

## Explicit Intent

– Used when you know exactly which Activity, Service, or BroadcastReceiver you want to start, usually within your own app. Example: Intent(this, SecondActivity::class.java).

## Implicit Intent

– Used when you specify what action you want to perform rather than a specific component, and Android finds an appropriate app/component to handle it. Example: opening a URL, sharing text, or sending an email.

## Parcelable

– Android's optimized way to serialize an object so it can be passed between Android components, such as through an Intent or Bundle.

## Serializable

– A standard Java mechanism that converts an object into a stream of data so it can be transferred or stored.

*Deep Link* – A link that takes the user directly to a specific screen or content inside an app, instead of just opening the app's home screen.

## SharedPreferences

– A simple Android storage mechanism used to store small amounts of key-value data, such as user preferences, settings, or login flags.

apply() – Updates the preferences asynchronously and returns immediately; it does not return a success/failure result.

commit() – Updates the preferences synchronously, meaning it waits for the write to finish, and returns a Boolean indicating whether the write succeeded.

## Basic File Storage

### Internal Storage

– No storage permission is required; files are private to your app, and normally only your app can access them. Files are removed when the app is uninstalled.
  
### App-specific External Storage

– No storage permission is required for your app-specific directory; your app can access the files, and the user can generally see/manage them through appropriate system file tools. These files are removed when the app is uninstalled.

### Shared External Storage

– Used for files that should be available to other apps or the user, such as photos, videos, and documents. Access depends on the

MediaStore/storage APIs and Android version; broad storage permissions are generally not needed when using MediaStore for appropriate shared media.

### Cache Storage

– No storage permission is required; only your app can normally access its cache files, and Android may delete them whenever storage space is needed.

Internal: data/data/com.example.app/files/

App-specific External: Android/data/com.example.app/files/

## SQLite in Android

SQLite is a lightweight relational database built into Android, used to store structured data locally in tables, rows, and columns.

-\>onCreate() – Called when the database is created for the first time, where you create your tables.

onCreate() always contains the latest database schema, so a new user gets the current table structure directly, while onUpgrade() contains the migration logic needed to update existing users from older database versions to the latest version.

```kotlin
override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

if (oldVersion \< 2) {

db.execSQL("ALTER TABLE User ADD COLUMN age INTEGER")

}
```

if (oldVersion \< 3) {

db.execSQL("ALTER TABLE User ADD COLUMN email TEXT")

}

}  
when we update table, we need to change schema or table in onCreate even in new version

## Room Database

Room mainly has 3 important parts: Entity, DAO, and Database.

DAO (Data Access Object) – Contains the methods used to access the database.

Database – Connects the Entities and DAOs together.

```kotlin
@Database(entities = \[User::class\], version = 1)

abstract class AppDatabase : RoomDatabase() {

abstract fun userDao(): UserDao

}

val db = Room.databaseBuilder(

context,

AppDatabase::class.java,

"app_database"

).build()
```

## RecyclerView

RecyclerView is an Android UI component used to display a scrollable list or grid of items efficiently, such as a list of users, products, or messages.

Adapter – The Adapter connects your data to the RecyclerView and tells it how to create and bind each list item.

ViewHolder – The ViewHolder holds the views of a single list item and reuses them when items scroll off-screen and new items appear.

DiffUtil is an Android utility used with RecyclerView to compare an old list with a new list and identify what actually changed.

areItemsTheSame() -\> Checks whether these are the same objects/items, usually by comparing a unique ID.

areContentsTheSame() -\> Checks whether the actual data/content is the same.(Easy memory:)

## PendingIntent

A PendingIntent is a token that allows another app or the Android system to execute an Intent on your app's behalf later.

```kotlin
val intent = Intent(this, MainActivity::class.java)

val pendingIntent = PendingIntent.getActivity(

this,

0,

intent,

PendingIntent.FLAG_IMMUTABLE

)

val notification = NotificationCompat.Builder(this, "channel")

.setContentTitle("New Message")

.setContentText("Tap to open")

.setSmallIcon(R.drawable.ic_notification)

.setContentIntent(pendingIntent)

.setAutoCancel(true)

.build()

pending intent used for opening after alarm class,services..etc
```

## Location

ACCESS_COARSE_LOCATION – Allows your app to access approximate location.

ACCESS_FINE_LOCATION – Allows your app to request precise location, such as GPS-level accuracy.

ACCESS_BACKGROUND_LOCATION - This is used when your app needs location access while it is not actively being used

## Bluetooth Classic in Android

Bluetooth Classic is used for continuous wireless communication between Android and devices such as headphones, speakers, keyboards, cars, and serial devices.

BluetoothAdapter → Represents the phone's Bluetooth hardware and lets you check whether Bluetooth is enabled.

BluetoothDevice → Represents a remote Bluetooth Classic device that you want to communicate with.

Discovery → Android scans for nearby Bluetooth Classic devices and reports them through a BroadcastReceiver.

Pairing → The phone and Bluetooth device establish a trusted relationship, usually requiring user confirmation.

BluetoothSocket → After pairing, the app creates a socket to establish a communication connection.

InputStream / OutputStream → Used to receive and send data through the Bluetooth socket.

Typical flow: BluetoothAdapter → Discover → BluetoothDevice → Pair → BluetoothSocket → InputStream/OutputStream → Disconnect

## BLE

BluetoothGatt → Android API used by the app to connect and communicate with a BLE device.

GATT (Generic Attribute Profile) → Defines how BLE devices organize and exchange data.

GATT Service → A group of related functionality, identified by a UUID.

Characteristic → A specific piece of data inside a service that your app can read, write, or receive notifications from.

Descriptor → Provides additional information/configuration for a characteristic, such as enabling notifications.

A BLE device exposes Services, and each Service contains Characteristics.

val service = bluetoothGatt.getService(SERVICE_UUID)

bluetoothGatt.setCharacteristicNotification(characteristic,true)

descriptor = char.getDesr… then descripti.value = BluGatt.ENA_NOTI..

val bluetoothGatt = bluetoothDevice.connectGatt(

this,

false,

gattCallback

)

bluetoothDevice.connectGatt(

this,

autoConnect,

gattCallback

)

autoConnect = false → Try to connect to the device now. This is the common choice when the user selects a device and you want to connect immediately.

autoConnect = true → Use Android's auto-connect behavior. Instead of treating it as an immediate connection attempt, Android can wait for the device to become available and establish the connection when possible.

autoConnect controls the connection strategy; it is not a “reconnect forever” switch.

```kotlin
private val gattCallback = object : BluetoothGattCallback() {

override fun onConnectionStateChange(

gatt: BluetoothGatt,

status: Int,

newState: Int

) {

if (newState == BluetoothProfile.STATE_DISCONNECTED) {

bluetoothGatt?.close()

bluetoothGatt = null

bluetoothDevice.connectGatt(

this,

autoConnect = false,

gattCallback

)

}
```

}

}

## Retrofit

```kotlin
val retrofit = Retrofit.Builder()

.baseUrl("https://example.com/api/")

.addConverterFactory(GsonConverterFactory.create())

.build()

val api = retrofit.create(ApiService::class.java)

ApiService define your API endpoints.
```

## KTOR

```kotlin
val client = HttpClient(Android) {

install(ContentNegotiation) {

json()

}
```

defaultRequest {

url("https://api.example.com/")

header(

HttpHeaders.Accept,

"application/json"

)

contentType(ContentType.Application.Json)

}

}

```kotlin
client.request(url) {

method = ... // GET/POST/PUT/DELETE

url {

path(...) // /users/123

parameters.append() // ?page=2

}
```

headers {

append(...) // Authorization

}

setBody(...) // JSON/body

}

## ViewModel
1.Activity implements ViewModelStoreOwner, which provides a ViewModelStore.

2.ViewModelStore internally contains a HashMap\<String, ViewModel\>.

3.The String key identifies a particular ViewModel within that store.

4.ViewModelProvider first checks the store using that key.

5.If the ViewModel exists, it returns the **same instance**.

6.If it doesn't exist, ViewModelProvider asks a Factory to create it.

7.If the ViewModel has constructor parameters, the Factory provides those parameters.

8.During configuration changes, the Activity is destroyed but its ViewModelStore is retained, so the same ViewModel is returned to the new Activity.

9.When the owner is permanently destroyed, the store is cleared and ViewModel.onCleared() is called.

## Live data

LiveData is an observable data holder that lets Activity or Fragment receive data changes from a ViewModel.  
Activity/Fragment observes LiveData using its LifecycleOwner, so updates happen only when the lifecycle is active.  
setValue() changes the value on the Main thread and immediately notifies active observers.  
postValue() can be called from a background thread and schedules the value update on the Main thread.  
When the owner reaches DESTROYED, LiveData automatically removes its lifecycle-bound observer.

viewModel.name.observe(this) { value -\>

textView.text = value

}

## Flow

Flow is a Kotlin API for asynchronously producing a stream of values over time.  
Flow has two main concepts: Flow produces values, and FlowCollector receives them.  
collect() starts collecting a Flow and provides a FlowCollector to it.  
emit(value) sends a value from the producer to the current collector.  
A cold Flow starts producing only when collect() is called.  
Each collector of a cold Flow normally gets its own execution of the producer.  
Hot Flows like StateFlow and SharedFlow exist independently of collectors.  
StateFlow maintains the latest value and delivers it to active collectors.  
Flow itself does not know about LifecycleOwner; lifecycle controls collection through APIs like repeatOnLifecycle.  
Internally, the core relationship is Flow.collect(collector) → producer → emit(value) → collector.emit(value).

StateFlow is used to represent the current state, while SharedFlow is mainly used to broadcast events/data.

StateFlow always has a current value; SharedFlow does not have a value property.

A new StateFlow collector immediately receives the latest state.

A new SharedFlow collector receives old values only if replay is configured.

StateFlow is essentially a specialized SharedFlow with **replay = 1 and a required current value**.

StateFlow is useful for **UI state** like Loading, Success, Error, and user data.

SharedFlow is useful for **events** like navigation, Snackbar, Toast, or refresh events.

StateFlow updates using state.value = newValue; SharedFlow emits using emit() or tryEmit().

Simple rule: **StateFlow = “What is the state now?”; SharedFlow = “What event/value should I broadcast?”**

```kotlin
interface MyFlow\<T\> {

suspend fun collect(collector: MyCollector\<T\>)

}

interface MyCollector\<T\> {

suspend fun emit(value: T)

}

class MyStringFlow : MyFlow\<String\> {

override suspend fun collect(

collector: MyCollector\<String\>

) {

collector.emit("A")

collector.emit("B")

collector.emit("C")

}

}

val flow = MyStringFlow()

flow.collect(

object : MyCollector\<String\> {

override suspend fun emit(value: String) {

println(value)

}

}

)
```

## Coroutines

-\>coroutine can pass control flow back and forth each other , so this is called cooperative routine

-\>ava.lang.OutOfMemoryError If we use multiple thread but we do not get OutOfMemoryError when we use coroutines. So coroutines are lightweight threads

*Structured Concurrency*

*1.every coroutines needs to be started in a logical scope with limited life-time*

*2.coroutines startred in the same scope forms hierarchy*

*3.Parent job won't complete until all its children have completed*

*4.Canceling parent job will cancel all children. cancelling child won't cancel parent or sibling*

*5.If a child coroutines fails ,the exceptions propagates upwards*

*supervisorJob will not handle exception caught in async, it propagtes exception to siblings*

-\>launch {} starts a new coroutine, and multiple launch blocks can execute concurrently on same thread

If they run on different threads, they may execute in parallel;

### Concurrent execution

```kotlin
runBlocking {

launch {

println("A start")

delay(1000)

println("A end")

}
```

launch {

println("B start")

delay(1000)

println("B end")

}

}  
### parallel execution on different thread
runBlocking {

launch(Dispatchers.Default) {

println("A start: \${Thread.currentThread().name}")

Thread.sleep(1000)

println("A end: \${Thread.currentThread().name}")

}

launch(Dispatchers.Default) {

println("B start: \${Thread.currentThread().name}")

Thread.sleep(1000)

println("B end: \${Thread.currentThread().name}")

}

}

### The coroutines builders are

:

launch {} → starts a coroutine and returns a Job.

async {} → starts a coroutine and returns a Deferred\<T\> for a result.

runBlocking {} → starts a coroutine and **blocks the current thread** until it finishes; mainly used in tests/bridging regular code to suspend code, not normal Android UI code

*<u>withContext()</u>* = temporarily change the coroutine's context for a block, get the result, then continue, It is not a Coroutine builder

Job → an **interface/type** that represents the lifecycle of a coroutine.

join() → a **suspending function on Job** that waits until that coroutine finishes.

Deferred → an **interface/type**, a special kind of Job that also represents a future result.

### CoroutineExceptionHandler

CoroutineExceptionHandler handles uncaught exceptions from a coroutine, mainly launch.

If one child coroutine fails, the exception **cancels its parent**, which can also cancel sibling coroutines.

The handler receives the uncaught exception after it propagates through the coroutine hierarchy.

Use supervisorScope when sibling coroutines should continue independently.

*<u>CancellationException</u>*

job.cancel() causes the coroutine to be cancelled.

delay() detects the cancellation and throws CancellationException.

catch catches the CancellationException so we can observe/handle it.

throw e does not cancel again; it lets the cancellation propagate.

Without throw e, the cancellation can be accidentally swallowed.

Here, job.cancel() causes the cancellation, and delay() throws CancellationException.Important: CancellationException isn't really an "error" like a network exception. It's the coroutine's normal mechanism for saying "this coroutine has been cancelled."

*val job = launch {*

*try {*

*delay(5000)*

*doWork()*

*} catch (e: CancellationException) {*

*println("Coroutine cancelled")*

*throw e*

*} catch (e: Exception) {*

*println("Real error: \${e.message}")*

*}*

*}*

*job.cancel()*

*val job = launch {*

*runCatching {*

*delay(5000)*

*doWork()*

*}.onFailure { e -\>*

*if (e is CancellationException) {*

*throw e*

*}*

*println("Real error: \${e.message}")*

*}*

*}*

*job.cancel()*

### supervisorScope

creates a supervisor parent where failure of one child does not automatically cancel the other children

1.First launch fails, but the second launch can continue.

2.First async fails, but the second async can continue,However, async stores its exception in the Deferred, so you need to handle

Yes — if you call await() on a failed async, the exception is rethrown at the await() point

supervisorScope {....}

*<u>SupervisorJob -\></u>* if one launch fails other will continue.

```kotlin
private val scope =

CoroutineScope(Dispatchers.Main + SupervisorJob())

scope.launch {

loadProfile()

}

scope.launch {

loadNotifications() // fails

}

scope.launch {

loadRecommendations()
```

}

### Continuation

A **Continuation** represents the point where a suspended coroutine should continue its execution.

When a suspend function suspends, Kotlin stores the current execution state in the continuation.

When the suspended operation completes, resume() is called on the continuation.

The coroutine then continues from the point where it was suspended.

The compiler manages the Continuation parameter automatically; you normally don't write it yourself.

```kotlin
*suspend fun getData(): String {*

*return suspendCoroutine { continuation -\>*

*continuation.resume("Data received")*

*}*

*}*

withContext() = temporarily execute a block with a different CoroutineContext, wait for its result, then continue the same coroutine.

A <u>Dispatcher</u> is NOT a thread.

A Dispatcher decides which thread or thread pool should execute a coroutine.

## Main Jetpack Composable lifecycle

Enter Composition → Recomposition → Leave Composition

Enter Composition : When a Composable first becomes part of the UI

Recomposition: If a state used by the Composable changes

Leave Composition:If the Composable is no longer needed

**1. remember  
**var count by remember { mutableStateOf(0) }  
Keeps state across recompositions.

**2. rememberSaveable  
**var name by rememberSaveable { mutableStateOf("") }  
Keeps state across configuration changes/recreation/process death.

**3. mutableStateOf  
**val state = mutableStateOf(0)  
Creates observable Compose state that triggers recomposition when changed.

**4. derivedStateOf  
**val result by remember { derivedStateOf { count \* 2 } }  
Creates state derived from other state.

**5. State\<T\>  
**val state: State\<Int\> = remember { mutableStateOf(0) }  
Provides read-only access to Compose state.

**6. MutableState\<T\>  
**val state: MutableState\<Int\> = mutableStateOf(0)  
Provides both read and write access.

**7. StateFlow\<T\>  
**val state: StateFlow\<Int\> = viewModel.state  
Used to expose UI state from ViewModel to Compose.

**8. SharedFlow\<T\>  
**val events: SharedFlow\<String\> = viewModel.events  
Used for one-time events like navigation or Snackbar.

State Hoisting means moving state from a child Composable to its parent, so the parent controls the state.

Compose does not create SavedStateHandle. The ViewModel infrastructure provides it to the ViewModel, and it allows small UI state to be restored after process death.

Single source of truth — only one place owns the state.

Reusable Composables — child doesn't manage its own state.

Easy communication — parent can share the same state with multiple children.

Better testing — child becomes stateless and easier to test.

LaunchedEffect(){}

1.  Composition: Yes

2.  Recomposition: No

3.  Key/State changes: Yes, restarts

4.  Coroutine: Yes

5.  Use case: API calls, Flow collection, animations, delays.

SideEffect{}

1.  Composition: Yes

2.  Recomposition: Yes

3.  no Key only State changes: Yes, after successful recomposition

4.  Coroutine: No

5.  Use case: Update non-Compose objects with the latest Compose state.

DisposableEffect(){}

1.  Composition: Yes

2.  Recomposition: No

3.  Key/State changes: Yes, old cleanup + runs again

4.  Coroutine: No

5.  Use case: Register/unregister listeners, receivers, or observers.

6.  Key changes → onDispose() → effect runs again

7.  Composable leaves Composition → onDispose() runs

```kotlin
DisposableEffect(userId) {

println("Setup for user \$userId")

onDispose {

println("Cleanup for user \$userId")}}
```

## produceState

produceState is used when you have asynchronous work and want to expose its result as a Compose State

produceState(initialValue, vararg keys)

val user by produceState\<User?\>(initialValue = null ,id) { value = getUserFromApi() }

Text(user?.name ?: "Loading...")

-\>getUserFromApi calls on main dispathchers coroutines

### rememberCoroutineScope

rememberCoroutineScope() gives you a CoroutineScope tied to the Composable's lifecycle.

```kotlin
val scope = rememberCoroutineScope()

Button(onClick = {

scope.launch {

delay(1000)

// work

}
```
```

}) {

Text("Click")

}

## Navigation in Compose

NavHostController → controls navigation

NavHost → contains your destinations/screens

composable() → defines each screen

```kotlin
NavHost(navController, startDestination = "home") {

composable("home") { HomeScreen(navController) }

composable("details") { DetailsScreen() }

}
```

### Passing data

Navigate to details screen

navController.navigate("details")

navController.navigate("details/100")

composable("details/{id}") { backStackEntry -\>

val id = backStackEntry.arguments?.getString("id")

}

Going back navController.popBackStack()

### launchSingleTop

If this destination is already at the top of the back stack, don't create another instance.

navController.navigate("home") {

launchSingleTop = true

}

### popUpTo

Login → Home → Details → Profile

Current: Profile

navController.navigate("Settings") { popUpTo("Home") }

It removes everything above Home:

popUpTo("Home") { inclusive = true }

It removes everything including home and above

navController.navigate("home") {

launchSingleTop = true

restoreState = true

popUpTo(navController.graph.startDestinationId){

saveState = true}

}

launchSingleTop → don't duplicate the top destination.

popUpTo → control/remove back-stack destinations.

saveState → save destination state.

restoreState → restore previously saved destination state.

## HILT DI

Hilt processes and validates your dependency graph at compile time,but it creates the actual object instances at runtime.

@HiltAndroidApp

class MyApplication : Application()-initializes Hilt's DI

@AndroidEntryPoint

class MainActivity : ComponentActivity()

Activity, Fragment, Service → @AndroidEntryPoint only when that component directly needs Hilt injection.

class UserViewModel @Inject constructor(private val repository: UserRepository)

Hilt sees that UserViewModel needs UserRepository, so it creates the repository and passes it.

@Module //Defines dependency configuration

@InstallIn(SingletonComponent::class) //Specifies which Hilt component owns the dependency

object NetworkModule{

@Provides //Tells Hilt how to create an object

@Singleton //One instance per component lifetime

fun provideRetrofit(): Retrofit{...}

}

@HiltViewModel //tells Hilt to create and inject dependencies into the ViewModel and manage its lifecycle

class UserViewModel @Inject constructor(

private val repository: UserRepository

) : ViewModel()

access hilt created objects

val viewModel: UserViewModel = hiltViewModel()

@Inject lateinit var repository: UserRepository

```kotlin
@HiltViewModel(assistedFactory = UserViewModel.Factory::class)  
class UserViewModel @AssistedInject constructor(  
private val repository: UserRepository,  
@Assisted private val userId: String  
) : ViewModel() {

@AssistedFactory  
interface Factory {  
fun create(userId: String): UserViewModel  
}  
}
```

in compose

val viewModel = hiltViewModel\<UserViewModel, UserViewModel.Factory\> { factory -\>

factory.create("123")

}

## Koin DI

### Init koin

```kotlin
class MyApp : Application() {

override fun onCreate() {

super.onCreate()

startKoin {

androidContext(this@MyApp)

modules(appModule) }

}
```

}

### Define module

```kotlin
val appModule = module {

factory { LoginRepository() } //every time new

single { UserRepository() } //single to app

viewModel { UserViewModel(get()) }

scope\<MyActivity\> {scoped { UserSession() }}

}
```

### use objects

val vm: UserViewModel = koinViewModel()

val repo: LoginRepository = koinInject()

### Dynamic parameters

```kotlin
class UserViewModel(

val repository: UserRepository,

val userId: String,

val userName: String

)
```

Koin creation in module

```kotlin
viewModel { params -\>

UserViewModel(

get(),

params.get(),

params.get()

)

}
```

val vm: UserViewModel = koinViewModel(

parameters = { parametersOf("123", "John") }

)

## WorkManager

WorkManager is an Android library used to run background work that should eventually complete, even if the app is closed.

```kotlin
class UploadWorker(...) : Worker(...) {

override fun doWork(): Result {

}
```

### OneTimeWork

```kotlin
val constraints = Constraints.Builder()

.setRequiresBatteryNotLow(true)

.setRequiredNetworkType(NetworkType.UNMETERED)

.build()

val data = workDataOf(

"userId" to 123,

"userName" to "John"

)

val request = OneTimeWorkRequestBuilder\<UploadWorker\>()

.setConstraints(constraints)

.setInputData(data)

.build()

WorkManager.getInstance(context).enqueue(request)
```

### Receive in doWork

val userId = inputData.getInt("userId", 0)

### PeriodicWork

```kotlin
val request = PeriodicWorkRequestBuilder\<SyncWorker\>(

repeatInterval = 1, TimeUnit.HOURS,

Flextime = 15, TimeUnit.MINUTES
```

)

Repeat every 1 hour, and WorkManager has a 15-minute flex window near the end of that period to run it.

### Chain requests

WorkManager.getInstance(context)

.beginWith(downloadRequest)

.then(processRequest)

.then(uploadRequest)

.enqueue()

WorkManager.getInstance(context)

.beginUniqueWork(

"sync",

ExistingWorkPolicy.APPEND, //or REPLACE

newRequest

)

.enqueue()

## Data Store

val Context.dataStore by preferencesDataStore(name = "app_preferences")

val USER_NAME = stringPreferencesKey("user_name")

val USER_AGE = intPreferencesKey("user_age")

### write/update data

context.dataStore.edit { preferences -\>

preferences\[USER_NAME\] = "John"

preferences\[USER_AGE\] = 25

}

### read as flow

val userNameFlow = context.dataStore.data

.map { preferences -\> preferences\[USER_NAME\] }

val ageFlow = context.dataStore.data

.map { preferences -\> preferences\[USER_AGE\] }

## Memory Leak

An object that is no longer needed is still reachable and therefore cannot be garbage collected.

reachable means : There is still some reference/path from a GC Root to that object.

## Application Not Responding

.(ANR)
for heavy operation on main thread like calculation,network/DB operation

## Reduce Startup time

Reduce work in Application.onCreate() and Activity startup; initialize only what is immediately required.

Use lazy initialization and defer non-critical SDK, database, and file operations until needed.

Measure startup with profiling/Macrobenchmark and use Baseline Profiles to improve frequently executed startup code.

### **R8** is Android's code shrinking, optimization, and obfuscation tool used mainly for release builds.It can remove unused classes/methods (**shrinking**), optimize bytecode (**optimization**), and rename classes/methods/fields (**obfuscation**) to make reverse engineering harder and reduce app size. **ProGuard** is the older configuration/rules format/tooling.

```kotlin
android {

buildTypes {

release {

isMinifyEnabled = true

isShrinkResources = true

}
```

}

}

isMinifyEnabled = true → enables R8 shrinking/optimization/obfuscation.

isShrinkResources = true → removes unused resources

app/

└── proguard-rules.pro

release {

isMinifyEnabled = true

proguardFiles(

getDefaultProguardFile("proguard-android-optimize.txt"),

"proguard-rules.pro"

)

}

## Junit and mockkito

```kotlin
@Test

fun getUserName_returnsUserName() {

val repository = mockk\<UserRepository\>()

every {

repository.getUser()

} returns User(1, "John")

val viewModel = UserViewModel(repository)

val result = viewModel.getUserName()

assertEquals("John", result) //expected and actual

}
```

## @Test :

Marks a function as a test

## @Before :** Runs **before every test method

## @After :

Runs **after every test method**.

@BeforeClass : Runs **once before all tests in the class , used for** static method.

@AfterClass

assertEquals() : Did I get the result I expected?

assertNotEquals(10, result)

assertTrue(user.isActive)

assertFalse(user.isBlocked)

assertNull(user.email)

assertNotNull(user)

assertSame(user1, user2) : Checks that two variables refer to the **same object**.

assertEquals → values are equal

assertSame → same object/reference

MockK — creating mocks

every {

repository.getUser()

} returns User(1, "John")

Whenever getUser() is called, return this User.

every {

repository.getUser()

} throws RuntimeException("Network error")

every {

repository.getUser()

} returnsMany listOf(

User(1, "John"),

User(2, "David")

)

verifyOrder {

repository.login()

repository.getUser()

}

verifySequence {

repository.login()

repository.getUser()

}

For suspend functions , suspend fun getUser(): User

coEvery {

repository.getUser()

} returns User(1, "John")

## Regex

### Keys

1= ^ —\> Start

2= \$ — End

3= . — Any single character(dot) Example -\> a.c , Matches -\> abc,axc,a1c

4= \[ \] — Character set Example -\> \[abc\] , Means one character from a, b, or c.

5= \[0-9\] — Any digit , Matches one digit from 0 to 9

6= \[a-z\] — Lowercase letter

7= \[A-Z\] — Uppercase letter

8= \[a-zA-Z\] — Any English letter

9= \[^...\] — NOT Example -\> \[^0-9\] , Anything except a digit.

### Quantifiers

1= + — One or more Example -\> \[0-9\]+ , matches 1,123,123456

2= \* — Zero or more Example -\> \[0-9\]\* , matches “” , 1,123

3= ? — Zero or one Example -\> colou?r

4= {n} — Exactly n , \[0-9\]{3} , 123,234,567

5= {n,m} — Between n and m min = n , max = m

6= {n,} — At least n Example \[0-9\]{3,} 3 or more digits.

7= \d — Digit

8= \w — Word character , including digits

9= \s — Whitespace

10= \\ — Actual dot

11= \| — OR

12= ( ) — Group , ex -\> (ab)+ , ababa,ababab

\[abc\] vs (abc)

\[abc\] one character: a OR b OR c

(abc) the complete sequence abc

Email regex

^\[A-Za-z0-9.\_%+-\]+@\[A-Za-z0-9.-\]+\\\[A-Za-z\]{2,}\$

PAN Card

^\[A-Z\]{5}\[0-9\]{4}\[A-Z\]\$

```kotlin
fun isValidEmail(email: String): Boolean {

val regex = Regex("^\[A-Za-z0-9.\_%+-\]+@\[A-Za-z0-9.-\]+\\.\[A-Za-z\]{2,}\$")

or we can use \\

val regex = Regex("""^\[A-Za-z0-9.\_%+-\]+@\[A-Za-z0-9.-\]+\\\[A-Za-z\]{2,}\$""")

return regex.matches(email)

}
```

## Modularization

Modularization is the process of dividing a large Android application into multiple independent Gradle modules based on features or common responsibilities. Feature modules contain business features such as login, profile, or payments, while core modules contain shared functionality such as networking, database, UI, and common utilities. Modules communicate through explicit Gradle dependencies, which improves code organization, maintainability, team scalability, and potentially build performance.

Modularization improves build performance by dividing the application into independent Gradle modules. When a module changes, Gradle can incrementally compile that module and affected modules while reusing cached outputs of unchanged modules. Independent modules can also be built in parallel.

## Clean Architecture

| **Element**        | **Main purpose**                            |
|--------------------|---------------------------------------------|
| **Presentation**   | **Show UI and handle UI interaction**       |
| **ViewModel**      | **Manage UI state and coordinate actions**  |
| **UseCase**        | **Perform one business operation**          |
| **Repository**     | **Provide data to the domain**              |
| **RepositoryImpl** | **Decide how data is actually obtained**    |
| **Remote/API**     | **Communicate with server**                 |
| **Local/DAO**      | **Communicate with local database**         |
| **DTO**            | **Represent API data**                      |
| **Domain Model**   | **Represent application/business data**     |
| **Mapper**         | **Convert between models**                  |
| **DI**             | **Provide dependencies between components** |

## Presentation:

UI, ViewModel, UI state, UI events, navigation.

## Domain:

Business logic, UseCases, domain models, repository interfaces.

## Data:

Repository implementations, APIs, DTOs, Room/DAO, entities, data sources, mappers.

## SOLID Principles

S — Single Responsibility Principle: A class should have only one reason to change.

ex : viewmodel only responsible for UI data,state

O — Open/Closed Principle: A class should be open for extension but closed for modification.

ex : RecyclerView.Adapter You extend/customize Adapter by overriding methods

L — Liskov Substitution Principle: A child class should be replaceable for its parent without breaking the behavior.

UserRepositoryImpl can replace UserRepository wherever the interface is expected.

I — Interface Segregation Principle: A class should not be forced to implement methods it doesn't need.

D — Dependency Inversion Principle: High-level code should depend on abstractions (interfaces), not concrete implementations.

ex : ViewModel depends on UserRepository interface, not on UserRepositoryImpl

## Design Pattern

### Creational Patterns :

Concerned with how objects are created.

1.**Singleton :** is a creational pattern ensures only one instance of an object exists

*1.create private static volatile variable*

*2.Without volatile, you may face a half-initialized object due to JVM reordering.*

*3.With volatile, another thread can immediately gets changes of object*

*3.create private constructor*

*4.use synchronized block inside the method*

*5. If you use synchronized block to method/fun below are the disadvantages*

*A.Even if object is already created, every thread must acquire the lock before checking*

*B.Even after initialization, synchronization is still used,wastes CPU when lots of threads call*

*6.we use LOCK or Singleton.class*

*7.Imagine Singleton.class or LOCK as the master key to the class. When any thread holds that key,*

*no other thread can enter that synchronized block, also it has meta data of class*

*JAVA*

```kotlin
public class Singleton {

private volatile static Singleton *object*;

private static final Object *LOCK* = new Object();

private Singleton() {

}


public static Singleton getInstance() {

if (*object* == null) {

synchronized (*LOCK*){

if (*object* == null) {

*object* = new Singleton();

}

}

}

return *object*;

}

}
```

## *KOTLIN*

```kotlin
class SingletonKT private constructor(){

companion object{

@Volatile

private var instance : SingletonKT? = null

private val LOCK = Any()

@JvmStatic

fun getInstance() : SingletonKT {

return instance ?: *synchronized*(LOCK){

instance ?: SingletonKT().*also* {

instance = it

} }

}}

}
```

## 2.Factory

Factory is a creational design pattern that centralizes object creation and allows the caller to get the appropriate object without knowing its creation details

```kotlin
*interface Notification {*

*fun send()*

*}*

*class EmailNotification : Notification {*

*override fun send() {*

*println("Sending Email")*

*}*

*}*

*class SmsNotification : Notification {*

*override fun send() {*

*println("Sending SMS")*

*}*

*}*

*object NotificationFactory {*

*fun create(type: String): Notification {*

*return when (type) {*

*"email" -\> EmailNotification()*

*"sms" -\> SmsNotification()*

*else -\> throw IllegalArgumentException("Unknown type")*

*}*

*}*

*}*

*val notification = NotificationFactory.create("email")*

*notification.send()*
```

3.Abstract Factory Pattern

Abstract Factory is a creational pattern used when you need to create families of related objects, rather than just one object.

interface Button { fun draw() }

interface TextField { fun draw() }

class LightButton : Button {override fun draw() {...}}

class LightTextField : TextField {override fun draw() {...}}

class DarkButton : Button {override fun draw() {...}}

class DarkTextField : TextField {override fun draw() {...}}

interface ThemeFactory {

fun createButton(): Button

fun createTextField(): TextField

}

class LightThemeFactory : ThemeFactory {

override fun createButton(): Button {

return LightButton()

}

override fun createTextField(): TextField {

return LightTextField()

}

}

class DarkThemeFactory : ThemeFactory {

override fun createButton(): Button {

return DarkButton()

}

override fun createTextField(): TextField {

return DarkTextField()

}

}

if user chooses dark theme

val factory: ThemeFactory = DarkThemeFactory()

val button = factory.createButton()

button.draw()...

## 4.Builder Design Pattern

Builder pattern is used to construct a complex object step-by-step, especially when it has many optional parameters.

class User private constructor

(

val name: String,

val age: Int){

class UserBuilder {

private var name = ""

private var age = 0

fun setName(name: String): UserBuilder {

this.name = name

return this

}

fun setAge(age: Int): UserBuilder {

this.age = age

return this

}

fun build(): User {

return User(name, age)

}

}

}

val user = User.Builder()

.setName("John")

.setAge(25)

.build()

Android: NotificationCompat.Builder, AlertDialog.Builder.

## Structural Patterns

Concerned with how classes/objects are combined.

## 1.Adapter Pattern

Adapter acts like a bridge between two incompatible interfaces**.**

```kotlin
interface Payment {

fun pay()

}

class OldPayment {

fun makePayment() {

println("Payment done")

}

}

class PaymentAdapter(

private val oldPayment: OldPayment

) : Payment {

override fun pay() {

oldPayment.makePayment()

}

}

val payment: Payment = PaymentAdapter(OldPayment())

payment.pay()
```

## 2.Facade

Facade provides a simple interface to a complex subsystem and hides its internal complexity.

## 3.Decorator
Decorator adds additional behavior to an existing object by wrapping it, without modifying the original class.

```kotlin
interface Coffee {

fun getPrice(): Int

}

class SimpleCoffee : Coffee {

override fun getPrice() = 100

}

class MilkDecorator(

private val coffee: Coffee

) : Coffee {

override fun getPrice(): Int {

return coffee.getPrice() + 20

}

}

val coffee: Coffee = SimpleCoffee()

val coffeeWithMilk: Coffee = MilkDecorator(coffee)

println(coffeeWithMilk.getPrice()) // 120

We can even add another decorator:

val coffee = MilkDecorator(

SugarDecorator(

SimpleCoffee()

)
```

)

## Behavioral Patterns

Concerned with how objects communicate and distribute responsibilities.

## 1.Observer

Observer pattern means one object changes, and automatically notifies other objects that are interested in that change.

```kotlin
interface Observer {

fun update(temp: Int)

}

interface Observable {

fun registerObserver(observer: Observer)

fun unregisterObserver(observer: Observer)

fun notifyObserver()

}

class WeatherStation : Observable {

class DisplayWeather : Observer {

runBlocking {

val weatherStation = WeatherStation()

val displayWeather = DisplayWeather(weatherStation)

weatherStation.tempChanged(10)

//calls notifyObserver and then update() foreach observer

delay(10)

weatherStation.tempChanged(20)
```

}

## 2.State

: Behavior changes depending on the object's current state.

example

```kotlin
sealed class UiState {

object Loading : UiState()

data class Success(val data: String) : UiState()

data class Error(val message: String) : UiState()

}
```

UI behaves differently based on the current state:

Log in
