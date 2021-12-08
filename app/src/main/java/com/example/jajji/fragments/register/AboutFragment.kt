package com.example.jajji.fragments.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.jajji.MainActivity
import com.example.jajji.databinding.FragmentAboutBinding
import com.example.jajji.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AboutFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("key")
            param2 = it.getString("number")
        }
    }

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseFireStore: FirebaseFirestore
    private var id = System.currentTimeMillis().toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        firebaseFireStore = FirebaseFirestore.getInstance()

        val currentUser = FirebaseAuth.getInstance().currentUser
        binding.apply {
            btn.setOnClickListener {
                val name = edName.text.toString()
                val surName = edName.text.toString()

                if (name == "" || surName == "") {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                } else {
                    firebaseFireStore.collection("users")
                        .document(id)
                        .set(
                            User(
                                param1,
                                name,
                                surName,
                                param2.toString()
                            )
                        )
                        .addOnSuccessListener {
                            if (currentUser != null) {
                                val intent = Intent(requireContext(), MainActivity::class.java)
                                startActivity(intent)
                            }
                            requireActivity().finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(requireContext(), "Hato", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AboutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}