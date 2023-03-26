package dev.mayaqq.spectrumJetpacks;

import dev.mayaqq.spectrumJetpacks.registry.EventRegistry;
import dev.mayaqq.spectrumJetpacks.registry.KeybindRegistry;
import dev.mayaqq.spectrumJetpacks.registry.TrinketRenderRegistry;
import net.fabricmc.api.ClientModInitializer;

public class SpectrumJetpacksClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        TrinketRenderRegistry.register();
        KeybindRegistry.register();
        EventRegistry.register();
    }
}