package evan.chen.tutorial.tdd.robolectricsample

import android.content.Context
import android.content.SharedPreferences
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*
import org.mockito.Mockito.`when`

class RepositoryTest {

    @Test
    fun saveUserId() {
        val sharedPrefs = mock(SharedPreferences::class.java)
        val sharedPrefsEditor = mock(SharedPreferences.Editor::class.java)
        val context = mock(Context::class.java)

        //Arrange
        `when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPrefs)
        `when`(sharedPrefs.edit()).thenReturn(sharedPrefsEditor)
        `when`(sharedPrefsEditor.putString(anyString(), anyString())).thenReturn(sharedPrefsEditor)

        val userId = "A1234567"
        val preKey = "USER_ID"

        //Act 呼叫repository.saveUserId()
        val repository = Repository(context)
        repository.saveUserId(userId)

        //Assert
        //檢查是否有putString，及傳入的key、value是否正確
        verify(sharedPrefsEditor).putString(
            argThat { key -> key == preKey },
            argThat { value -> value == userId }
        )
        //檢查SharedPreference是否有呼叫commit
        verify(sharedPrefsEditor).commit()
    }
}