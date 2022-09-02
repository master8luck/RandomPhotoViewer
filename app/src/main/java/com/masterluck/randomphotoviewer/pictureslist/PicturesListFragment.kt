package com.masterluck.randomphotoviewer.pictureslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.masterluck.randomphotoviewer.NetworkUtils
import com.masterluck.randomphotoviewer.databinding.FragmentPicturesListBinding

class PicturesListFragment : Fragment() {

    private var _binding: FragmentPicturesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PicturesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPicturesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PicturesListAdapter(requireContext(), this::onPhotoClicked)
        binding.run {
            rvPhotos.adapter = adapter
            viewModel.photos.observe(viewLifecycleOwner) { photos ->
                if (photos.isEmpty()) {
                    if (!NetworkUtils.isInternetAvailable(requireContext()))
                        Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(context, "Can't fetch photos", Toast.LENGTH_LONG).show()
                }
                adapter.setupPhotos(photos)
            }
            viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                rvPhotos.isVisible = !isLoading
                pbLoading.isVisible = isLoading
            }
            rvPhotos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) {
                        viewModel.fetchMorePhoto()
                    }
                }
            })
        }
    }

    private fun onPhotoClicked(link: String) {
        findNavController().navigate(PicturesListFragmentDirections.actionPicturesListFragmentToSinglePictureFragment(link))
    }
}