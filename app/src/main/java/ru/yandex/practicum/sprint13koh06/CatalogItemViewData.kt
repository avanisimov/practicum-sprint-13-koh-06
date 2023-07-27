package ru.yandex.practicum.sprint13koh06

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.yandex.practicum.sprint13koh06.databinding.VCatalogItemBinding
import java.text.NumberFormat
import java.util.*

data class CatalogItemViewData(
    val item: CatalogItem,
    val count: Int?,
){
    val id = item.id
}

class CatalogItemViewHolder(
    parent: ViewGroup,
    val binding: VCatalogItemBinding = VCatalogItemBinding.inflate(
        LayoutInflater.from(
            parent.context
        ), parent, false
    )
) : RecyclerView.ViewHolder(
    binding.root
) {

    fun bind(viewData: CatalogItemViewData) {
        binding.root

        Glide
            .with(binding.root.context)
            .load(viewData.item.imageUrl)
            .centerCrop()
            .into(binding.image)
        binding.title.text = viewData.item.name
        val format = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("RU"))
        val priceF = format.format(viewData.item.price.toDouble()/100)
        val priceS = priceF.substring(0, priceF.length-2)
        binding.price.text = "$priceS/${viewData.item.unit}"

        if (viewData.count != null) {
            binding.addToCart.visibility = View.GONE
            binding.countContainer.visibility = View.VISIBLE
            binding.count.text = viewData.count.toString()
        }
    }

}