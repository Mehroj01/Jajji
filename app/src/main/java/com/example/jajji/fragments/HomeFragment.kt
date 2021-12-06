package com.example.jajji.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.example.jajji.MainActivity

import com.example.jajji.adapters.MainAdapter
import com.example.jajji.adapters.SecondAdapter

import com.example.jajji.model.Furniture
import com.example.jajji.R
import com.example.jajji.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var mainAdapter: MainAdapter
    private lateinit var secondAdapter: SecondAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        loadData()
        binding.apply {
            mainRv.adapter = mainAdapter
           // secondRv.adapter = secondAdapter

        }

        return binding.root
    }

    private fun loadData() {
        val furList = mutableListOf<Furniture>()

        furList.add(
            Furniture(name = "Playhouse", image = R.drawable.housewarm, costs = "800,000 sum")
        )
        furList.add(
            Furniture(name = "Summer playhouse", image = R.drawable.summer, costs = "1,200,000 sum")
        )
        furList.add(
            Furniture(name = "Playhouse", image = R.drawable.image1, costs = "1,000,070 sum")
        )

        furList.add(
            Furniture(name = "Playhouse", image = R.drawable.img2, costs = "1,300,400 sum")
        )
        furList.add(
            Furniture(name = "Playhouse", image = R.drawable.playhous, costs = "4,400,000 sum")
        )
        furList.add(
            Furniture(name = "Playhouse", image = R.drawable.download, costs = "3,400,000 sum")
        )
        furList.shuffle()
        mainAdapter = MainAdapter(furList,
            object : MainAdapter.OnImageClick {
                override fun click(furniture: Furniture) {
                    val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
                    val bundle = Bundle()
                    bundle.putSerializable("fur", furniture)
                    (activity as MainActivity).hideBottom()
                    fab.visibility = View.GONE
                    findNavController().navigate(R.id.action_homeFragment_to_singleFragment, bundle)
                }

                override fun shopClick(furniture: Furniture) {
                    val bundle = Bundle()
                    val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
                    bundle.putSerializable("fur", furniture)
                    (activity as MainActivity).hideBottom()
                    fab.visibility = View.GONE
                    findNavController().navigate(R.id.action_homeFragment_to_singleFragment, bundle)
                }

            })
        secondAdapter = SecondAdapter(furList,
            object : SecondAdapter.OnImageClick {
                override fun click(furniture: Furniture) {
                    val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
                    val bundle = Bundle()
                    bundle.putSerializable("fur", furniture)
                    (activity as MainActivity).hideBottom()
                    fab.visibility = View.GONE
                    findNavController().navigate(R.id.action_homeFragment_to_singleFragment, bundle)
                }

            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}