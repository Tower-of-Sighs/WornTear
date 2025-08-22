package com.mafuyu404.worntear;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class Config {
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>>  CLIMB_BLOCK_WHITELIST;
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Config");
        CLIMB_BLOCK_WHITELIST = BUILDER
                .comment("eg. :0.3+[0.4,0.8]*0.7")
                .defineList("ClimbBlockWhitelist",
                        List.of("minecraft:stone"),
                        entry -> entry instanceof String
                );
        SPEC = BUILDER.build();
    }
}
