package dev.mayaqq.spectrumJetpacks.registry;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.StickyKeyBinding;
import org.lwjgl.glfw.GLFW;

public class KeybindRegistry {
    public static KeyBinding toggleKey;
    public static KeyBinding hoverKey;
    public static KeyBinding boostKey;

    public static void register() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new StickyKeyBinding(
                "key.spectrumjetpacks.toggle",
                GLFW.GLFW_KEY_DELETE,
                "category.spectrumjetpacks",
                () -> true
        ));
        hoverKey = KeyBindingHelper.registerKeyBinding(new StickyKeyBinding(
                "key.spectrumjetpacks.hover",
                GLFW.GLFW_KEY_INSERT,
                "category.spectrumjetpacks",
                () -> true
        ));
        boostKey = KeyBindingHelper.registerKeyBinding(new StickyKeyBinding(
                "key.spectrumjetpacks.boost",
                GLFW.GLFW_KEY_HOME,
                "category.spectrumjetpacks",
                () -> true
        ));
    }
}
