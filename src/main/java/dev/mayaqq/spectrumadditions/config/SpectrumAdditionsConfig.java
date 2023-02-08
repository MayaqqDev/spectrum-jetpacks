package dev.mayaqq.spectrumadditions.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "spectrumadditions")
public class SpectrumAdditionsConfig implements ConfigData {
    public boolean soundsEnabled = true;
    public int inkUsagePerTick = 1;
    public int inkUsagePerTickHovering = 2;
}
