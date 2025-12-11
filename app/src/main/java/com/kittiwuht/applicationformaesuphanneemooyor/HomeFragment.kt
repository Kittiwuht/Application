package com.kittiwuht.applicationformaesuphanneemooyor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class HomeFragment : Fragment() {

    private lateinit var stockLogsContainer: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        stockLogsContainer = view.findViewById(R.id.stockLogsContainer)

        return view
    }

    override fun onResume() {
        super.onResume()
        updateStockLogs()
    }

    private fun updateStockLogs() {
        stockLogsContainer.removeAllViews()

        val logs = StockManager.getStockLogs()

        if (logs.isEmpty()) {
            // แสดงข้อความเมื่อยังไม่มี log
            val emptyView = TextView(requireContext()).apply {
                text = "ยังไม่มีการเพิ่ม stock"
                textSize = 14f
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                setPadding(16, 16, 16, 16)
            }
            stockLogsContainer.addView(emptyView)
            return
        }

        // แสดง log แต่ละรายการ
        logs.take(5).forEach { log ->
            val logCard = createLogCard(log)
            stockLogsContainer.addView(logCard)
        }
    }

    private fun createLogCard(log: StockLog): CardView {
        val cardView = CardView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 16)
            }
            radius = 12f
            cardElevation = 4f
            setCardBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.white))
        }

        val contentLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(24, 16, 24, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // ข้อมูลสินค้า
        val infoLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
        }

        val productNameText = TextView(requireContext()).apply {
            text = log.productName
            textSize = 16f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            setTypeface(null, android.graphics.Typeface.BOLD)
        }

        val detailLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
        }

        val addedText = TextView(requireContext()).apply {
            text = "เพิ่ม: ${log.addedAmount} ชิ้น"
            textSize = 14f
            setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark))
            setTypeface(null, android.graphics.Typeface.BOLD)
        }

        val separatorText = TextView(requireContext()).apply {
            text = " / "
            textSize = 14f
            setPadding(8, 0, 8, 0)
        }

        val remainingText = TextView(requireContext()).apply {
            text = "คงเหลือ: ${log.totalStock} ชิ้น"
            textSize = 14f
            setTextColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray))
        }

        val timeText = TextView(requireContext()).apply {
            text = log.timestamp
            textSize = 12f
            setTextColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray))
        }

        detailLayout.addView(addedText)
        detailLayout.addView(separatorText)
        detailLayout.addView(remainingText)

        infoLayout.addView(productNameText)
        infoLayout.addView(detailLayout)
        infoLayout.addView(timeText)

        // Status badge
        val statusBadge = TextView(requireContext()).apply {
            text = "เพิ่มแล้ว"
            textSize = 12f
            setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark))
            setTypeface(null, android.graphics.Typeface.BOLD)
            background = ContextCompat.getDrawable(requireContext(), R.drawable.b1)
            setPadding(24, 12, 24, 12)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        contentLayout.addView(infoLayout)
        contentLayout.addView(statusBadge)
        cardView.addView(contentLayout)

        return cardView
    }
}