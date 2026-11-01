package com.barnacle.obsolete_prehistory.registry;

import com.barl_inc.unusual_prehistory.item.UP2MobBucketItem;
import com.barnacle.obsolete_prehistory.ObsoletePrehistory;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class OPItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ObsoletePrehistory.MOD_ID);
    public static List<DeferredItem<? extends Item>> ITEM_TRANSLATIONS = new ArrayList<>();

    public static final DeferredItem<Item> PALEOBALISTUM_SPAWN_EGG = registerSpawnEggItem("paleobalistum", OPEntities.PALEOBALISTUM, 0x643a19, 0xd0952b);
    public static final DeferredItem<Item> BANFFIA_SPAWN_EGG = registerSpawnEggItem("banffia", OPEntities.BANFFIA, 0xb7851e, 0x491c0f);

    public static final DeferredItem<Item> TANGERINE_FOSSIL = registerItem("tangerine_fossil", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MUSACEAE_FOSSIL = registerItem("musaceae_fossil", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PALEOBALISTUM_BUCKET = registerItemNoLang("paleobalistum_bucket", () -> new UP2MobBucketItem(OPEntities.PALEOBALISTUM.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties()));
    public static final DeferredItem<Item> BANFFIA_BUCKET = registerItemNoLang("banffia_bucket", () -> new UP2MobBucketItem(OPEntities.BANFFIA.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties()));

    private static <I extends Item> DeferredItem<I> registerItem(String name, Supplier<? extends I> supplier) {
        DeferredItem<I> item = ITEMS.register(name, supplier);
        ITEM_TRANSLATIONS.add(item);
        return item;
    }

    private static <I extends Item> DeferredItem<I> registerItemNoLang(String name, Supplier<? extends I> supplier) {
        return ITEMS.register(name, supplier);
    }

    private static DeferredItem<Item> registerSpawnEggItem(String name, Supplier<? extends EntityType<? extends Mob>> type, int baseColor, int spotColor) {
        return registerItem(name + "_spawn_egg", () -> new DeferredSpawnEggItem(type, baseColor, spotColor, new Item.Properties()));
    }

    public static IntFunction<String> nameOf(IntFunction<? extends Enum<?>> enumGetter) {
        return id -> enumGetter.apply(id).name().toLowerCase(Locale.ROOT);
    }
}