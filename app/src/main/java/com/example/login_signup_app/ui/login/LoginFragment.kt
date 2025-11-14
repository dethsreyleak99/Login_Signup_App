package com.example.login_signup_app.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.login_signup_app.LoginApp
import com.example.login_signup_app.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModel.createFactory(requireActivity().application as LoginApp)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        setupObservers()
        setupRememberMe()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (validateInputs(email, password)) {
                // Save remember me preference
                val app = requireActivity().application as LoginApp
                app.sessionManager.setRememberMe(binding.cbRememberMe.isChecked)

                viewModel.login(email, password)
            }
        }

        // Sign up text click
        binding.tvSignupLink.setOnClickListener {
            findNavController().navigate(com.example.login_signup_app.R.id.action_loginFragment_to_signupFragment)
        }

        // Forgot password click
        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(com.example.login_signup_app.R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        // Social login buttons - check if they exist in layout first
        binding.root.findViewById<View?>(com.example.login_signup_app.R.id.btnGoogleLogin)?.setOnClickListener {
            showMessage("Google login - UI only for now")
        }

        binding.root.findViewById<View?>(com.example.login_signup_app.R.id.btnFacebookLogin)?.setOnClickListener {
            showMessage("Facebook login - UI only for now")
        }
    }

    private fun setupObservers() {
        viewModel.loginResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                // Navigate to home only if we're not already there
                val currentDestination = findNavController().currentDestination?.id
                if (currentDestination != com.example.login_signup_app.R.id.homeFragment) {
                    findNavController().navigate(com.example.login_signup_app.R.id.action_loginFragment_to_homeFragment)
                }
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error?.let { showError(it) }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.btnLogin.isEnabled = !isLoading
            binding.btnLogin.text = if (isLoading) "Logging in..." else "Login"
            // Add progress bar if it exists in your layout
            binding.root.findViewById<View?>(com.example.login_signup_app.R.id.progressBar)?.visibility =
                if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun setupRememberMe() {
        val app = requireActivity().application as LoginApp
        if (app.sessionManager.shouldRememberMe()) {
            val user = app.sessionManager.getCurrentUser()
            user?.let {
                binding.etEmail.setText(it.email)
                binding.cbRememberMe.isChecked = true
            }
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        var isValid = true

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Valid email required"
            isValid = false
        } else {
            binding.tilEmail.error = null
        }

        if (password.isEmpty() || password.length < 6) {
            binding.tilPassword.error = "Password must be at least 6 characters"
            isValid = false
        } else {
            binding.tilPassword.error = null
        }

        return isValid
    }

    private fun showMessage(message: String) {
        android.widget.Toast.makeText(requireContext(), message, android.widget.Toast.LENGTH_SHORT).show()
    }

    private fun showError(message: String) {
        android.widget.Toast.makeText(requireContext(), message, android.widget.Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}