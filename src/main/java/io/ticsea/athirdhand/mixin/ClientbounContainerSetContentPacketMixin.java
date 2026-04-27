package io.ticsea.athirdhand.mixin;

import io.ticsea.athirdhand.config.ModConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(ClientboundContainerSetContentPacket.class)
public abstract class ClientbounContainerSetContentPacketMixin {
    @Shadow @Final private List<ItemStack> items;
    @Shadow @Final private int containerId;
    @Unique
    Minecraft athirdhand2_0$mc = Minecraft.getInstance();

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void takeAll(CallbackInfo ci) {
        Player player = athirdhand2_0$mc.player;
        MultiPlayerGameMode gameMode = athirdhand2_0$mc.gameMode;

        if (!ModConfigs.isModEnbale() || player == null || gameMode == null) return;

        Map<Item, CompoundTag> playerInventoryItems = new HashMap<>();
        player.getInventory().items.forEach(itemStack -> playerInventoryItems.put(itemStack.getItem(), itemStack.getTag()));

        for (int i = 0; i < this.items.size() - 36; ++i) {
            ItemStack itemStack = this.items.get(i);
            Item item = itemStack.getItem();

            if (item == Items.AIR || !playerInventoryItems.containsKey(item)) continue;

            if (ModConfigs.isCheckNBTEable() && itemStack.hasTag()) {
                if (!playerInventoryItems.get(item).equals(itemStack.getTag())) {
                    continue;
                }
            }

            int finalI = i;
            athirdhand2_0$mc.execute(() -> gameMode.handleInventoryMouseClick(this.containerId, finalI,  0, ClickType.QUICK_MOVE, player));
        }
    }
}
