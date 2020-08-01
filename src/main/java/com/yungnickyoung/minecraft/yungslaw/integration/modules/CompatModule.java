package com.yungnickyoung.minecraft.yungslaw.integration.modules;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class CompatModule {
    protected final String MOD_ID;

    public CompatModule(String modId) {
        this.MOD_ID = modId;
    }

    /**
     * Whether or not the mod is to be used for compat.
     * Mod must be loaded and compat must be enabled in the config for this to be true.
     */
    protected boolean enabled = false;

    /**
     * List of ores this mod provides, if applicable.
     */
    public List<IBlockState> ores = new ArrayList<>();

    /**
     * @return true if this mod is successfully loaded and ready for use
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Disables this module.
     */
    public void disable() {
        this.enabled = false;
    }

    /**
     * Enables this module, and loads blocks and other references if necessary.
     * Modules should override their method to add their own behavior.
     */
    public void enable() {
        this.enabled = true;
    }

    /**
     * @return true if the mod has been properly loaded and is enabled in the config
     */
    public boolean shouldBeEnabled() {
        return Loader.isModLoaded(this.MOD_ID);
    }

    protected <T> void addIfAbsent(List<T> list, T item) {
        if (!list.contains(item)) list.add(item);
    }

    protected <T> void addIfAbsent(List<T> list, Collection<T> items) {
        items.forEach(item -> addIfAbsent(list, item));
    }
}
