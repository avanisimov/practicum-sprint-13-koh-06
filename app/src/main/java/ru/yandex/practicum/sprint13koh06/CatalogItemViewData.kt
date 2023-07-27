package ru.yandex.practicum.sprint13koh06

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.yandex.practicum.sprint13koh06.databinding.VCatalogItemBinding

data class CatalogItemViewData(
    val item: CatalogItem,
    val count: Int?,
){
    val id = item.id
}

class CatalogItemViewHolder(
    private val parent: ViewGroup,
    val binding: VCatalogItemBinding = VCatalogItemBinding.inflate(
        LayoutInflater.from(
            parent.context
        ), parent, false
    )
) : RecyclerView.ViewHolder(
    binding.root
) {

    fun bind(viewData: CatalogItemViewData) {

        Glide
            .with(binding.root.context)
            .load(viewData.item.imageUrl)
            .centerCrop()
            .into(binding.image)
        binding.title.text = viewData.item.name

        val currencyChar = binding.root.context.getString(R.string.currency)
        binding.price.text = "${viewData.item.price / 100} ${currencyChar}/${viewData.item.unit}"

        if (viewData.count != null) {
            binding.addToCart.visibility = View.GONE
            binding.countContainer.visibility = View.VISIBLE
            binding.count.text = viewData.count.toString()
        }
    }

}