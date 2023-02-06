package dev.mayaqq.spectrumJetpacks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.StickyKeyBinding;
import org.lwjgl.glfw.GLFW;

public class SpectrumJetpacksClient implements ClientModInitializer {
    public static KeyBinding hoverKey;
    public static KeyBinding toggleKey;

    @Override
    public void onInitializeClient() {
        hoverKey = KeyBindingHelper.registerKeyBinding(new StickyKeyBinding(
                "key.spectrumjetpacks.hover",
                GLFW.GLFW_KEY_INSERT,
                "category.spectrumjetpacks",
                () -> true
        ));
        toggleKey = KeyBindingHelper.registerKeyBinding(new StickyKeyBinding(
                "key.spectrumjetpacks.toggle",
                GLFW.GLFW_KEY_DELETE,
                "category.spectrumjetpacks",
                () -> true
        ));
    }
}