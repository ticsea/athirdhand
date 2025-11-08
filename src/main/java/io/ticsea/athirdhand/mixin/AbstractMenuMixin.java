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

package io.ticsea.athirdhand.mixin;

import io.ticsea.athirdhand.events.ContainerInitialEvent;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(AbstractContainerMenu.class)
public abstract class AbstractMenuMixin {
    @Shadow @Final @Nullable private MenuType<?> menuType;

    @Inject(method = "initializeContents", at = @At(value = "TAIL"))
    private void inject(int p_182411_, List<ItemStack> p_182412_, ItemStack p_182413_, CallbackInfo ci) {
        ContainerInitialEvent containerInitialEvent = new ContainerInitialEvent(this.menuType);
        MinecraftForge.EVENT_BUS.post(containerInitialEvent);
    }
}
