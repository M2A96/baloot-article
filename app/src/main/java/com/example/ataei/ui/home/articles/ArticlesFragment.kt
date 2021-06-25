package com.example.ataei.ui.home.articles

import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataei.R
import com.example.ataei.databinding.ArticleItemBinding
import com.example.ataei.databinding.FragmentHomeListBinding
import com.example.ataei.ui.base.BaseFragment
import com.example.ataei.ui.base.ViewModelScope
import com.example.ataei.ui.base.adapter.SingleLayoutAdapter
import com.example.ataei.util.EndlessRecyclerViewScrollListener
import com.example.ataei.util.extentions.observeSafe

class ArticlesFragment : BaseFragment<ArticlesViewModel, FragmentHomeListBinding>() {

    private val TAG = ArticlesFragment::class.java.simpleName
    override val layoutId: Int = R.layout.fragment_home_list
    override val viewModel: ArticlesViewModel by getLazyViewModel(ViewModelScope.ACTIVITY)

    private val scrollListener by lazy {
        object : EndlessRecyclerViewScrollListener() {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.loadMore()
            }

        }
    }

    override fun onViewInitialized(binding: FragmentHomeListBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel
        binding.adapter = SingleLayoutAdapter<ArticleItem, ArticleItemBinding>(
            layoutId = R.layout.article_item,
            onItemClicked = viewModel::onItemClicked
        )

        scrollListener.layoutManager = binding.articlesRecyclerView.layoutManager

        binding.articlesRecyclerView.addOnScrollListener(scrollListener)

        viewModel.articles.observeSafe(viewLifecycleOwner) {
            binding.adapter?.addNewItems(it)
            Log.d(TAG, "onViewInitialized: $it")
        }

        viewModel.loadingLiveData.observeSafe(viewLifecycleOwner) {
            scrollListener.loading = it
            binding.progressBar.isVisible = it
        }
    }
}