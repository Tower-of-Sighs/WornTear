package com.mafuyu404.worntear.event;

import com.mafuyu404.worntear.Worntear;
import com.mafuyu404.worntear.init.Utils;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = Worntear.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onConfigLoad(ModConfigEvent.Loading event) {
        if (event.getConfig().getModId().equals(Worntear.MODID)) Utils.loadAllRule();
    }
}
