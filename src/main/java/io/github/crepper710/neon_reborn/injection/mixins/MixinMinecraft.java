package io.github.crepper710.neon_reborn.injection.mixins;

import io.github.crepper710.neon_reborn.NeonReborn;
import io.github.crepper710.neon_reborn.utils.IconUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Util;
import net.minecraftforge.common.ForgeVersion;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.ByteBuffer;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 2, shift = At.Shift.AFTER))
    private void startGame(CallbackInfo callbackInfo) {
        NeonReborn.start();
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    private void shutdown(CallbackInfo callbackInfo) {
        NeonReborn.stop();
    }

    @Inject(method = "setWindowIcon", at = @At("HEAD"), cancellable = true)
    private void setWindowIcon(CallbackInfo callbackInfo) {
        if (Util.getOSType() != Util.EnumOS.OSX) {
            Display.setIcon(IconUtils.getIcons());
            callbackInfo.cancel();
        }
    }

    @Inject(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V", shift = At.Shift.AFTER))
    private void createDisplay(CallbackInfo callbackInfo) {
        Display.setTitle(NeonReborn.Info.NAME + " " + NeonReborn.Info.VERSION + " | " + ForgeVersion.mcVersion);
    }

}
