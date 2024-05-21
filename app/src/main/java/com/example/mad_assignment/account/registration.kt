package com.example.mad_assignment.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mad_assignment.R
import com.example.mad_assignment.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.registerButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                registerUser(email, password)
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Registration success
                    Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.login)
                } else {
                    // If registration fails, display a message to the user.
                    Toast.makeText(requireContext(), "Registration failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
