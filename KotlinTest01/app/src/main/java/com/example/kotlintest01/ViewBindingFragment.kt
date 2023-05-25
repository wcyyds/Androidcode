package com.example.kotlintest01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewBindingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewBindingFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        //onCreateView()函数中加载的布局，那么理应在与其对应的onDestroyView()函数中对binding变量置空
        //从而保证binding变量的有效生命周期是在onCreateView()函数和onDestroyView()函数之间。
        return _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}