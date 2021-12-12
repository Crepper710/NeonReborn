@file:JvmName("OpenGLUtils")

package io.github.crepper710.neon_reborn.utils

import net.minecraft.client.Minecraft

import org.lwjgl.opengl.GL11

fun uniformScale() {
    if (Minecraft.getMinecraft().gameSettings.guiScale != 0) {
        GL11.glScaled(
            4.0 / Minecraft.getMinecraft().gameSettings.guiScale,
            4.0 / Minecraft.getMinecraft().gameSettings.guiScale,
            4.0 / Minecraft.getMinecraft().gameSettings.guiScale
        )
    }
}

fun pushMatrix() = GL11.glPushMatrix()

fun popMatrix() = GL11.glPopMatrix()