package com.masterluck.randomphotoviewer.singlepicture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.masterluck.randomphotoviewer.databinding.FragmentSinglePictureBinding

class SinglePictureFragment : Fragment() {

    private var _binding: FragmentSinglePictureBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<SinglePictureFragmentArgs>()

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSinglePictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext()).load(args.link).into(binding.ivPhoto)
    }
}