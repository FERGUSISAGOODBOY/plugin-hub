package com.hitthedaddy;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import java.awt.Toolkit;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

@Slf4j
@PluginDescriptor(
        name = "Hit The Scyth Daddy",
        description = "Plays a sound and shows an overlay when you should hit.",
        tags = {"TOB", "boss", "helper", "PVM"}
)
public class HitTheDaddyPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private HitTheDaddyConfig config;

    @Inject
    private HitTheDaddyOverlay overlay;

    @Provides
    HitTheDaddyConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(HitTheDaddyConfig.class);
    }

    @Override
    protected void startUp()
    {
        overlay.setEnabled(true);
        log.info("Hit The Scyth Daddy started!");
    }

    @Override
    protected void shutDown()
    {
        overlay.setEnabled(false);
        log.info("Hit The Scyth Daddy stopped!");
    }

    @Subscribe
    public void onGameTick(GameTick event)
    {
        if (config.playSound())
        {
            playSound();
        }
    }

    private void playSound()
    {
        if (config.useCustomSound() && !config.customSoundPath().isEmpty())
        {
            try
            {
                File soundFile = new File(config.customSoundPath());
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            }
            catch (Exception e)
            {
                log.error("Failed to play custom sound", e);
                Toolkit.getDefaultToolkit().beep(); // fallback if custom sound fails
            }
        }
        else
        {
            Toolkit.getDefaultToolkit().beep(); // default beep
        }
    }
}
