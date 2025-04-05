package com.example.saranskfinal

import com.example.saranskfinal.presentation.onboarding.OnBoarding
import com.example.saranskfinal.presentation.onboarding.OnBoardingManager
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class OnBoardingManagerTest {

    lateinit var onBoardingManager: OnBoardingManager

    @Before
    fun setUp(){
        onBoardingManager = OnBoardingManager()
    }

    @Test
    fun imageAndTitlePop(){
        val expected = OnBoarding(1,"correct")
        onBoardingManager.push(expected)
        onBoardingManager.push(OnBoarding(2,"wrong"))

        val actual = onBoardingManager.pop()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun CorrectPop(){
        val expected = onBoardingManager.size()

        onBoardingManager.push(OnBoarding(1, "some"))
        onBoardingManager.push(OnBoarding(1, "some"))
        onBoardingManager.pop()
        onBoardingManager.pop()

        val actual = onBoardingManager.size()

        Assert.assertEquals(expected, actual)

    }

    @Test
    fun ButtonTextChanged(){
        val expected = OnBoarding(1, "")
        onBoardingManager.push(expected)
        onBoardingManager.pop()
        onBoardingManager.push(OnBoarding(2, "sone"))
        onBoardingManager.push(OnBoarding(4, "some"))

        val actual = onBoardingManager.pop()

        Assert.assertFalse(expected.title == actual.title)
    }
}