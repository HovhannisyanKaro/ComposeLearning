package com.composeappdemo

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

data class TestRequest(val data: String)
data class TestResultData(val data: String = "{ \"result\" : \"SomeResult\" }")

interface TestApi {
    suspend fun requestTestData()
}

interface TestRepository {
    suspend fun testRequest(request: TestRequest): Flow<Resource<TestResultData>>
}

//TestApi is nullable because of I am very lazy
class TestRepositoryImpl(val api: TestApi? = null) : TestRepository {

    override suspend fun testRequest(request: TestRequest): Flow<Resource<TestResultData>> {
        return object : SomeClassThatMakeNetworkRequests<TestResultData, Unit>() {
            //override what fun what you want/need ?: O_o
        }.asFlow()
    }
}

class TestViewModel(val testRepository: TestRepository) : ViewModel(), IModel<TestState, TestIntent> {
    /**
     * or just inject in whatever viewModel that you want
     */
//    private val testRepository: TestRepository by inject()

    private val _state = MutableSharedFlow<TestState>(extraBufferCapacity = 1)
    override val state: Flow<TestState> = _state

    override val intents = MutableSharedFlow<TestIntent>(extraBufferCapacity = 1)

    init {
        intents.process().launchIn(viewModelScope)
    }

    fun processIntent(intent: TestIntent) = intents.tryEmit(intent)

    private fun Flow<TestIntent>.process() = onEach { intent ->
        when (intent) {
            TestIntent.InitialIntent -> updateState(TestState.InitialState)
            is TestIntent.RequestTestDataIntent -> loadTestData(intent.testRequest)
            is TestIntent.ShowErrorIntent -> updateState(TestState.TestDataErrorState(intent.errorMsg))
        }
    }

    private fun loadTestData(request: TestRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            testRepository.testRequest(request).collect {
                when (it.status) {
                    Resource.Status.LOADING -> updateState(TestState.TestDataLoadingState)
                    Resource.Status.ERROR -> updateState(TestState.TestDataErrorState(it.error?.message))
                    Resource.Status.SUCCESS -> updateState(TestState.TestDataSuccessState(it.data))
                }
            }
        }
    }

    private fun updateState(state: TestState) {
        _state.tryEmit(state)
    }
}

@Composable
fun TestScreen() {
    //inject ViewModel by compose()
    val testViewModel = TestViewModel(TestRepositoryImpl())

    val intentChannel = Channel<TestIntent>()

    var state by remember {
        mutableStateOf<TestState>(TestState.InitialState)
    }

    LaunchedEffect(key1 = true) {
        intentChannel.consumeAsFlow().onEach(testViewModel::processIntent).launchIn(this)

        testViewModel.state.onEach {
            state = it
        }.launchIn(this) // hope this is right scope to launch flow
    }

    Button(onClick = {
        intentChannel.trySend(TestIntent.RequestTestDataIntent(TestRequest("fake test data")))
    }) {
        Text(text = "make test request")
    }

    when (state) {
        TestState.InitialState -> InitialState()
        is TestState.TestDataErrorState -> ShowError()
        TestState.TestDataLoadingState -> Loading()
        is TestState.TestDataSuccessState -> TestDataSuccessState()
    }
}

@Composable
fun InitialState() {

}

@Composable
fun ShowError() {

}

@Composable
fun Loading() {

}

@Composable
fun TestDataSuccessState() {

}

sealed class TestState : IState {
    object InitialState : TestState()

    object TestDataLoadingState : TestState()

    data class TestDataSuccessState(val testResultData: TestResultData?) : TestState()

    data class TestDataErrorState(val errorMsg: String?) : TestState()
}

sealed class TestIntent : IIntent {
    object InitialIntent : TestIntent()

    data class ShowErrorIntent(val errorMsg: String) : TestIntent()

    data class RequestTestDataIntent(val testRequest: TestRequest) : TestIntent()
}


interface IModel<S : IState, I : IIntent> {
    val intents: Flow<I>
    val state: Flow<S>
}

interface IState {}

interface IIntent {}


abstract class SomeClassThatMakeNetworkRequests<ResultType, RequestType> {

    fun asFlow(): Flow<Resource<ResultType>> = flow {
        emit(Resource.loading(null))
        //if else ->
        emit(Resource.success(null))
        //if else ->
        emit(Resource.error(Throwable(), null))
    }

    protected open fun isError(response: RequestType?): Throwable? {
        return null
    }
}

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, error)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}