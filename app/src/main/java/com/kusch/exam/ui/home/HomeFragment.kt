package com.kusch.exam.ui.home

import com.kusch.exam.databinding.FragmentHomeBinding
import com.kusch.exam.model.data.Lesson
import com.kusch.exam.ui.BaseFragment
import com.kusch.exam.ui.home.homeworks.HomeworksAdapter
import com.kusch.exam.ui.home.lessons.LessonsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalTime


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel by viewModel<HomeViewModel>()
    private val adapter: LessonsAdapter by lazy { LessonsAdapter { openSkype() } }

    private val adapterHomeworks: HomeworksAdapter by lazy { HomeworksAdapter { } }

    override fun initViews() {
        initTimer()

        binding.lessonsRecyclerview.adapter = adapter
        homeViewModel.ldLessons.observe(viewLifecycleOwner) {
            adapter.setData(it)
            scrollLessons(it)
        }
        homeViewModel.requestLessons()

        binding.homeworksRecyclerview.adapter = adapterHomeworks
        homeViewModel.ldHomeworks.observe(viewLifecycleOwner) { adapterHomeworks.setData(it) }
        homeViewModel.requestHomeworks()
    }

    private fun initTimer() {
        homeViewModel.init()
        homeViewModel.initTimer()
        homeViewModel.ldTime.observe(viewLifecycleOwner) {
            System.out.println(it.get(2))
            binding.digit1.text = it[0][0].toString()
            binding.digit2.text = it[0][1].toString()
            binding.digit3.text = it[1][0].toString()
            binding.digit4.text = it[1][1].toString()
            binding.digit5.text = it[2][0].toString()
            binding.digit6.text = it[2][1].toString()
        }
    }

    private fun scrollLessons(list: List<Lesson>) {
        val curTime = LocalTime.now()
        for (i in 0 until list.size) {
            if ((list[i].time < curTime && curTime < list[i].time.plusMinutes(list[i].duration))
                || (list[i].time > curTime)
            ) {
                binding.lessonsRecyclerview.scrollToPosition(i)
                return
            }
        }
    }
}