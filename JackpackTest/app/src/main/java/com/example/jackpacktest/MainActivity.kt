package com.example.jackpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jackpacktest.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("111nihao","123456")



//        sp = getPreferences(Context.MODE_PRIVATE)
//        val countReserved = sp.getInt("count_reserved", 0)
//
//        viewModel = ViewModelProvider(this,
//            MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
//
//        binding.plusOneBtn.setOnClickListener {
//            viewModel.plusOne()
//        }
//
//        binding.clearBtn.setOnClickListener {
//            viewModel.clear()
//        }
//        if(true){
//            viewModel.counter.observe(this, Observer{count->
//                binding.infoText.text = count.toString()
//            })
//        }else{
//            viewModel.counter.observe(this){count->
//                binding.infoText.text = count.toString()
//            }
//        }
//
//
//        val userDao = AppDatabase.getDatabase(this).userDao()
//        val user1 = User("tom","brady", 40)
//        val user2 = User("tom","hanks", 63)
//        binding.addDataBtn.setOnClickListener {
//            thread {
//                user1.id = userDao.insertUser(user1)
//                user2.id = userDao.insertUser(user2)
//            }
//        }
//        binding.updataDataBtn.setOnClickListener {
//            thread {
//                user1.age = 42
//                userDao.updataeUser(user1)
//            }
//        }
//        binding.deletaDataBtn.setOnClickListener {
//            thread {
//                userDao.deleteUserByLastName("hanks")
//            }
//        }
//        binding.queryDataBtn.setOnClickListener {
//            thread {
//                for(user in userDao.loadAllUsers()){
//                    Log.d("Mainactivity", user.toString())
//                }
//            }
//        }




    }


    override fun onPause() {
        super.onPause()
//        sp.edit{
//            putInt("count_reserved", viewModel.counter.value ?: 0)
//        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("111nihao","onStop")

        lifecycle.addObserver(MyObserver())
    }

}