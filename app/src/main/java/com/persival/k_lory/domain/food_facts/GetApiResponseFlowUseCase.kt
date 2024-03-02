package com.persival.k_lory.domain.food_facts

import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetApiResponseFlowUseCase @Inject constructor(
    private val foodDataCentralRepository: FoodDataCentralRepository
) {
    fun invoke(): StateFlow<FoodWrapper> = foodDataCentralRepository.getApiResponseFlow()
}