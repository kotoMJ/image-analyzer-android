package com.example.android.analyzer.text.ui

import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.OpenableColumns
import com.example.android.analyzer.core.ktools.CameraType
import com.example.android.analyzer.text.entity.LocalFileSourceDefinition
import com.example.android.core.arch.ObservableViewModel
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class TextSourceDefinitionViewModel @Inject constructor() : ObservableViewModel() {

	val sourceDefinition: MutableLiveData<LocalFileSourceDefinition> = MutableLiveData()
	val sourceDefinitionUri: MutableLiveData<Uri> = MutableLiveData()
	val canGoToNextScreen: MutableLiveData<Boolean> = MutableLiveData()

	fun setDefinition(definition: LocalFileSourceDefinition) {
		sourceDefinition.value = definition
		sourceDefinitionUri.value = definition.fileUri
		canGoToNextScreen.value = true
	}

	fun getUrisFromResult(data: Intent): List<Uri> {
		val selectedFiles = mutableListOf<Uri>()

		val clipData = data.clipData
		if (clipData != null) { // checking multiple selection
			for (i in 0 until clipData.itemCount) {
				selectedFiles.add(clipData.getItemAt(i).uri)
			}
		} else {
			selectedFiles.add(data.data!!)
		}

		return selectedFiles
	}

	/**
	 * Cursor is closed outside. Just load the metadata
	 */
	fun loadFileMetadata(cursor: Cursor, fileUri: Uri, cameraType: CameraType? = null): LocalFileSourceDefinition? {
		val fileIdIndex = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_DOCUMENT_ID)
		val documentId = if (fileIdIndex > -1) cursor.getString(fileIdIndex) else UUID.randomUUID().toString()

		val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
		val fileName: String = cursor.getString(nameIndex)

		val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
		val sizeBytes: Long? = if (!cursor.isNull(sizeIndex)) cursor.getLong(sizeIndex) else null

		val lastModifiedIndex = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_LAST_MODIFIED)
		val lastModified: Date? = when {
			cameraType != null -> Date()
			!cursor.isNull(lastModifiedIndex) -> Date(cursor.getLong(lastModifiedIndex))
			else -> Date()
		}

		val mimeType = when {
			cameraType != null -> cameraType.mimeType
			else -> {
				val mimeIndex = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_MIME_TYPE)
				cursor.getString(mimeIndex)
			}
		}

		return LocalFileSourceDefinition(
			fileId = documentId,
			name = fileName,
			sizeBytes = sizeBytes,
			lastModified = lastModified,
			mimeType = mimeType,
			fileUri = fileUri
		)
	}
}


