package com.helsanf.modular.learn

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SimpleTest {

    private var simpleClass : SimpleClass? = null

    @Before
    fun setUp(){
        simpleClass = SimpleClass()
    }

    @Test
    fun testMultiPlay(){
        val result = simpleClass?.multiply(3,4)
        Assert.assertEquals(12,result)
    }

    inner class SimpleClass{
        fun multiply(x: Int, y: Int): Int {
            if (x > 100)
                throw IllegalArgumentException("Number should be less or equal to 100")
            return x * y
        }
    }

}