package dev.mayaqq.spectrumJetpacks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "spectrumjetpacks")
public class SpectrumJetpacksConfig implements ConfigData {
    public boolean soundsEnabled = true;
    public int inkUsagePerTick = 1;
    public int inkUsagePerTickHovering = 2;
}
