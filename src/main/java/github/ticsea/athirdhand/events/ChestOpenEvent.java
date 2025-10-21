package github.ticsea.athirdhand.events;

import github.ticsea.athirdhand.config.ModConfig;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.gui.screens.inventory.ShulkerBoxScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChestOpenEvent {
//    private static final Logger LOGGER = LoggerFactory.getLogger(ChestOpenEvent.class);
    private static final BooleanValue SWITCH = ModConfig.getSwitch();
    private static final List<Class<? extends AbstractContainerMenu>> SUPPORT_CONTAINER_MENU = List.of(ChestMenu.class, ShulkerBoxMenu.class);
    private static final List<Class<? extends AbstractContainerScreen<?>>> SUPPORT_CONTAINER_SCREEN =
            List.of(ContainerScreen.class, ShulkerBoxScreen.class);

    private static final Minecraft mc = Minecraft.getInstance();

    public static void onChestOpen(ScreenEvent.Opening event) {
//        LOGGER.debug("Fired");
        // check connection will also check player == null too.
        if (mc.getConnection() == null) return;
        if (!SWITCH.get() || !SUPPORT_CONTAINER_SCREEN.contains(event.getScreen().getClass())) return;

        var player = mc.player;
        var container = player.containerMenu;
        if (!SUPPORT_CONTAINER_MENU.contains(container.getClass())) return;
        mc.execute(() -> transferMatchingItems(container, container.containerId, player, mc));
    }

    private static void transferMatchingItems(
            AbstractContainerMenu container,
            int containerId,
            Player player,
            Minecraft mc
    ) {
        for (Slot slot : container.slots) {
            if (slot.container instanceof Inventory) continue;

            ItemStack stack = slot.getItem();
            // the method contains() may cause wrong because SOME ITEM NBT DIFF.
            if (!stack.isEmpty() && player.getInventory().contains(stack)) {
                // 0 respond left button click.
                // IDEA says handleInventoryMouseClick may produce nullpointerException.
                mc.gameMode.handleInventoryMouseClick(containerId, slot.index, 0, ClickType.QUICK_MOVE, player);
            }
        }
    }
}