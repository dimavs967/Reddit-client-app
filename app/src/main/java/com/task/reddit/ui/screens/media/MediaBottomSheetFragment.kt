package com.task.reddit.ui.screens.media

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.task.reddit.R
import com.task.reddit.databinding.FragmentMediaBottomSheetBinding
import com.task.reddit.model.ImageModel
import com.task.reddit.ui.screens.base.BaseBottomSheetFragment
import com.task.reddit.util.ImageDownloader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaBottomSheetFragment : BaseBottomSheetFragment<FragmentMediaBottomSheetBinding>() {

    private var imageData: ImageModel? = null

    override fun getViewBinding(): FragmentMediaBottomSheetBinding =
        FragmentMediaBottomSheetBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageData =
            arguments?.let { MediaBottomSheetFragmentArgs.fromBundle(it).imageModel }

        loadImage()
        setButton()
        requestPermission()
    }

    private fun setButton() {
        binding.downloadButton.setOnClickListener {
            if (checkPermissionGranted()) {
                imageData?.title?.let { t -> saveImageToStorage(t) }
                navigator.goBack()
            }
        }
    }

    private fun loadImage() {
        Glide
            .with(requireContext())
            .load(imageData?.url)
            .into(binding.previewImage)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
    }

    private fun saveImageToStorage(title: String) {
        val bitmap = binding.previewImage.drawable.toBitmap()
        val path = ImageDownloader().saveImage(bitmap, title)
        Toast.makeText(requireContext(), "Saved to $path", Toast.LENGTH_LONG).show()
    }

    private fun checkPermissionGranted(): Boolean {
        return if (permissionState() == PackageManager.PERMISSION_GRANTED) true else {
            Toast.makeText(requireContext(), R.string.need_permission, Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun permissionState(): Int {
        return ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

}