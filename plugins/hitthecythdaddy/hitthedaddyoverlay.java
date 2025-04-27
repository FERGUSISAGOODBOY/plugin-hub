package com.hitthedaddy;

import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

import java.awt.Dimension;
import java.awt.Graphics2D;

public class HitTheDaddyOverlay extends Overlay
{
    private final Client client;
    private boolean enabled = false;

    @Inject
    public HitTheDaddyOverlay(Client client)
    {
        this.client = client;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        setPriority(OverlayPriority.HIGH);
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (!enabled)
        {
            return null;
        }

        graphics.drawString("SPANK THE MONKEY!", 100, 100);
        return null;
    }
}
