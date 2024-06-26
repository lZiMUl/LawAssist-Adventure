package com.lzimul.LawAssistAdventure.register;

import com.lzimul.LawAssistAdventure.entity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.lzimul.LawAssistAdventure.Config.MODID;

public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> Entity = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);

    public static final Supplier<EntityType<Demo1Animal>> DEMO1 = Entity.register("demo1", () -> EntityType.Builder.of(Demo1Animal::new, MobCategory.CREATURE).sized(2, 0.5F).build("demo1"));
    public static final Supplier<EntityType<Demo2Animal>> DEMO2 = Entity.register("demo2", () -> EntityType.Builder.of(Demo2Animal::new, MobCategory.CREATURE).sized(2, 0.5F).build("demo2"));

    public static void register(IEventBus modEventBus) {
        Entity.register(modEventBus);
    }
}
