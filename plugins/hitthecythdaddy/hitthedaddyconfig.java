package com.hitthedaddy;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("hitthedaddy")
public interface HitTheDaddyConfig extends Config
{
    @ConfigItem(
            keyName = "playSound",
            name = "Play Sound",
            description = "Enable or disable the hit notification sound."
    )
    default boolean playSound()
    {
        return true;
    }

    @ConfigItem(
            keyName = "useCustomSound",
            name = "Use Custom Sound",
            description = "Play a custom sound instead of the default beep."
    )
    default boolean useCustomSound()
    {
        return false;
    }

    @ConfigItem(
            keyName = "customSoundPath",
            name = "Custom Sound Path",
            description = "Path to the custom sound file (.wav). Only used if 'Use Custom Sound' is enabled."
    )
    default String customSoundPath()
    {
        return "";
    }
}
