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

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.eventbus.api.Event;

public class ContainerInitialEvent extends Event {
    private final MenuType<?> menuType;

    public ContainerInitialEvent(MenuType<?> menuType) {

        this.menuType = menuType;
    }

    public MenuType<?> getMenuType() {
        return menuType;
    }
}
