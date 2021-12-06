package com.example.jajji.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar


import com.example.jajji.R
import com.example.jajji.databinding.FragmentHolderBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HolderFragment : Fragment(), Toolbar.OnMenuItemClickListener {
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    private var _binding: FragmentHolderBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHolderBinding.inflate(inflater, container, false)

        binding.apply {
            //expandableListView.setIndicatorBounds()
           // expandableListView.setIndicatorBounds(expandableListView.getRight() + 40, expandableListView.getWidth());
        }
      //  setupExpandableListView()
        return binding.root
    }

    private var titleList: List<String>? = null

  //  val data: HashMap<String, List<String>>
   //     get() {val listData = HashMap<String, List<String>>()
  //
  //
  //            val redmiMobiles = ArrayList<String>()
  //            redmiMobiles.add("1 kishilik ")
  //            redmiMobiles.add("2 kishilik ")
  //            redmiMobiles.add("3 kishilik ")
  //            redmiMobiles.add("4 kishilik ")
  //
  //            val micromaxMobiles = ArrayList<String>()
  //            micromaxMobiles.add("1 kishilik")
  //            micromaxMobiles.add("2 kishilik")
  //            micromaxMobiles.add("3 kishilik")
  //            micromaxMobiles.add("4 kishilik")
  //
  //            val appleMobiles = ArrayList<String>()
  //            appleMobiles.add("1 kishilik   ")
  //            appleMobiles.add("2 kishilik   ")
  //            appleMobiles.add("3 kishilik   ")
  //            appleMobiles.add("4 kishilik   ")
  //
  //
  //
  //            listData["Krovatlar"] = redmiMobiles
  //            listData["Shkaftlar"] = micromaxMobiles
  //            listData["Stol-stullar"] = appleMobiles
  //
  //
  //            val samsungMobiles = ArrayList<String>()
  //            samsungMobiles.add("+998922288238")
  //            samsungMobiles.add("+998942479944")
  //            listData["Call Center"] = samsungMobiles
  //            return listData
  //        }
  //
  //
  //    private fun setupExpandableListView() {
  //        val expandableListView = binding.expandableListView
  //        val listData = data
  //        titleList = ArrayList(listData.keys)
  //        expandableList =
  //            ExpandableListviewAdapter(requireContext(), titleList as ArrayList<String>, listData)
  //        expandableListView.setAdapter(expandableList)
  //
  //
  //        expandableListView.setOnGroupExpandListener { groupPosition ->
  //            Toast.makeText(
  //                requireContext(),
  //                (titleList as ArrayList<String>)[groupPosition] + " List Expanded.",
  //                Toast.LENGTH_SHORT
  //            ).show()
  //        }
  //
  //        expandableListView.setOnGroupCollapseListener { groupPosition ->
  //            Toast.makeText(
  //                requireContext(),
  //                (titleList as ArrayList<String>)[groupPosition] + " List Collapsed.",
  //                Toast.LENGTH_SHORT
  //            ).show()
  //        }
  //
  //        expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
  //            Toast.makeText(
  //                requireContext(),
  //                "Clicked: " + (titleList as ArrayList<String>)[groupPosition] + " -> " + listData[(titleList as ArrayList<String>)[groupPosition]]!!.get(
  //                    childPosition
  //                ),
  //                Toast.LENGTH_SHORT
  //            ).show()
  //            false
  //        }
  //    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.save -> {

                return true
            }

        }
        return false
    }
}