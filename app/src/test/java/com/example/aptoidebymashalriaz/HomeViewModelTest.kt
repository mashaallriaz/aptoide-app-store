package com.example.aptoidebymashalriaz

import com.example.aptoidebymashalriaz.domain.interactors.GetAllApps
import com.example.aptoidebymashalriaz.domain.models.App
import com.example.aptoidebymashalriaz.domain.models.Result
import com.example.aptoidebymashalriaz.ui.screens.home.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val mockGetAllApps: GetAllApps = mockk()
    private val mockAppsList = listOf(mockk<App>())

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `GIVEN getAllApps emits Loading WHEN HomeViewModel is created THEN uiState shows loading true and apps`() =
        runTest {
            coEvery { mockGetAllApps.invoke(Unit) } returns flowOf(Result.Loading(mockAppsList))

            val viewModel = HomeViewModel(mockGetAllApps)

            verify(exactly = 1) { mockGetAllApps.invoke(Unit) }

            assertEquals(true, viewModel.uiState.value.loading)
            assertEquals(mockAppsList, viewModel.uiState.value.apps)
        }

    @Test
    fun `GIVEN getAllApps emits Success WHEN HomeViewModel is created THEN uiState shows loading false and apps`() =
        runTest {
            coEvery { mockGetAllApps.invoke(Unit) } returns flowOf(Result.Success(mockAppsList))

            val viewModel = HomeViewModel(mockGetAllApps)
            verify(exactly = 1) { mockGetAllApps.invoke(Unit) }

            assertEquals(false, viewModel.uiState.value.loading)
            assertEquals(mockAppsList, viewModel.uiState.value.apps)
        }


    @Test
    fun `GIVEN getAllApps emits Error WHEN HomeViewModel is created THEN uiState shows loading false and empty apps`() =
        runTest {
            coEvery { mockGetAllApps.invoke(Unit) } returns flowOf((Result.Error(message = "Error")))

            val viewModel = HomeViewModel(mockGetAllApps)
            verify(exactly = 1) { mockGetAllApps.invoke(Unit) }

            assertEquals(false, viewModel.uiState.value.loading)
            assertEquals(emptyList<App>(), viewModel.uiState.value.apps)
        }

    @Test
    fun `GIVEN getAllApps emits Loading then Error WHEN HomeViewModel is created THEN state updates correctly`() =
        runTest {
            coEvery { mockGetAllApps.invoke(Unit) } returns flow {
                emit(Result.Loading(mockAppsList))
                emit(Result.Error(message = "Error"))
            }

            val viewModel = HomeViewModel(mockGetAllApps)
            verify(exactly = 1) { mockGetAllApps.invoke(Unit) }

            assertEquals(false, viewModel.uiState.value.loading)
            assertEquals(mockAppsList, viewModel.uiState.value.apps)
        }
}