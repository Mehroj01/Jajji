package com.example.jajji.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jajji.databinding.ItemViewBinding
import com.example.jajji.model.Furniture

class SecondAdapter(
    private val dataSet: MutableList<Furniture>,
    private val listener: OnImageClick
) :
    RecyclerView.Adapter<SecondAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            // Define click listener for the ViewHolder's View.
            binding.apply {

            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding =
            ItemViewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.binding.costs.text = dataSet[position].costs
        holder.binding.img.setImageResource(dataSet[position].image)
        holder.binding.name.text = dataSet[position].name
        holder.itemView.setOnClickListener {
            listener.click(dataSet[position])
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
    interface OnImageClick {
        fun click(furniture: Furniture)
    }

}