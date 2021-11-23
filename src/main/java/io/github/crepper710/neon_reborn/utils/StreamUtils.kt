@file:JvmName("StreamUtils")

package io.github.crepper710.neon_reborn.utils

import java.io.InputStream

inline fun readStreamFully(stream: InputStream) = stream.readBytes()