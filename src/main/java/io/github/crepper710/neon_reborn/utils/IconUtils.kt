@file:JvmName("IconUtils")

package io.github.crepper710.neon_reborn.utils

import java.io.InputStream
import java.nio.ByteBuffer
import javax.imageio.ImageIO

fun getIcons() = arrayOf(readImageToBuffer(streamFromJar("/assets/minecraft/neon_reborn/icon_16x16.png")), readImageToBuffer(streamFromJar("/assets/minecraft/neon_reborn/icon_32x32.png")))

fun readImageToBuffer(stream: InputStream?): ByteBuffer {
    val image = ImageIO.read(stream)
    val rgbValues = image.getRGB(0, 0, image.width, image.height, null, 0, image.width)
    val imageBuffer = ByteBuffer.allocate(4 * rgbValues.size);
    rgbValues.forEach { i -> imageBuffer.putInt((i shl 8) or ((i shr 24) and 255)) }
    imageBuffer.flip()
    return imageBuffer
}