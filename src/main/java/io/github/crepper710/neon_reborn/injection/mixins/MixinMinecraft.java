package io.github.crepper710.neon_reborn.injection.mixins;

import io.github.crepper710.neon_reborn.NeonReborn;
import io.github.crepper710.neon_reborn.eventsystem.events.EventKeyPress;
import io.github.crepper710.neon_reborn.utils.IconUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.Util;
import net.minecraftforge.common.ForgeVersion;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("UnresolvedMixinReference")
@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Shadow
    public GuiScreen currentScreen;

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 2, shift = At.Shift.AFTER))
    private void startGame(CallbackInfo ci) {
        NeonReborn.start();
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    private void shutdown(CallbackInfo ci) {
        NeonReborn.stop();
    }

    @Inject(method = "setWindowIcon", at = @At("HEAD"), cancellable = true)
    private void setWindowIcon(CallbackInfo ci) {
        if (Util.getOSType() != Util.EnumOS.OSX) {
            Display.setIcon(IconUtils.getIcons());
            ci.cancel();
        }
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.AFTER))
    private void onKeyPress(CallbackInfo ci) {
        if (Keyboard.getEventKeyState() && this.currentScreen == null) {
            NeonReborn.getInstance().getEventManager().call(new EventKeyPress(Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey()));
        }
    }

    @Inject(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V", shift = At.Shift.AFTER))
    private void createDisplay(CallbackInfo ci) {
        Display.setTitle(NeonReborn.Info.NAME + " " + NeonReborn.Info.VERSION + " | " + ForgeVersion.mcVersion);
    }

}
