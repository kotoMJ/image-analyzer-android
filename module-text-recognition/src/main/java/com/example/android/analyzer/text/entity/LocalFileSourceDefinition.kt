package com.example.android.analyzer.text.entity

import android.net.Uri
import com.example.android.analyzer.core.OpenForMocking
import java.util.Date

@OpenForMocking
data class LocalFileSourceDefinition(
	val fileUri: Uri,
	val fileId: String,
	val name: String,
	val mimeType: String,
	val sizeBytes: Long?,
	val lastModified: Date?
)