package dozer.ui.clickGui.impl.settings;

import dozer.systems.setting.impl.SettingSlider;
import dozer.ui.clickGui.impl.Widget;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Slider extends Widget<SettingSlider> {

    private boolean dragging;

    public Slider(SettingSlider numberSetting, int x, int y, int width, int height, Color color) {
        super(numberSetting, x, y, width, height, color);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (dragging) {
            double diff = mouseX - (this.x);
            double min = type.min();
            double max = type.max();

            if (diff == 0) {
                type.setValue(type.min());
            } else {
                double newValue = this.roundToPlace(((diff / (100)) * (max - min) + min));
                newValue = this.roundToIncrement(newValue, type.increment());

                if (newValue > type.max() || newValue < type.min()) newValue = type.min();

                type.setValue(newValue);
            }
        }

        drawRect(x, y, x + 100, y + 14, new Color(16, 16, 16, 200));
        drawRect(x, y + 13, (int) (x + (type.getValue() / type.max() * 100)), y + 14, new Color(255, 255, 255, 200));
        drawStringWithShadow(type.getName(), this.x + 2, this.y + 3, Color.WHITE);
        drawStringWithShadow(type.getValue() + "", this.x + 100 - fontRenderer().getStringWidth(type.getValue() + "") - 2, this.y + 2, Color.WHITE);
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
