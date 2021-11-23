@file:JvmName("FileUtils")

package io.github.crepper710.neon_reborn.utils

import io.github.crepper710.neon_reborn.NeonReborn
import java.io.InputStream

fun streamFromJar(name: String): InputStream? = NeonReborn::class.java.getResourceAsStream(name)
