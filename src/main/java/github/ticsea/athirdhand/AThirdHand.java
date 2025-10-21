// Copyright © 2025 ticsea. ALL RIGHTS RESERVED.
// Unauthorized use, copying or modification is prohibited.

package github.ticsea.athirdhand;

import static github.ticsea.athirdhand.config.ModConfig.SPEC;

import github.ticsea.athirdhand.client.ModKeyBinding;
import github.ticsea.athirdhand.events.KeyPressEvent;
import github.ticsea.athirdhand.events.ChestOpenEvent;
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
            forgeEventBus.addListener(ChestOpenEvent::onChestOpen);
        }
        context.registerConfig(ModConfig.Type.CLIENT, SPEC);
        github.ticsea.athirdhand.config.ModConfig.loadConfig();
    }
}
