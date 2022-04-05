package com.kusch.exam.ui.dashboard

import com.kusch.exam.databinding.FragmentDashboardBinding
import com.kusch.exam.ui.BaseFragment
import com.kusch.exam.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleFragment : BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {
    private val adapter: ScheduleAdapter by lazy { ScheduleAdapter { openSkype() } }
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun initViews() {
        binding.markersRecyclerview.adapter = adapter
        homeViewModel.ldSchedule.observe(viewLifecycleOwner) { adapter.setData(it) }
        homeViewModel.requestSchedule()
    }
}