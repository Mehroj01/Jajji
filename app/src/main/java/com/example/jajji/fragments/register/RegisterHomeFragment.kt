package com.example.jajji.fragments.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jajji.R
import com.example.jajji.databinding.FragmentRegisterHomeBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class RegisterHomeFragment : Fragment() {
    private var _binding: FragmentRegisterHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth: FirebaseAuth
    lateinit var storedVerificationId: String
    private lateinit var mResendingToken: PhoneAuthProvider.ForceResendingToken
    private var isLogin = false
    private val TAG = "RegisterHomeFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterHomeBinding.inflate(inflater, container, false)
        mAuth = FirebaseAuth.getInstance()
        binding.apply {
            next.setOnClickListener {
                if (!isLogin) {
                    val number1 = edTv.text.toString()
                    if (number1 == "") {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    } else {
                        sendVerificationCode(number1)
                        layout1.visibility = View.GONE
                        layout2.visibility = View.VISIBLE
                        isLogin = true
                    }
                } else {
                    verifyPhoneNumberWithCode(
                        storedVerificationId,
                        binding.edTv2.text.toString()
                    )
//                    callbacks.onVerificationCompleted(credential1)
                }

            }
        }

        return binding.root
    }


    private fun sendVerificationCode(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d(TAG, "onVerificationCompleted:$credential")
            Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Toast.makeText(requireContext(), "Invalid request", Toast.LENGTH_SHORT).show()
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Toast.makeText(
                    requireContext(),
                    "The SMS quota for the project has been exceeded",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.d(TAG, "onCodeSent:$verificationId")

            storedVerificationId = verificationId
            mResendingToken = token
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                    updateUI(user)
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                    Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }


    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val bundle = Bundle()
            bundle.putString("key", user.uid)
            bundle.putString("number", user.phoneNumber)
            findNavController().navigate(R.id.aboutFragment, bundle)
//            val intent = Intent(requireContext(), StartActivity::class.java)
//            intent.putExtra("key",user.uid)
//            startActivity(intent)
//            requireActivity().finish()
        }
    }
}