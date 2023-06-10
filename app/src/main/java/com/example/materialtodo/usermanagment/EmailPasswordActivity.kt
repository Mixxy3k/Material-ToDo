package com.example.materialtodo.usermanagment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EmailPasswordActivity : Activity() {

    // [START declare_auth]
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    // [END declare_auth]

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = Firebase.auth
        // [END initialize_auth]
    }

    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }
    // [END on_start_check_user]

    fun isUserLoggedIn(): Boolean {
        val currentUser = auth.currentUser
        return currentUser != null
    }

    fun createAccount(email: String, password: String): String {
        var result = ""
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    // set result to error message but only first line after ":"
                    result = "Cannot create account " + task.exception!!.message.toString().split(":")[1]
                }
            }
        return result
    }
    // [END create_user_with_email]

    fun signIn(email: String, password: String): String {
        var Result = ""
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Result = "Cannot sign in " + task.exception!!.message.toString()
                }
            }
        return Result
    }

    fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                // Email Verification sent
            }
        // [END send_email_verification]
    }

    private fun reload() {
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}