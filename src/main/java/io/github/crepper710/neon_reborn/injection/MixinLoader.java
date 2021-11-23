package io.github.crepper710.neon_reborn.injection;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

public class MixinLoader implements IFMLLoadingPlugin {

    public MixinLoader() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.neon_reborn.json");
        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
        System.out.println("hi");
    }

    @NotNull
    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Nullable
    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Nullable
    @Override
    public String getAccessTransformerClass() {
        return null;
    }
    
}
