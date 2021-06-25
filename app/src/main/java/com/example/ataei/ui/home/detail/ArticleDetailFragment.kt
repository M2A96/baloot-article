package com.example.ataei.ui.home.detail

import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.ataei.R
import com.example.ataei.databinding.FragmentArticleDetailBinding
import com.example.ataei.ui.base.BaseFragment
import com.example.ataei.ui.base.ViewModelScope
import com.example.ataei.ui.home.articles.ArticlesViewModel
import com.example.ataei.util.extentions.observeSafe

class ArticleDetailFragment :
    BaseFragment<ArticlesViewModel, FragmentArticleDetailBinding>() {
    override val viewModel: ArticlesViewModel by getLazyViewModel(ViewModelScope.ACTIVITY)
    override val layoutId: Int
        get() = R.layout.fragment_article_detail

    private lateinit var webView: WebView

    override fun onViewInitialized(binding: FragmentArticleDetailBinding) {
        super.onViewInitialized(binding)
        webView = binding.webViewArticles
        initWebView()

        viewModel.selectedArticle.observeSafe(viewLifecycleOwner) {
            binding.webViewArticles.loadUrl(
                it.articleUrl
            )
        }
    }

    private fun initWebView() {
        binding.webViewArticles.setInitialScale(1)
        binding.webViewArticles.settings.javaScriptEnabled = true
        binding.webViewArticles.settings.loadWithOverviewMode = true
        binding.webViewArticles.settings.useWideViewPort = true
        binding.webViewArticles.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        binding.webViewArticles.settings.builtInZoomControls = false
        binding.webViewArticles.settings.setSupportZoom(false)
        binding.webViewArticles.settings.displayZoomControls = false
        binding.webViewArticles.isHorizontalScrollBarEnabled = false
        binding.webViewArticles.isVerticalScrollBarEnabled = true
        binding.webViewArticles.setOnLongClickListener { true }
        binding.webViewArticles.isLongClickable = false
        binding.webViewArticles.isHapticFeedbackEnabled = false
        binding.webViewArticles.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                progressContainer?.visibility = View.GONE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
//                progressContainer?.visibility = View.GONE

            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler?.proceed()
            }
        }
    }

    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }
}