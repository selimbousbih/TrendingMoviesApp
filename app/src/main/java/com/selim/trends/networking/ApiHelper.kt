package com.selim.trends.networking

import com.selim.trends.application.Config.IMAGE_BASE_URL

object ApiHelper {
    fun getImagePath(filename: String?, size: ImageSize = ImageSize.THUMBNAIL): String? {
        return filename?.run {
            "${IMAGE_BASE_URL}${size.value}$filename"
        }
    }

    enum class ImageSize(val value: String) {
        THUMBNAIL("w500"), ORIGINAL("original")
    }
}