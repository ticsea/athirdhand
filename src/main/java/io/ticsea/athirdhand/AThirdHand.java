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

package io.ticsea.athirdhand;

import static io.ticsea.athirdhand.config.ModConfigs.SPEC;

import io.ticsea.athirdhand.client.ModKeyBinding;
import io.ticsea.athirdhand.config.ModConfigs;
import io.ticsea.athirdhand.events.KeyPressEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(AThirdHand.MODID)
public class AThirdHand {

    public static final String MODID = "athirdhand";

    public AThirdHand(FMLJavaModLoadingContext context) {
        var modEventBus = context.getModEventBus();
        var forgeEventBus = MinecraftForge.EVENT_BUS;

        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(ModKeyBinding::registerKeybind);
            forgeEventBus.addListener(KeyPressEvent::onKeyPress);
        }
        context.registerConfig(ModConfig.Type.CLIENT, SPEC);
        ModConfigs.loadConfig();
    }


}
