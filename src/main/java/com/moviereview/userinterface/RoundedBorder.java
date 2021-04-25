package com.moviereview.userinterface;

import java.awt.Graphics;

import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Insets;

public class RoundedBorder implements Border{
    private int borderRadius;

    RoundedBorder(int radius)
    {
        this.borderRadius = radius;
    }
    
    @Override
    public Insets getBorderInsets(Component c)
    {
        return new Insets(this.borderRadius+1, this.borderRadius+1, this.borderRadius+2, this.borderRadius);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        g.drawRoundRect(x, y, width-1, height-1, borderRadius, borderRadius);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
