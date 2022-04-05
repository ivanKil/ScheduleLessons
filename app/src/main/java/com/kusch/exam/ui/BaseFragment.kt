package com.kusch.exam.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun initViews() {}

    fun openSkype() {
        if (isSkypeClientInstalled(requireContext()))
        else
            goToMarket(requireActivity())
    }

    fun isSkypeClientInstalled(myContext: Context): Boolean {
        val myPackageMgr: PackageManager = myContext.packageManager
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES)
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
        return true
    }

    fun goToMarket(myContext: Context) {
        val marketUri: Uri = Uri.parse("market://details?id=com.skype.raider")
        val myIntent = Intent(Intent.ACTION_VIEW, marketUri)
        myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        myContext.startActivity(myIntent)
        return
    }
}