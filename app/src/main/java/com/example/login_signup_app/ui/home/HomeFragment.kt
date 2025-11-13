package com.example.login_signup_app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.login_signup_app.LoginApp
import com.example.login_signup_app.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.createFactory(requireActivity().application as LoginApp)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObservers()
        loadUserData()
    }

    private fun setupUI() {
        binding.btnProfile.setOnClickListener {
            showMessage("Profile clicked")
        }

        binding.cardSettings.setOnClickListener {
            showMessage("Settings clicked")
        }

        binding.cardStatistics.setOnClickListener {
            showMessage("Statistics clicked")
        }

        binding.cardHelp.setOnClickListener {
            showMessage("Help & Support clicked")
        }

        binding.cardLogout.setOnClickListener {
            logout()
        }

        binding.btnFeature1.setOnClickListener {
            showMessage("Feature 1 clicked")
        }

        binding.btnFeature2.setOnClickListener {
            showMessage("Feature 2 clicked")
        }

        binding.btnFeature3.setOnClickListener {
            showMessage("Feature 3 clicked")
        }
    }

    private fun setupObservers() {
        viewModel.userData.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.tvWelcomeName.text = "Hello, ${it.username}!"
                binding.tvEmail.text = it.email
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun loadUserData() {
        lifecycleScope.launch {
            viewModel.loadUserData()
        }
    }

    private fun logout() {
        viewModel.logout()
        findNavController().navigate(com.example.login_signup_app.R.id.action_homeFragment_to_loginFragment)
    }

    private fun showMessage(message: String) {
        android.widget.Toast.makeText(requireContext(), message, android.widget.Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}