package dev.mayaqq.spectrumJetpacks.registry;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import dev.mayaqq.spectrumJetpacks.renderer.TrinketJetpackRenderer;

import static dev.mayaqq.spectrumJetpacks.registry.ItemRegistry.BEDROCK_JETPACK;
import static dev.mayaqq.spectrumJetpacks.registry.ItemRegistry.GEMSTONE_JETPACK;

public class TrinketRenderRegistry {
    public static void register() {
        TrinketRendererRegistry.registerRenderer(GEMSTONE_JETPACK, new TrinketJetpackRenderer());
        TrinketRendererRegistry.registerRenderer(BEDROCK_JETPACK, new TrinketJetpackRenderer());
    }
}
