package io.github.crepper710.neon_reborn.injection.mixins;

import io.github.crepper710.neon_reborn.NeonReborn;
import io.github.crepper710.neon_reborn.eventsystem.events.EventRender2D;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("ALL")
@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    @Inject(method = "renderTooltip", at = @At("HEAD"))
    private void renderTooltip(ScaledResolution sr, float partialTicks, CallbackInfo callbackInfo) {
        NeonReborn.getInstance().getEventManager().call(new EventRender2D(partialTicks));
    }

}
