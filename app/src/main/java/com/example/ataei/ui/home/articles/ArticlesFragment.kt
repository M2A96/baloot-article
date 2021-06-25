package com.example.ataei.ui.home.articles

import android.util.Log
import com.example.ataei.R
import com.example.ataei.databinding.ArticleItemBinding
import com.example.ataei.databinding.FragmentHomeListBinding
import com.example.ataei.ui.base.BaseFragment
import com.example.ataei.ui.base.ViewModelScope
import com.example.ataei.ui.base.adapter.SingleLayoutAdapter
import com.example.ataei.util.extentions.observeSafe

class ArticlesFragment : BaseFragment<ArticlesViewModel, FragmentHomeListBinding>() {

    private val TAG = ArticlesFragment::class.java.simpleName
    override val layoutId: Int = R.layout.fragment_home_list
    override val viewModel: ArticlesViewModel by getLazyViewModel(ViewModelScope.ACTIVITY)

    override fun onViewInitialized(binding: FragmentHomeListBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel
        binding.adapter = SingleLayoutAdapter<ArticleItem, ArticleItemBinding>(
            layoutId = R.layout.article_item,
            onItemClicked = viewModel::onItemClicked
        )



        viewModel.articles.observeSafe(viewLifecycleOwner) {
            binding.adapter?.swapItems(it)
            Log.d(TAG, "onViewInitialized: $it")
        }
    }
}