package com.raulespim.flights.feature_flighthistory.util

import junit.framework.TestCase.fail
import org.junit.Assert

fun assertSameClass(first: Any?, second: Any?) {
    Assert.assertEquals(
        first!!.javaClass.name,
        second!!.javaClass.name
    )
}

inline fun <reified T: Any> assertInstanceOf(any: Any?) {
    if (any !is T) {
        fail("Expected instance of ${any!!.javaClass.simpleName}, but found ${T::class.simpleName}")
    }
}