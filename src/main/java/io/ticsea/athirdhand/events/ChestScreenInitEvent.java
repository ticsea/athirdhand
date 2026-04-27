package io.ticsea.athirdhand.events;

import com.mojang.blaze3d.platform.Window;
import io.ticsea.athirdhand.config.ModConfigs;
import io.ticsea.athirdhand.gui.widget.CheckNBTButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.gui.screens.inventory.ShulkerBoxScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChestScreenInitEvent {
    private static final Window WINDOW = Minecraft.getInstance().getWindow();

@SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Post event) {
        Screen screen = event.getScreen();

        if (!(screen instanceof ContainerScreen || screen instanceof ShulkerBoxScreen)) return;

        int height = ((AbstractContainerScreen<?>) screen).getGuiTop();
        int y = height == 36 ? WINDOW.getGuiScaledHeight() / 2 - 14 : WINDOW.getGuiScaledHeight() / 2 + 13;

        event.addListener(new CheckNBTButton(WINDOW.getGuiScaledWidth() / 2  - 9, y, pButton -> ModConfigs.toggleCheckNBTButton()));

//        event.addListener(Button.builder(Component.empty(), pButton -> System.out.println()).build());
    }
}
