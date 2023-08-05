package com.example.secondproject.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.secondproject.PostRequest_Interface
import com.example.secondproject.Translation1
import com.example.secondproject.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        homeViewModel.getFictions();

        val textViewTest: TextView = binding.textViewtest

        homeViewModel.banner.observe(viewLifecycleOwner, Observer {
            textViewTest.text = it.toString()
        })

//        Log.d("main111", "444441")
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://ifanyi.iciba.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val postrequestInterface = retrofit.create(PostRequest_Interface::class.java)
//        val map:MutableMap<String, Any> = HashMap()
//        map["from"] = "zh"
//        map["to"] = "en"
//        map["q"] = "你好世界"
//        postrequestInterface.getCall(map).enqueue(object : Callback<Translation1> {
//            override fun onResponse(call: Call<Translation1>, response: Response<Translation1>) {
//                val translation = response.body()
//                if(translation != null){
//                    Log.d("main111", "99999" + translation.status)
//                    Log.d("main111", "99999" + translation.content.out)
//                }
//
//            }
//
//            override fun onFailure(call: Call<Translation1>, t: Throwable) {
//                Log.d("main111", "55555" + t.toString())
//                Log.d("main111", "55555" + t.message)
//            }
//
//        })


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}