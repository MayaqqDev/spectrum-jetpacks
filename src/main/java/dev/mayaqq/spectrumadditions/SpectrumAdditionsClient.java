package dev.mayaqq.spectrumadditions;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import dev.mayaqq.spectrumadditions.utils.EquipUtils;
import dev.mayaqq.spectrumadditions.utils.TrinketJetpackRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.StickyKeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import static dev.mayaqq.spectrumadditions.registries.SpectrumAdditionsItems.GEMSTONE_JETPACK;

public class SpectrumAdditionsClient implements ClientModInitializer {
    public static KeyBinding toggleKey;
    public static KeyBinding hoverKey;

    @Override
    public void onInitializeClient() {
        TrinketRendererRegistry.registerRenderer(GEMSTONE_JETPACK, new TrinketJetpackRenderer());
        toggleKey = KeyBindingHelper.registerKeyBinding(new StickyKeyBinding("key.spectrumadditions.toggle", GLFW.GLFW_KEY_DELETE, "category.spectrumadditions", () -> true));
        hoverKey = KeyBindingHelper.registerKeyBinding(new StickyKeyBinding("key.spectrumadditions.hover", GLFW.GLFW_KEY_INSERT, "category.spectrumadditions", () -> true));

        HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
            long energyNum = EquipUtils.getEnergy(EquipUtils.getJetpack(MinecraftClient.getInstance().player));
            MinecraftClient mc = MinecraftClient.getInstance();
            if (EquipUtils.hasJetpack(mc.player) > 0) {
                long energyPercent = energyNum / (5000 / 100);
                String energyString = energyPercent + "%";
                if (energyPercent >= 50) {
                    energyString = "§a" + energyString;
                } else if (energyPercent >= 25) {
                    energyString = "§e" + energyString;
                } else {
                    energyString = "§c" + energyString;
                }
                Text energyText = Text.of("Stored Ink: " + energyString);
                String toggleString = toggleKey.isPressed() ? "§aON" : "§cOFF";
                String hoverString = hoverKey.isPressed() ? "§aON" : "§cOFF";
                Text toggleText = Text.of("Toggled: " + toggleString);
                Text hoverText = Text.of("Hovering: " + hoverString);
                TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
                int width = textRenderer.getWidth(energyText);
                textRenderer.drawWithShadow(matrices, energyText, 45 - width / 2f, 100, 0xFFFFFF);
                textRenderer.drawWithShadow(matrices, toggleText, 45 - width / 2f, 110, 0xFFFFFF);
                textRenderer.drawWithShadow(matrices, hoverText, 45 - width / 2f, 120, 0xFFFFFF);
            }
        });
    }
}