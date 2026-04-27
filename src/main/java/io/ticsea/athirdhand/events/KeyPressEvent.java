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

import io.ticsea.athirdhand.client.ModKeyBinding;
import io.ticsea.athirdhand.config.ModConfigs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;

public class KeyPressEvent {
//    private static final Logger LOGGER = LogUtils.getLogger();

    private static final KeyMapping KEY = ModKeyBinding.getKey();
    private static final Minecraft mc = Minecraft.getInstance();

    private static final Component MSG_ENABLED = Component.translatable("info.state.on");
    private static final Component MSG_DISABLED = Component.translatable("info.state.off");

    public static void onKeyPress(ClientTickEvent event) {
        if (event.phase!=Phase.END || mc.player == null) return;

        while (KEY.consumeClick()) {
            ModConfigs.toggleModEnable();

            var temp = ModConfigs.isModEnbale() ? MSG_ENABLED : MSG_DISABLED;
            mc.player.displayClientMessage(temp, true);
        }
    }
}
