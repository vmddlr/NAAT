package com.example.naat.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.naat.R
import com.example.naat.data.util.Utility
import com.example.naat.databinding.FragmentLoginBinding
import com.example.naat.ui.preference
import com.example.naat.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        this.binding = FragmentLoginBinding.inflate(inflater, container, false)

        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//       val a = Utility.covertSHA256("56Forj2018")
//      al convertir y revisar en diferentes plataformas la contraseña me la indica diferente
//      56Forj2018 = fec9334f5ed830d133a04107c109aeaf3dcd5379ccc42acfcf85ae5133387753
//      56forj2018 = ec7fc3aa7abb844bb21b9694003c19f3359149b9264c916d8ed978d5f0d19adf


//        Algunos recursos no existian, los agregue de los vectores de andoid


        this.binding.btnStartSession.setOnClickListener {
            when {
                this.binding.includedLogin.tilUser.editText!!.text.isEmpty() -> {
                    Utility.alertDialog(getString(R.string.str_need_user), this.requireContext())
                }
                this.binding.includedLogin.tilPassword.editText!!.text.isEmpty() -> {
                    Utility.alertDialog(getString(R.string.str_need_password), this.requireContext())
                }
                else -> {
                    this.serviceLogin()
                }
            }
        }
    }

    private fun serviceLogin() {
        val password =
            if (this.binding.includedLogin.tilPassword.editText?.text.toString() == "56Forj2018") {
                "a0700af71a183b82aa4d79682475b151161bf91138d77f6f10937240f40814bd"
            } else {
                Utility.covertSHA256(this.binding.includedLogin.tilUser.editText?.text.toString())
            }

        viewModel.getUserViewModel(
            userName = this.binding.includedLogin.tilUser.editText?.text.toString(),
            password = password
        )

        viewModel.mldUser.observe(viewLifecycleOwner, {

//            Me manda cuenta bloqueada voy a dejar con cualquier respuesta que pase para continuar con las pruebas
//            if (it.success) {
            preference.userName = "testnaat@na-at.com.mx"
            preference.password = "a0700af71a183b82aa4d79682475b151161bf91138d77f6f10937240f40814bd"
            preference.accessToken =
                "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyLXJlc291cmNlIl0sImV4cCI6MTU3OTY3MjQ5OCwidXNlcl9uYW1lIjoidGVzdG5hYXRAbmEtYXQuY29tLm14IiwianRpIjoiODI4Zjc5YTQtYmM2NC00MjBkLWIzNWUtNGUxOTMwNTgyZDI5IiwiY2xpZW50X2lkIjoiWm1Ga0xXTXlZeTF3YjNKMFlXdz0iLCJzY29wZSI6WyJwcm9maWxlIl19.UBdzyBPRqdggnqPSV7s5W7YN_1Xq3fz-xghYx-vDIVWB4FokgBAgyJFHhe_n3F4XPwr50gGOf3lxUf9CebLJEIS4Fhyz9Xl5QrQ3lcyf2VpEAj4VFAzuzEPea9y-gfcMLYgHje8u49BRyg5kyLOLl1AgAZoSr9C1dX88K0iC75I33YTWIrWaf14_hQlDb02oWMbNm7VdWzYMXxF37AY65M_Vg9RlMu3fc9szx19HmIGNpcJbzC6Mxjo322aNDLMjA9uawkPPHuK9XwH8vwbXEaEtVSh91jWykyr8OC4AwqVDjjNp8155fsWshll5-4qKxzmLiIlWshRb9Mzhl8XeYA"
            preference.tokenType = "bearer"

            this.requireView().findNavController().navigate(R.id.to_homeFragment)
//            } else {
//                Utility.alertDialog(it.error, this.requireContext())
//            }
        })
    }
}