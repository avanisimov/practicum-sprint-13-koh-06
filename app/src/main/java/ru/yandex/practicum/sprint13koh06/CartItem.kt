package ru.yandex.practicum.sprint13koh06

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.yandex.practicum.sprint13koh06.databinding.VCartItemBinding
import java.text.NumberFormat
import java.util.Locale

data class CartItem(
    val id: String,
    val catalogItem: CatalogItem,
    val count: Int,
) {
    val sum: Int
        get() = catalogItem.price.times(count).toInt()
}


class CartItemViewHolder(
    parent: ViewGroup,
    val binding: VCartItemBinding = VCartItemBinding.inflate(
        LayoutInflater.from(
            parent.context
        ), parent, false
    )
) : RecyclerView.ViewHolder(
    binding.root
) {

    fun bind(item: CartItem) {

        Glide
            .with(binding.root.context)
            .load(item.catalogItem.imageUrl)
            .into(binding.image)
        binding.title.text = item.catalogItem.name

        binding.count.text = item.count.toString()
        val format = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("RU"))
        val priceF = format.format((item.catalogItem.price.toDouble() * item.count)/100)
        val priceS = priceF.substring(0, priceF.length-2)
        val currencyChar = binding.root.context.getString(R.string.currency)
        binding.totalCost.text = "$priceS $currencyChar"
    }

}