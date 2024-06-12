package com.danjam.context

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

private const val CHILD_FILE_PATH = "images"
private const val TEMP_FILE_PREFIX = "selected_image"
private const val EXTENSION = ".jpg"
private const val AUTHORITY_SUFFIX = ".fileprovider"

fun Context.getTempPngFileUri(): Uri {
    val directory = File(this.cacheDir, CHILD_FILE_PATH)
    directory.mkdirs()

    val file: File = File.createTempFile(
        TEMP_FILE_PREFIX,
        EXTENSION,
        directory,
    )

    val authority = this.packageName + AUTHORITY_SUFFIX

    return FileProvider.getUriForFile(this, authority, file)
}
