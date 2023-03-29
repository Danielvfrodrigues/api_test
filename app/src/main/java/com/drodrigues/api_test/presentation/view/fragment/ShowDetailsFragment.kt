package com.drodrigues.api_test.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.drodrigues.api_test.databinding.FragmentShowDetailsBinding
import com.drodrigues.api_test.domain.entity.SeasonEntity
import com.drodrigues.api_test.presentation.view.listener.SeasonCardClickListener
import com.drodrigues.api_test.presentation.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ShowDetailsFragment : Fragment(), SeasonCardClickListener {

    private lateinit var binding: FragmentShowDetailsBinding
    private val mainViewModel: MainViewModel by viewModel()
    private val args: ShowDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentShowDetailsBinding.inflate(layoutInflater)

        mainViewModel.getShowByIdLiveData.observe(viewLifecycleOwner) { show ->
            Picasso.get().load(show.imageEntity.medium).into(binding.showCover)
            binding.showName.text = show.name
            binding.showGenres.text = show.genres.toString()
            binding.showSummary.text = HtmlCompat.fromHtml(show.summary, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }

        lifecycleScope.launch {
            mainViewModel.getShowById(args.showId)
            mainViewModel.getSeasonListByShowId(args.showId)
        }

        return binding.root
    }

    override fun onClick(seasonEntity: SeasonEntity) {
        Toast.makeText(context,"====== CLICK =====", Toast.LENGTH_LONG).show()
    }
}