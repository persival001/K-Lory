package com.persival.k_lory.ui.main

import com.persival.k_lory.domain.food_facts.FoodWrapper
import com.persival.k_lory.domain.food_facts.GetApiResponseFlowUseCase
import com.persival.k_lory.domain.food_facts.GetFoodPropertiesUseCase
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import com.persival.k_lory.utils_for_tests.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    // Mocks
    private val getFoodPropertiesUseCase: GetFoodPropertiesUseCase = mockk()
    private val getApiResponseFlowUseCase: GetApiResponseFlowUseCase = mockk()

    // ViewModel under test
    private lateinit var viewModel: MainViewModel

    @BeforeEach
    fun setUp() {
        val initialStateFlow = MutableStateFlow<FoodWrapper>(FoodWrapper.Loading)
        // Setup mocks
        coEvery { getApiResponseFlowUseCase.invoke() } returns initialStateFlow
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = MainViewModel(getFoodPropertiesUseCase, getApiResponseFlowUseCase)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `apiResponseFlow emits Loading initially`() = testCoroutineRule.runTest {
        val firstItem = viewModel.apiResponseFlow.first()

        Assertions.assertEquals(FoodWrapper.Loading, firstItem)
    }

    @Test
    fun `launchAPI triggers use case and updates apiResponseFlow`() = testCoroutineRule.runTest {
        val foodPropertiesEntity = FoodPropertiesEntity(
            description = "food",
            ingredients = "food",
            servingSizeUnit = "food",
            servingSize = 100.0,
            energy = 100.0,
            protein = 100.0,
            fat = 100.0,
            carbohydrate = 100.0
        )
        val mockResponse = listOf(foodPropertiesEntity)

        val initialStateFlow = MutableStateFlow<FoodWrapper>(FoodWrapper.Loading)

        coEvery { getApiResponseFlowUseCase.invoke() } returns initialStateFlow

        viewModel = MainViewModel(getFoodPropertiesUseCase, getApiResponseFlowUseCase)

        initialStateFlow.value = FoodWrapper.Success(mockResponse)

        viewModel.launchAPI()

        val items = viewModel.apiResponseFlow.take(2).toList()

        Assertions.assertEquals(listOf(FoodWrapper.Loading, FoodWrapper.Success(mockResponse)), items)
    }

}