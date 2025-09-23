package com.mafuyu404.worntear;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class Config {
    public static final ForgeConfigSpec.ConfigValue<Boolean> TOOLTIP;
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Config");
        TOOLTIP = BUILDER
                .comment("是否在物品提示框中显示耐久影响系数。")
                .define("tooltip", true);
        SPEC = BUILDER.build();
    }
}
