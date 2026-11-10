package com.barnacle.obsolete_prehistory.registry;

import com.barnacle.obsolete_prehistory.ObsoletePrehistory;
import com.barnacle.obsolete_prehistory.entity.Antarctilamna;
import com.barnacle.obsolete_prehistory.entity.Banffia;
import com.barnacle.obsolete_prehistory.entity.Paleobalistum;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class OPEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ObsoletePrehistory.MOD_ID);

    public static List<DeferredHolder<EntityType<?>, ? extends EntityType<?>>> ENTITY_TRANSLATIONS = new ArrayList<>();

    public static final DeferredHolder<EntityType<?>, EntityType<Paleobalistum>> PALEOBALISTUM = registerEntity("paleobalistum", Paleobalistum::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.9F, 1.2F).eyeHeight(0.45F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Banffia>> BANFFIA = registerEntity("banffia", Banffia::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.9F, 0.4F).eyeHeight(0.45F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Antarctilamna>> ANTARCTILAMNA = registerEntity("antarctilamna", Antarctilamna::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.9F, 0.9F).eyeHeight(0.45F).clientTrackingRange(10));

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerEntity(String name, EntityType.EntityFactory<E> factory, MobCategory entityClassification, Consumer<EntityType.Builder<E>> builderConsumer) {
        DeferredHolder<EntityType<?>, EntityType<E>> entity = registerEntityNoLang(name, factory, entityClassification, builderConsumer);
        ENTITY_TRANSLATIONS.add(entity);
        return entity;
    }

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerEntityNoLang(String name, EntityType.EntityFactory<E> factory, MobCategory entityClassification, Consumer<EntityType.Builder<E>> builderConsumer) {
        return ENTITY_TYPES.register(name, () -> {
            var builder = EntityType.Builder.of(factory, entityClassification);
            builderConsumer.accept(builder);
            return builder.build(ObsoletePrehistory.MOD_ID + ":" + name);
        });
    }
}