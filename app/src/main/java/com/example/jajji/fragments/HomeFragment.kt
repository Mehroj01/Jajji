package com.example.jajji.fragments

import android.graphics.Color
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


import com.example.jajji.model.Furniture
import com.example.jajji.R
import com.example.jajji.databinding.FragmentHomeBinding
import com.example.jajji.databinding.ItemTabBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import java.util.*
import kotlin.random.Random

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
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val categoryList = listOf("1 kishilik", "2 kishilik", "3 kishilik", "4 kishilik")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        loadData()
        binding.apply {

            mainRv.adapter = mainAdapter
            // secondRv.adapter = secondAdapter


            for (category in categoryList) {
                tab.addTab(tab.newTab().setText(category))
            }

            categoryList.forEachIndexed { index, s ->
                val tabBinding = ItemTabBinding.inflate(layoutInflater)
                tabBinding.text.text = s
                if (index == 0) {
                    tabBinding.card.setCardBackgroundColor(Color.parseColor("#ff6600"))
                    tabBinding.text.setTextColor(Color.parseColor("#FFFFFFFF"))
                } else {
                    tabBinding.card.setCardBackgroundColor(Color.parseColor("#FFF3F4F6"))
                    tabBinding.text.setTextColor(Color.parseColor("#FF666C8E"))
                }
                tab.getTabAt(index)?.customView = tabBinding.root
            }

            tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val itemTabBinding = ItemTabBinding.bind(tab!!.customView!!)
                    itemTabBinding.card.setCardBackgroundColor(Color.parseColor("#ff6600"))
                    itemTabBinding.text.setTextColor(Color.parseColor("#FFFFFFFF"))
                    if (tab.position == 0) {
                        loadData()
//                        if (mySharedPreference.getString("lang") == "en") {
//                            loadTabData("General", "us")
//                        } else if (mySharedPreference.getString("lang") == "es") {
//                            loadTabData("General", "us")
//                            loadRec(rec, "us")
//                        } else {
//                            loadTabData("General", mySharedPreference.getString("lang"))
//                        }
                    } else {
                        loadData()
//                        if (mySharedPreference.getString("lang") == "en") {
//                            loadTabData(categoryList[tab.position], "us")
//                        } else if (mySharedPreference.getString("lang") == "es") {
//                            loadTabData("General", "us")
//                            loadRec(rec, "us")
//                        } else {
//                            loadTabData(
//                                categoryList[tab.position],
//                                mySharedPreference.getString("lang")
//                            )
//                        }
                    }

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val itemTabBinding = ItemTabBinding.bind(tab!!.customView!!)
                    itemTabBinding.card.setCardBackgroundColor(Color.parseColor("#FFF3F4F6"))
                    itemTabBinding.text.setTextColor(Color.parseColor("#FF666C8E"))
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

        return binding.root
    }

    private fun loadData(){
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

                    val bundle = Bundle()
                    bundle.putSerializable("fur", furniture)
                    (activity as MainActivity).hideBottom()
                    findNavController().navigate(
                        R.id.action_homeFragment_to_singleFragment,
                        bundle
                    )
                }

                override fun shopClick(furniture: Furniture) {
                    val bundle = Bundle()

                }

            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}