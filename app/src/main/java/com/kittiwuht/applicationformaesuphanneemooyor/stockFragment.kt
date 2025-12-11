package com.kittiwuht.applicationformaesuphanneemooyor

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class stockFragment : Fragment() {

    private lateinit var editMooStock: EditText
    private lateinit var btnMooConfirm: Button
    private lateinit var editOrangeStock: EditText
    private lateinit var btnOrangeConfirm: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stock, container, false)

        // หา View ทั้งหมด
        editMooStock = view.findViewById(R.id.editMooStock)
        btnMooConfirm = view.findViewById(R.id.btnMooConfirm)
        editOrangeStock = view.findViewById(R.id.editOrangeStock)
        btnOrangeConfirm = view.findViewById(R.id.btnOrangeConfirm)

        // แสดง stock ปัจจุบัน
        updateStockDisplay()

        // ตั้งค่า listener สำหรับปุ่มหมูยอ
        btnMooConfirm.setOnClickListener {
            addStockForProduct("หมูยอ", editMooStock)
        }

        // ตั้งค่า listener สำหรับปุ่มน้ำส้ม
        btnOrangeConfirm.setOnClickListener {
            addStockForProduct("น้ำส้ม", editOrangeStock)
        }

        return view
    }

    private fun addStockForProduct(productName: String, editText: EditText) {
        val inputText = editText.text.toString()

        // ลบข้อความ "ชิ้น" ออกก่อน (ถ้ามี)
        val cleanText = inputText.replace("ชิ้น", "").trim()

        val amount = cleanText.toIntOrNull()

        if (amount == null || amount <= 0) {
            Toast.makeText(requireContext(), "กรุณาใส่จำนวนที่ถูกต้อง", Toast.LENGTH_SHORT).show()
            return
        }

        // เพิ่ม stock
        StockManager.addStock(productName, amount)

        // แสดงข้อความแจ้งเตือน
        val newTotal = StockManager.getCurrentStock(productName)
        Toast.makeText(
            requireContext(),
            "เพิ่ม $productName จำนวน $amount ชิ้น\nคงเหลือ $newTotal ชิ้น",
            Toast.LENGTH_SHORT
        ).show()

        // อัพเดทการแสดงผล
        updateStockDisplay()

        // ล้างข้อมูลใน EditText
        editText.setText("")
    }

    private fun updateStockDisplay() {
        val mooStock = StockManager.getCurrentStock("หมูยอ")
        val orangeStock = StockManager.getCurrentStock("น้ำส้ม")

        editMooStock.hint = "เพิ่มเติม (ปัจจุบัน: $mooStock ชิ้น)"
        editOrangeStock.hint = "เพิ่มเติม (ปัจจุบัน: $orangeStock ชิ้น)"
    }

    override fun onResume() {
        super.onResume()
        updateStockDisplay()
    }
}