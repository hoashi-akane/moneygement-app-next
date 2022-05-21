package com.example.moneygement.service

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.LoginQuery
import com.example.moneygement.model.User
import com.example.moneygement.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class AuthService : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun getLoginQueryToUserModel(loginInfo: LoginQuery.Login): User =
        User().also {
            it.id = loginInfo.id()
            it.name = loginInfo.name()
            it.mail = loginInfo.email()
            it.nickName = loginInfo.nickname()
        }

    fun createAuthSharedPreferences(context: Context): SharedPreferences {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
        return EncryptedSharedPreferences.create(
            "user_info",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun checkUserInfo(context: Context): LoginQuery.Login? {

        val sharedPreferences = createAuthSharedPreferences(context)
        val email = sharedPreferences.getString("email", "")
        val password = sharedPreferences.getString("password", "")

        if (email.equals("") || password.equals("")) {
            return null
        }
        return runBlocking {
            UserRepository().login(email!!, password!!)
        }
    }

    fun logout(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_info", 0)
        sharedPreferences.edit().clear().commit()
    }
}