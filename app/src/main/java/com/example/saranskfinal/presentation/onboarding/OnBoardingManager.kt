package com.example.saranskfinal.presentation.onboarding

data class OnBoarding(
    val image: Int, val title: String
)

class OnBoardingManager() {

    val onBoardingItems = ArrayDeque<OnBoarding>()

    fun push(onBoardingItem: OnBoarding){
        onBoardingItems.addLast(onBoardingItem)
    }

    fun pop(): OnBoarding {
        return onBoardingItems.first()
    }

    fun size(): Int {
        return onBoardingItems.size
    }

}