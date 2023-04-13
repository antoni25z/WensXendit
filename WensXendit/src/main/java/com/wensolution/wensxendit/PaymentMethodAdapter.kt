package com.wensolution.wensxendit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wensolution.wensxendit.databinding.ItemPaymentMethodBinding

class PaymentMethodAdapter(
    private val list: Array<String>,
    private val actives: Array<String>,
    private val price: Int,
    private val mode: String,
    val click: PaymentClick
) :
    RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentMethodAdapter.ViewHolder {
        val binding =
            ItemPaymentMethodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentMethodAdapter.ViewHolder, position: Int) {
        val payment = list[position]

        when (payment) {
            PaymentMethod.DANA -> {
                val fee = 0.015 * price
                val subtotal = price + fee

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.dana)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)
                }
            }
            PaymentMethod.LINKAJA -> {
                val fee = 0.027 * price
                val subtotal = price + fee

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.linkaja)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)
                }
            }
            PaymentMethod.OVO -> {
                val fee = 0.0273 * price
                val subtotal = price + fee

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.ovo)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)
                }
            }
            PaymentMethod.ASTRAPAY -> {
                val fee = 0.015 * price
                val subtotal = price + fee

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.astrapay)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)
                }
            }
            PaymentMethod.SHOPEEPAY -> {
                val fee = 0.02 * price
                val subtotal = price + fee

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.astrapay)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)
                }
            }
            PaymentMethod.BCA -> {
                val subtotal = price + 4000

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.bca)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())
                }
            }
            PaymentMethod.BJB -> {
                val subtotal = price + 4000

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.bjb)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())
                }
            }
            PaymentMethod.BNI -> {
                val subtotal = price + 4000

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.bni)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())
                }
            }
            PaymentMethod.BRI -> {
                val subtotal = price + 4000

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.bri)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())
                }
            }
            PaymentMethod.BSI -> {
                val subtotal = price + 4000

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.bsi)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())
                }
            }
            PaymentMethod.BSS -> {
                val subtotal = price + 4000

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.bss)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())
                }
            }
            PaymentMethod.MANDIRI -> {
                val subtotal = price + 4000

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.mandiri)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())
                }
            }
            PaymentMethod.PERMATA -> {
                val subtotal = price + 4000

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.permata)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())
                }
            }
            PaymentMethod.QRIS -> {
                val fee = 0.007 * price
                val subtotal = price + fee

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.qris)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)
                }
            }
            PaymentMethod.CARD -> {
                val fee = 0.029 * price
                val subtotal = price + fee + 2000

                if (actives.contains(payment)) {
                    holder.binding.paymentImg.setImageResource(R.drawable.creditcard)
                    holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)
                }
            }
        }
        holder.itemView.setOnClickListener {
            var subtotal = 0.0
            when (payment) {
                PaymentMethod.DANA -> {
                    val fee = 0.015 * price
                    subtotal = price + fee
                }
                PaymentMethod.LINKAJA -> {
                    val fee = 0.027 * price
                    subtotal = price + fee
                }
                PaymentMethod.OVO -> {
                    val fee = 0.0273 * price
                    subtotal = price + fee
                }
                PaymentMethod.ASTRAPAY -> {
                    val fee = 0.015 * price
                    subtotal = price + fee
                }
                PaymentMethod.SHOPEEPAY -> {
                    val fee = 0.02 * price
                    subtotal = price + fee

                }
                PaymentMethod.BCA -> {
                    subtotal = price + 4000.0

                }
                PaymentMethod.BJB -> {
                    subtotal = price + 4000.0
                }
                PaymentMethod.BNI -> {
                    subtotal = price + 4000.0
                }
                PaymentMethod.BRI -> {
                    subtotal = price + 4000.0
                }
                PaymentMethod.BSI -> {
                    subtotal = price + 4000.0
                }
                PaymentMethod.BSS -> {
                    subtotal = price + 4000.0
                }
                PaymentMethod.MANDIRI -> {
                    subtotal = price + 4000.0
                }
                PaymentMethod.PERMATA -> {
                    subtotal = price + 4000.0
                }
                PaymentMethod.QRIS -> {
                    val fee = 0.007 * price
                    subtotal = price + fee
                }
                PaymentMethod.CARD -> {
                    val fee = 0.029 * price
                    subtotal = price + fee + 2000
                }
            }
            click.onClick(payment, mode, subtotal)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: ItemPaymentMethodBinding) :
        RecyclerView.ViewHolder(binding.root)
}