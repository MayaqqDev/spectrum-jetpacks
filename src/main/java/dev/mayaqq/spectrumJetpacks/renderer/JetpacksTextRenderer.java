package dev.mayaqq.spectrumJetpacks.renderer;

import de.dafuqs.spectrum.render.HudRenderers;
import dev.mayaqq.spectrumJetpacks.items.JetpackItem;
import dev.mayaqq.spectrumJetpacks.utils.EquipUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import static dev.mayaqq.spectrumJetpacks.registry.KeybindRegistry.*;

public class JetpacksTextRenderer {
    public static void renderJetpackInfo(DrawContext drawContext) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientPlayerEntity player = mc.player;
        long energyNum = EquipUtils.getEnergy(EquipUtils.getJetpack(player));
        if (EquipUtils.jetpackNumber(player) > 0) {
            long energyPercent = energyNum / (((JetpackItem) EquipUtils.getJetpack(player).getItem()).maxInk / 100);
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
            drawContext.drawTextWithShadow(textRenderer, energyText.asOrderedText(), (int) (45 - width / 2f), 100, 0xFFFFFF);
            drawContext.drawTextWithShadow(textRenderer, toggleText.asOrderedText(), (int) (45 - width / 2f), 110, 0xFFFFFF);
            drawContext.drawTextWithShadow(textRenderer, hoverText.asOrderedText(), (int) (45 - width / 2f), 120, 0xFFFFFF);
        }
    }
}