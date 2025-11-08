/*
 * Copyright (c) 2025 ticsea. All rights reserved.
 * This code is provided "as is" without warranty of any kind, express or implied.
 * You may copy, modify, and distribute this code exclusively for non-commercial
 * purposes (e.g., personal use, academic research, non-profit projects) without
 * prior permission, provided this copyright notice is retained in all copies or
 * derivatives.
 * Commercial use (including, but not limited to, selling the code, using it in
 * paid products/services, or monetizing it in any form) is strictly prohibited
 * without explicit written permission from the copyright holder.
 */

package io.ticsea.athirdhand.events;

import io.ticsea.athirdhand.config.ModConfig;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChestOpenEvent {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChestOpenEvent.class);
    private static final BooleanValue SWITCH = ModConfig.getSwitch();
    private static final List<MenuType<? extends AbstractContainerMenu>> SUPPORT_CONTAINER_SCREEN =
            List.of(MenuType.GENERIC_9x3, MenuType.SHULKER_BOX);

    private static final Minecraft mc = Minecraft.getInstance();

    public static void onChestOpen(ContainerInitialEvent event) {
        LOGGER.debug("Trick or treat!");
        if (!SWITCH.get() || !SUPPORT_CONTAINER_SCREEN.contains(event.getMenuType())) {
            LOGGER.warn("It looks like you don't have the mod feature enabled, or the mod doesn't support this type of container");
            return;
        };

        var player = mc.player;
        NonNullList<Slot> slots = player.containerMenu.slots;
        var containerId = player.containerMenu.containerId;
        transferMatchingItems(slots, containerId, player, mc);
    }

    private static void transferMatchingItems(
            NonNullList<Slot> slots,
            int containerId,
            Player player,
            Minecraft mc
    ) {
        for (Slot slot : slots) {
            if (slot.container instanceof Inventory) break;

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