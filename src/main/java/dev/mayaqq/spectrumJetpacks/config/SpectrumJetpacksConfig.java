package dev.mayaqq.spectrumJetpacks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "spectrumjetpacks")
public class SpectrumJetpacksConfig implements ConfigData {
    @Comment("Whether to play sounds when using jetpacks (client-side)")
    public boolean soundsEnabled = true;
    @Comment("The frequency of energy drain in ticks")
    public int energyDrainTickFrequency = 2;
    public int inkUsagePerTick = 1;
    public int inkUsagePerTickGoingUp = 2;
    public int inkUsagePerTickForward = 1;
    public int gemstoneJetpackMaxFuel = 5000;
    public float gemstoneJetpackMaxVerticalVelocity = 0.3f;
    public float gemstoneJetpackMaxHorizontalVelocity = 1.5f;
    public float gemstoneJetpackVerticalSpeedAdditionPerTick = 0.1f;
    public float gemstoneJetpackHorizontalSpeedMultiplier = 0.05f;
    public int bedrockJetpackMaxFuel = 10000;
    public float bedrockJetpackMaxVerticalVelocity = 0.5f;
    public float bedrockJetpackMaxHorizontalVelocity = 2f;
    public float bedrockJetpackVerticalSpeedAdditionPerTick = 0.2f;
    public float bedrockJetpackHorizontalSpeedMultiplier = 0.1f;
}
