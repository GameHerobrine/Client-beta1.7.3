package dozer.ui.clickGui.impl.settings;

import dozer.setting.impl.SettingSlider;
import dozer.ui.clickGui.impl.ModuleButton;
import dozer.ui.clickGui.impl.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.src.gui.Gui;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Slider extends Widget {

    private final SettingSlider numberSetting;
    private boolean dragging;

    public Slider(SettingSlider numberSetting, int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.numberSetting = numberSetting;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {


        if (dragging) {
            double diff = mouseX - (this.x);
            double min = this.numberSetting.min();
            double max = this.numberSetting.max();

            if (diff == 0) {
                this.numberSetting.setValue(this.numberSetting.min());
            } else {
                double newValue = this.roundToPlace(((diff / (100)) * (max - min) + min));
                newValue = this.roundToIncrement(newValue, this.numberSetting.increment());
                if (newValue > this.numberSetting.max()) {
                    newValue = this.numberSetting.min();
                }
                if (newValue < this.numberSetting.min()) {
                    newValue = this.numberSetting.min();
                }
                this.numberSetting.setValue(newValue);
            }
        }

        drawRect(x, y, x + 100, y + 14, new Color(16, 16, 16, 200));
        drawRect(x, y + 13, (int) (x + (this.numberSetting.getValue() / this.numberSetting.max() * 100)), y + 14, new Color(255, 255, 255, 200));
        drawStringWithShadow(this.numberSetting.getName(), this.x + 2, this.y + 3, Color.WHITE);
        drawStringWithShadow(this.numberSetting.getValue() + "", this.x + 100 - Minecraft.getMinecraft().fontRenderer.getStringWidth(this.numberSetting.getValue() + "") - 2, this.y + 2, Color.WHITE);
    }

    public double roundToPlace(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double roundToIncrement(double value, double increment) {
        double v = (double) Math.round(value / increment) * increment;
        BigDecimal bd = new BigDecimal(v);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }



    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

        if(!isHovering(mouseX, mouseY)) return;

        if (mouseButton == 0) {
            dragging = true;
            System.out.println("dragging");
        }
    }

    @Override
    public void mouseReleased(int x, int y, int button) {
        dragging = false;
    }

    @Override
    public void keyTyped(char character, int keyCode) {

    }

}
