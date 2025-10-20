package github.ticsea.athirdhand.events;

import github.ticsea.athirdhand.client.ModKeyBinding;
import github.ticsea.athirdhand.config.ModConfig;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;

public class KeyPressEvent {
//    private static final Logger LOGGER = LogUtils.getLogger();

    private static final KeyMapping KEY = ModKeyBinding.getKey();
    private static final BooleanValue SWITCH = ModConfig.getSwitch();
    private static final Minecraft mc = Minecraft.getInstance();

    private static final Component MSG_ENABLED = Component.translatable("athirdhand.state.on");
    private static final Component MSG_DISABLED = Component.translatable("athirdhand.state.off");

    public static void onKeyPress(ClientTickEvent event) {
        if (event.phase!=Phase.END) return;

        while (KEY.consumeClick()) {
            SWITCH.set(!SWITCH.get());

            var temp = SWITCH.get() ? MSG_ENABLED : MSG_DISABLED;
            mc.player.displayClientMessage(temp, true);
        }
    }
}
