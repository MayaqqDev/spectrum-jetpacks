package dev.mayaqq.spectrumJetpacks.registry;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacks.CONFIG;

public class ServerEventRegistry {
    public static boolean tick = false;
    private static int tickCounter = 0;
    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickCounter++;
            if (tickCounter >= CONFIG.energyDrainTickFrequency) {
                tickCounter = 0;
                tick = true;
            }
            else {
                tick = false;
            }
        });
    }
}