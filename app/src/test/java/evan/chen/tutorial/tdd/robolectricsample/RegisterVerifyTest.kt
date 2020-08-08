package evan.chen.tutorial.tdd.robolectricsample

import org.junit.Test

import org.junit.Assert.*

class RegisterVerifyTest {

    @Test
    fun isLoginIdVerify() {
        val registerVerify = RegisterVerify()
        assertFalse(registerVerify.isLoginIdVerify("A1234"))
        assertTrue(registerVerify.isLoginIdVerify("A123456"))
    }
}