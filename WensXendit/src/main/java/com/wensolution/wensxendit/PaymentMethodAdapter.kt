package com.wensolution.wensxendit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.wensolution.wensxendit.databinding.ItemPaymentMethodBinding

class PaymentMethodAdapter(
    private val list: ArrayList<String>,
    private val price: Long,
    private val mode: String,
    val click: PaymentClick,
    val ovoClick: OvoClick
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

                holder.binding.paymentImg.setImageResource(R.drawable.dana)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)

            }
            PaymentMethod.LINKAJA -> {
                val fee = 0.027 * price
                val subtotal = price + fee

                holder.binding.paymentImg.setImageResource(R.drawable.linkaja)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)

            }
            PaymentMethod.OVO -> {
                val fee = 0.0273 * price
                val subtotal = price + fee

                holder.binding.paymentImg.setImageResource(R.drawable.ovo)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)

            }
            PaymentMethod.ASTRAPAY -> {
                val fee = 0.015 * price
                val subtotal = price + fee

                holder.binding.paymentImg.setImageResource(R.drawable.astrapay)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)

            }
            PaymentMethod.SHOPEEPAY -> {
                val fee = 0.02 * price
                val subtotal = price + fee

                holder.binding.paymentImg.setImageResource(R.drawable.shopee)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)

            }
            PaymentMethod.BCA -> {
                val subtotal = price + 4000

                holder.binding.paymentImg.setImageResource(R.drawable.bca)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())

            }
            PaymentMethod.BJB -> {
                val subtotal = price + 4000

                holder.binding.paymentImg.setImageResource(R.drawable.bjb)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())

            }
            PaymentMethod.BNI -> {
                val subtotal = price + 4000

                holder.binding.paymentImg.setImageResource(R.drawable.bni)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())

            }
            PaymentMethod.BRI -> {
                val subtotal = price + 4000

                holder.binding.paymentImg.setImageResource(R.drawable.bri)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())

            }
            PaymentMethod.BSI -> {
                val subtotal = price + 4000

                holder.binding.paymentImg.setImageResource(R.drawable.bsi)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())

            }
            PaymentMethod.BSS -> {
                val subtotal = price + 4000

                holder.binding.paymentImg.setImageResource(R.drawable.bss)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())

            }
            PaymentMethod.MANDIRI -> {
                val subtotal = price + 4000

                holder.binding.paymentImg.setImageResource(R.drawable.mandiri)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())

            }
            PaymentMethod.PERMATA -> {
                val subtotal = price + 4000

                holder.binding.paymentImg.setImageResource(R.drawable.permata)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal.toDouble())

            }
            PaymentMethod.QRIS -> {
                val fee = 0.007 * price
                val subtotal = price + fee

                holder.binding.paymentImg.setImageResource(R.drawable.qris)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)

            }
            PaymentMethod.CARD -> {
                val fee = 0.029 * price
                val subtotal = price + fee + 2000

                holder.binding.paymentImg.setImageResource(R.drawable.creditcard)
                holder.binding.priceTxt.text = convertCurrencyFormat(subtotal)

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
            if (payment == "OVO") {
                holder.binding.phoneCard.visibility = View.VISIBLE
                holder.binding.nextOvoBtn.visibility = View.VISIBLE
            } else {
                click.onClick(payment, mode, subtotal)
            }
        }

        holder.binding.nextOvoBtn.setOnClickListener {
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
            val phoneNumber = holder.binding.phoneNumberEdt.text.toString()
            if (phoneNumber.isNotBlank() && phoneNumber[0].digitToInt() != 0) {
                ovoClick.onOvoClick(payment, subtotal, phoneNumber)
            } else {
                Toast.makeText(holder.itemView.context, "Periksa kembali nomor telepon anda", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: ItemPaymentMethodBinding) :
        RecyclerView.ViewHolder(binding.root)
}