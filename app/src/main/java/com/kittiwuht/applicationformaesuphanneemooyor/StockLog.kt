package com.kittiwuht.applicationformaesuphanneemooyor

import java.text.SimpleDateFormat
import java.util.*

// Data class สำหรับเก็บข้อมูล log การเพิ่ม stock
data class StockLog(
    val productName: String,
    val addedAmount: Int,
    val totalStock: Int,
    val timestamp: String = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("th", "TH")).format(Date())
)

// Object สำหรับจัดการข้อมูล stock แบบ global
object StockManager {
    private val stockLogs = mutableListOf<StockLog>()

    // Stock ปัจจุบันของแต่ละสินค้า
    private val currentStock = mutableMapOf(
        "หมูยอ" to  0,
        "น้ำส้ม" to 0
    )

    fun addStock(productName: String, amount: Int) {
        val current = currentStock[productName] ?: 0
        val newTotal = current + amount
        currentStock[productName] = newTotal

        stockLogs.add(0, StockLog(productName, amount, newTotal))
    }

    fun getCurrentStock(productName: String): Int {
        return currentStock[productName] ?: 0
    }

    fun getStockLogs(): List<StockLog> {
        return stockLogs.toList()
    }

    fun clearOldLogs() {
        if (stockLogs.size > 10) {
            stockLogs.subList(10, stockLogs.size).clear()
        }
    }
}