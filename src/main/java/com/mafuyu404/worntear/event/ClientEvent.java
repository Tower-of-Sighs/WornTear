package com.mafuyu404.worntear.event;

import com.mafuyu404.worntear.Config;
import com.mafuyu404.worntear.Worntear;
import com.mafuyu404.worntear.init.RuleCache;
import com.mafuyu404.worntear.init.Utils;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Worntear.MODID, value = Dist.CLIENT)
public class ClientEvent {
    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        if (!Config.TOOLTIP.get()) return;
        String rule = RuleCache.matchItem(Utils.getItemRegistryName(event.getItemStack().getItem()));
        if (rule == null) return;
        float scale = Utils.resolveRule(rule, Utils.getDamagePercent(event.getItemStack()));
        scale = Math.round(scale * 10000) / 100f;
        event.getToolTip().add(1, Component.literal("§i耐久影响系数: " + scale + "%"));
    }
}
