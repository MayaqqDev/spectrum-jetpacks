package dev.mayaqq.spectrumJetpacks.mixin;

import dev.mayaqq.spectrumJetpacks.utils.EquipUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("HEAD"))
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        Long energyNum = EquipUtils.getEnergy(EquipUtils.getJetpack(MinecraftClient.getInstance().player));
        if (EquipUtils.hasJetpack(MinecraftClient.getInstance().player) > 0) {

            Text energyText = Text.of("Energy: " + energyNum);
            TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
            int width = textRenderer.getWidth(energyText);
            textRenderer.drawWithShadow(matrices, energyText, 100 - width / 2f, 100, 0xFFFFFF);
        }
    }
}