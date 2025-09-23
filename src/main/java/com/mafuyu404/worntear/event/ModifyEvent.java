package com.mafuyu404.worntear.event;

import com.mafuyu404.worntear.Worntear;
import com.mafuyu404.worntear.init.RuleCache;
import com.mafuyu404.worntear.init.Utils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Worntear.MODID)
public class ModifyEvent {
    @SubscribeEvent
    public static void modifyBreak(PlayerEvent.BreakSpeed event) {
        ItemStack itemStack = event.getEntity().getMainHandItem();
        if (itemStack.isEmpty()) return;
        String rule = RuleCache.matchItem(Utils.getItemRegistryName(itemStack.getItem()));
        if (rule == null) return;
        float scale = Utils.resolveRule(rule, Utils.getDamagePercent(itemStack));
        event.setNewSpeed(event.getNewSpeed() * scale);
    }

    @SubscribeEvent
    public static void modifyAttack(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            ItemStack itemStack = player.getMainHandItem();
            if (itemStack.isEmpty()) return;
            String rule = RuleCache.matchItem(Utils.getItemRegistryName(itemStack.getItem()));
            if (rule == null) return;
            float scale = Utils.resolveRule(rule, Utils.getDamagePercent(itemStack));
            event.setAmount(event.getAmount() * scale);
        }
    }

//    @SubscribeEvent
//    public static void modifyArmor(LivingDamageEvent event) {
//        if (event.getSource().getEntity() instanceof Player player) {
//            ItemStack itemStack = player.getMainHandItem();
//            if (itemStack.isEmpty()) return;
//            String rule = RuleCache.matchItem(Utils.getItemRegistryName(itemStack.getItem()));
//            if (rule == null) return;
//            float scale = Utils.resolveRule(rule, Utils.getDamagePercent(itemStack));
//            event.setAmount(event.getAmount() * scale);
//        }
//    }
//
//    @SubscribeEvent
//    public static void onItemAttributeModifier(ItemAttributeModifierEvent event) {
//        event.addModifier(Attributes.ARMOR, new AttributeModifier(
//                "damage_effect",
//                50,
//                AttributeModifier.Operation.MULTIPLY_TOTAL
//        ));
//        if (event.getItemStack().getItem() instanceof ArmorItem) {
//            String rule = RuleCache.matchItem(Utils.getItemRegistryName(itemStack.getItem()));
//            if (rule == null) return;
//            float scale = Utils.resolveRule(rule, Utils.getDamagePercent(itemStack));
//            event.addModifier(Attributes.ARMOR, new AttributeModifier(
//                    "damage_effect",
//                    5,
//                    AttributeModifier.Operation.MULTIPLY_TOTAL
//            ));
//        }
//    }
}
