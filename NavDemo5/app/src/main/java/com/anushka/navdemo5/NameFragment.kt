package com.anushka.navdemo5


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.anushka.navdemo5.databinding.FragmentNameBinding

/**
 * A simple [Fragment] subclass.
 */
class NameFragment : Fragment() {
    private lateinit var binding: FragmentNameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_name, container, false)
        binding.nextButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(activity, "No name", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = bundleOf("name" to name)
                it.findNavController().navigate(R.id.action_nameFragment_to_emailFragment, bundle)
            }
        }
        return binding.root
    }
}
