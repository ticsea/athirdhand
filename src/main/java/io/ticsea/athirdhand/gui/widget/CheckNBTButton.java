package io.ticsea.athirdhand.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import io.ticsea.athirdhand.AThirdHand;
import io.ticsea.athirdhand.config.ModConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.widget.ExtendedButton;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class CheckNBTButton extends ExtendedButton {
    public static final ResourceLocation CHECK_NBT_ON = ResourceLocation.fromNamespaceAndPath(AThirdHand.MODID, "textures/check_nbt/check_nbt_on.png");
    public static final ResourceLocation CHECK_NBT_OFF = ResourceLocation.fromNamespaceAndPath(AThirdHand.MODID, "textures/check_nbt/check_nbt_off.png");
    public static final int WIDTH = 18;
    public static final int HEIGHT = 14;

    public CheckNBTButton(int x, int y, OnPress onPress) {
        super(x, y, WIDTH, HEIGHT, Component.empty(), onPress);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.visible = shouldRender();
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, setTexture());
        if (this.isHovered) {
            var v = ModConfigs.isCheckNBTEable() ? 12.5F : 0.0F;
            guiGraphics.blit(setTexture(), this.getX(), this.getY(), 0.0F, v, WIDTH, HEIGHT, 18, 18);
            guiGraphics.renderTooltip(Minecraft.getInstance().font, Collections.singletonList(setTooltip().getVisualOrderText()), mouseX, mouseY);
        } else {
            guiGraphics.blit(setTexture(), this.getX(), this.getY(), 0.0F, 0.0F, WIDTH, HEIGHT, 18, 18);
        }
    }

    private boolean shouldRender() {
        return ModConfigs.isModEnbale();
    }

    private ResourceLocation setTexture() {
//        return CHECK_NBT_ON;

        if (ModConfigs.isCheckNBTEable()) {
            return CHECK_NBT_ON;
        } else {
            return CHECK_NBT_OFF;
        }
    }

    private Component setTooltip() {
        return ModConfigs.isCheckNBTEable() ? Component.translatable("afknokit.tooltip.check_nbt_button.on") : Component.translatable("afknokit.tooltip.check_nbt_button.off");
    }
}
