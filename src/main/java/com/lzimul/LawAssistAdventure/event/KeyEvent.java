package com.lzimul.LawAssistAdventure.event;

import com.lzimul.LawAssistAdventure.network.DataNetwork;
import com.lzimul.LawAssistAdventure.register.KeyRegister;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static com.lzimul.LawAssistAdventure.Config.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class KeyEvent {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (KeyRegister.KeyboardR.consumeClick()) {
            PacketDistributor.SERVER.noArg().send(new DataNetwork("server:key/R", 0));
        }
    }
}
