package com.example.ataei.ui.home.list

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.ataei.R
import com.example.ataei.databinding.FragmentHomeListBinding
import com.example.ataei.databinding.SuggestedGameItemBinding
import com.example.ataei.ui.base.BaseFragment
import com.example.ataei.ui.base.ViewModelScope
import com.example.ataei.ui.base.adapter.SingleLayoutAdapter
import com.example.ataei.ui.custom.MagicalImageView
import com.example.ataei.util.extentions.observeSafe

class HomeListFragment : BaseFragment<HomeListViewModel, FragmentHomeListBinding>() {

    override val layoutId: Int = R.layout.fragment_home_list
    override val viewModel: HomeListViewModel by getLazyViewModel(ViewModelScope.ACTIVITY)

    override fun onViewInitialized(binding: FragmentHomeListBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel
        binding.adapter = SingleLayoutAdapter<GameItem, SuggestedGameItemBinding>(
            layoutId = R.layout.suggested_game_item,
            onItemClicked = viewModel::onItemClicked
        )

        viewModel.games.observeSafe(viewLifecycleOwner) {
            binding.adapter?.swapItems(it)
        }

    }


}