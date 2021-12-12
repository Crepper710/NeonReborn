package io.github.crepper710.neon_reborn.gui;

import io.github.crepper710.neon_reborn.NeonReborn;
import io.github.crepper710.neon_reborn.eventsystem.Subscribe;
import io.github.crepper710.neon_reborn.eventsystem.events.EventKeyPress;
import io.github.crepper710.neon_reborn.utils.OpenGLUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public class SettingsGui extends GuiScreen {

    public SettingsGui() {
        this.mc = Minecraft.getMinecraft();
        NeonReborn.getInstance().getEventManager().register(this);
    }

    @Override
    public void initGui() {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        OpenGLUtils.popMatrix();
        OpenGLUtils.uniformScale();

        OpenGLUtils.pushMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onResize(Minecraft mcIn, int w, int h) {
        super.onResize(mcIn, w, h);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.mouseEvent(ClickType.BEGIN, mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        this.mouseEvent(ClickType.DRAG, mouseX, mouseY, clickedMouseButton);
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        this.mouseEvent(ClickType.END, mouseX, mouseY, state);
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        int mWheel = Mouse.getEventDWheel();
        if (mWheel != 0) {
            this.mouseEvent(ClickType.SCROLL, x, y, mWheel);
        }
    }

    private void mouseEvent(ClickType clickType, int x, int y, int value) {

    }

    @Subscribe({EventKeyPress.class})
    private void onKeyPress(EventKeyPress eventKeyPress) {
        if (eventKeyPress.getKeyCode() == Keyboard.KEY_RSHIFT) {
            this.mc.displayGuiScreen(this);
        }
    }

    public enum ClickType {

        BEGIN(InputType.BUTTON), DRAG(InputType.BUTTON), END(InputType.BUTTON), SCROLL(InputType.MOUSE_WHEEL);

        private final InputType inputType;

        ClickType(InputType inputType) {
            this.inputType = inputType;
        }

        public boolean isButtonAction() {
            return inputType == InputType.BUTTON;
        }

        public boolean isMouseWheelAction() {
            return inputType == InputType.MOUSE_WHEEL;
        }

        public InputType getInputType() {
            return inputType;
        }

        private enum InputType {

            BUTTON, MOUSE_WHEEL

        }

    }

}
