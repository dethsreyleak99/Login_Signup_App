package com.example.login_signup_app.ui.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.login_signup_app.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {
//        binding.btnBack.setOnClickListener {
//            findNavController().navigateUp()
//        }

        binding.btnResetPassword.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (validateEmail(email)) {
                // In a real app, this would send a reset email
                showMessage("Password reset instructions sent to $email")
                findNavController().navigate(com.example.login_signup_app.R.id.action_forgotPasswordFragment_to_loginFragment)
            }
        }

        binding.tvBackToLogin.setOnClickListener {
            findNavController().navigate(com.example.login_signup_app.R.id.action_forgotPasswordFragment_to_loginFragment)
        }
    }

    private fun validateEmail(email: String): Boolean {
        return if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Valid email required"
            false
        } else {
            binding.tilEmail.error = null
            true
        }
    }

    private fun showMessage(message: String) {
        android.widget.Toast.makeText(requireContext(), message, android.widget.Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}