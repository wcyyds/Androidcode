package com.example.secondproject.ui.study

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.secondproject.databinding.FragmentWebViewBinding

//webview的简单的演示碎片
class WebViewFragment(val url: String) : Fragment() {

    private var _binding: FragmentWebViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val webView: WebView = binding.articlewebview
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)


        // Inflate the layout for this fragment
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}