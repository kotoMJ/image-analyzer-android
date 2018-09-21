package com.example.android.analyzer.text.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.annotation.RequiresPermission
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.android.analyzer.core.arch.BaseFragment
import com.example.android.analyzer.core.ktools.CameraType
import com.example.android.analyzer.core.ktools.FragmentPermissionManager
import com.example.android.analyzer.core.ktools.SinglePermissionRequest
import com.example.android.analyzer.core.ktools.vmb
import com.example.android.analyzer.text.R
import com.example.android.analyzer.text.databinding.FragmentTextSourceDefinitionBinding
import timber.log.Timber

interface TextSourceDefinitionView {
	fun onLocalFilesClick()
	fun onTakePhotoClick()
	fun onCaptureVideoClick()
	fun onAnalyzeAction()
}

class TextSourceDefinitionFragment : BaseFragment(), TextSourceDefinitionView {

	companion object {
		const val REQUEST_UPLOAD_LOCAL_FILE = 111
		const val REQUEST_PHOTO_CAPTURE = 222
		const val REQUEST_VIDEO_CAPTURE = 333
	}

	private val vmb by vmb<TextSourceDefinitionViewModel, FragmentTextSourceDefinitionBinding>(R.layout.fragment_text_source_definition) { findViewModel(TextSourceDefinitionViewModel::class.java) }

	val permissionManager by lazy { FragmentPermissionManager(this) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		vmb.binding.view = this
		vmb.binding.viewModel = findViewModel(TextSourceDefinitionViewModel::class.java)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return vmb.rootView
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		vmb.binding.setLifecycleOwner(viewLifecycleOwner)
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		permissionManager.onPermissionResult(requestCode, permissions, grantResults)
	}

	@SuppressLint("MissingPermission")
	override fun onLocalFilesClick() {

		permissionManager.requestPermission(SinglePermissionRequest(Manifest.permission.WRITE_EXTERNAL_STORAGE, grantedCallback = {
			startLocalFilesPicker()

		}, deniedCallback = {
			showSnackbar(vmb.binding.root, R.string.text_source_definition_permission_write_external_storage_needed)
		}))
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == REQUEST_PHOTO_CAPTURE) {
				// We don't know why, but seems that Android version 6 gives [data] as null, and higher versions doesn't
				//uploadPhotoFile()
			} else if (data != null) {
				when (requestCode) {
					REQUEST_UPLOAD_LOCAL_FILE -> uploadLocalFiles(data)
					REQUEST_VIDEO_CAPTURE -> uploadLocalFiles(data, CameraType.VIDEO)
				}
			}
		}

		super.onActivityResult(requestCode, resultCode, data)
	}

	private fun uploadLocalFiles(data: Intent, cameraType: CameraType? = null) {
		val fileUris = vmb.viewModel.getUrisFromResult(data)
		if (fileUris.isEmpty()) {
			showSnackbar(vmb.binding.root, getString(R.string.text_source_definition_no_files_selected))
			return
		}

		fileUris.forEach { uri -> loadFromUri(uri, cameraType) }
	}

	private fun loadFromUri(uri: Uri, cameraType: CameraType? = null) {
		val cursor = vmb.activity.contentResolver.query(uri, null, null, null, null)

		val sourceDefinitionFile = cursor.use {
			if (!it.moveToFirst()) {
				throw IllegalStateException("Metadata of a file weren't found")
			}

			vmb.viewModel.loadFileMetadata(it, uri, cameraType)
		}

		if (sourceDefinitionFile != null) {
			vmb.viewModel.setDefinition(sourceDefinitionFile)
			Timber.v("Source file: $sourceDefinitionFile")
		}
	}

	@RequiresPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
	private fun startLocalFilesPicker() {
		val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
			addCategory(Intent.CATEGORY_OPENABLE)
			putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
			type = "*/*"
		}

		startActivityForResult(intent, REQUEST_UPLOAD_LOCAL_FILE)
	}

	override fun onTakePhotoClick() {
//		viewModel.permissionManager.request(this, Manifest.permission.WRITE_EXTERNAL_STORAGE,
//			{
//				startCameraScreen()
//			},
//			{
//				showSnackbar(binding.root, R.string.consultation_files_permission_write_external_storage_needed)
//			})

	}

	override fun onCaptureVideoClick() {
//		val takePictureIntent = CameraUtility.getCameraImageIntent()
//		if (takePictureIntent.resolveActivity(baseActivity.packageManager) == null) {
//			showToast(R.string.camera_app_not_available)
//			return
//		}
//
//		@SuppressLint("MissingPermission")
//		val photoFile = CameraUtility.createConsultationFile(context, CameraUtility.CameraType.IMAGE)
//
//		if (photoFile == null) {
//			showToast(R.string.camera_error_create_file)
//			return
//		}
//
//		photoURI = FileProvider.getUriForFile(context, LegalZoomBase.filesAuthority, photoFile)
//
//		takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//		startActivityForResult(takePictureIntent, REQUEST_PHOTO_CAPTURE)
	}

	override fun onAnalyzeAction() {
		Navigation.findNavController(vmb.rootView).navigate(R.id.next_action)
	}
}