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
import com.task.reddit.databinding.FragmentMediaBottomSheetBinding
import com.task.reddit.ui.screens.base.BaseBottomSheetFragment
import com.task.reddit.util.ImageHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaBottomSheetFragment : BaseBottomSheetFragment<FragmentMediaBottomSheetBinding>() {

    override fun getViewBinding(): FragmentMediaBottomSheetBinding =
        FragmentMediaBottomSheetBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageData =
            arguments?.let { MediaBottomSheetFragmentArgs.fromBundle(it).imageModel }

        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1
        )

        Glide
            .with(requireContext())
            .load(imageData?.url)
            .into(binding.previewImage)

        binding.downloadButton.setOnClickListener {
            if (checkPermissionGranted()) {
                saveImageToStorage(imageData!!.title)
                navigator.goBack()
            }
        }
    }

    private fun saveImageToStorage(title: String) {
        val bitmap = binding.previewImage.drawable.toBitmap()
        val path = ImageHelper.saveImage(bitmap, title)
        Toast.makeText(requireContext(), "Saved to $path", Toast.LENGTH_LONG).show()
    }

    private fun checkPermissionGranted(): Boolean {
        return if (permissionState() == PackageManager.PERMISSION_GRANTED) true else {
            Toast.makeText(requireContext(), "Need permission to save", Toast.LENGTH_SHORT).show()
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