package com.example.jajji.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.jajji.model.Furniture
import com.example.jajji.R
import com.example.jajji.databinding.FragmentSingleBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val ARG_PARAM2 = "param2"

class SingleFragment : Fragment() {
    private var fur: Furniture? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fur = it.getSerializable("fur") as Furniture?
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentSingleBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleBinding.inflate(inflater, container, false)

        binding.apply {
            name.text = fur!!.name
            costs.text = fur!!.costs
            image.setImageResource(fur!!.image)
            linear.setOnClickListener {
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
            }


            back.setOnClickListener {
                findNavController().popBackStack()
               // val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)

             //   (activity as MainActivity).showBottom()

            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
       // val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)

       // (activity as MainActivity).showBottom()
    }

}