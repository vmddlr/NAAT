package com.example.naat.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.naat.R
import com.example.naat.data.util.Utility
import com.example.naat.databinding.FragmentTelephonicRechargeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TelephonicRechargeFragment : Fragment() {

    private lateinit var binding: FragmentTelephonicRechargeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        this.binding = FragmentTelephonicRechargeBinding.inflate(inflater, container, false)

        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.binding.btnContinue.setOnClickListener {
            this.processCellphoneMinutes()
        }
    }

    private fun processCellphoneMinutes() {
        when {
            this.binding.tilNumber.editText!!.text.isEmpty() -> {
                Utility.alertDialog(getString(R.string.str_need_number), this.requireContext())
            }
            this.binding.tilMoney.editText!!.text.isEmpty() -> {
                Utility.alertDialog(getString(R.string.str_need_money), this.requireContext())
            }
            else -> {
                this.alertDialogConfirm()
            }
        }
    }

    private fun alertDialogConfirm(){
        val customLayout = layoutInflater.inflate(R.layout.item_alert_confirm_layout, null)
        val materialAlert = MaterialAlertDialogBuilder(this.requireActivity())
            .setView(customLayout)
            .setCancelable(false)

        val alertDialog = materialAlert.show()

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
        val currentDate = sdf.format(Date())
        val splitDate = currentDate.split(' ')

        val tvClock = customLayout.findViewById<TextView>(R.id.tvTime)
        val tvCalendar = customLayout.findViewById<TextView>(R.id.tvDate)

        tvCalendar.text = splitDate[0]
        tvClock.text = splitDate[1]

        val btnCancel = customLayout.findViewById<Button>(R.id.btnCancel)
        val btnAccept = customLayout.findViewById<Button>(R.id.btnAccept)
        val tvNumber = customLayout.findViewById<TextView>(R.id.tvNumbre)
        val tvAmountMoney = customLayout.findViewById<TextView>(R.id.tvAmountMoney)


        tvNumber.text = this.binding.tilNumber.editText!!.text
        val money = "$ ${this.binding.tilMoney.editText!!.text}"
        tvAmountMoney.text = money

        btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        btnAccept.setOnClickListener {
            alertDialog.dismiss()
            this.alertDialogSuccess()
        }
    }

    private fun alertDialogSuccess(){
        val customLayout = layoutInflater.inflate(R.layout.item_alert_success_layout, null)
        val materialAlert = MaterialAlertDialogBuilder(this.requireActivity())
            .setView(customLayout)
            .setCancelable(false)

        val alertDialog = materialAlert.show()

        val btnAccept = customLayout.findViewById<Button>(R.id.btnAccept)

        btnAccept.setOnClickListener {
            alertDialog.dismiss()
            this.requireView().findNavController().navigate(R.id.to_homeFragment)
        }
    }
}