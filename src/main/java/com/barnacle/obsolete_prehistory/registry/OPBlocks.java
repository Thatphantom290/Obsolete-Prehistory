package com.barnacle.obsolete_prehistory.registry;

import com.barl_inc.unusual_prehistory.block.UnderwaterEggBlock;
import com.barnacle.obsolete_prehistory.ObsoletePrehistory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class OPBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ObsoletePrehistory.MOD_ID);

    public static List<DeferredBlock<? extends Block>> BLOCK_TRANSLATIONS = new ArrayList<>();

    public static List<Supplier<? extends Block>> EGG_BLOCKS = new ArrayList<>();

    public static final BlockBehaviour.Properties WATER_EGG_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instabreak().noOcclusion().noCollission().sound(SoundType.FROGSPAWN);

    public static final DeferredBlock<Block> PALEOBALISTUM_EGGS = registerEggBlock("paleobalistum_eggs", () -> new UnderwaterEggBlock(WATER_EGG_PROPERTIES, OPEntities.PALEOBALISTUM::get, 2));
    public static final DeferredBlock<Block> BANFFIA_EGGS = registerEggBlock("banffia_eggs", () -> new UnderwaterEggBlock(WATER_EGG_PROPERTIES, OPEntities.BANFFIA::get, 3));

    private static <B extends Block> DeferredBlock<B> registerBlock(String name, Supplier<? extends B> supplier) {
        DeferredBlock<B> block = BLOCKS.register(name, supplier);
        OPItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        BLOCK_TRANSLATIONS.add(block);
        return block;
    }

    private static <B extends Block> DeferredBlock<B> registerBlockNoLang(String name, Supplier<? extends B> supplier) {
        DeferredBlock<B> block = BLOCKS.register(name, supplier);
        OPItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static <B extends Block> DeferredBlock<B> registerEggBlock(String name, Supplier<B> supplier) {
        DeferredBlock<B> block = BLOCKS.register(name, supplier);
        OPItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        BLOCK_TRANSLATIONS.add(block);
        EGG_BLOCKS.add(block);
        return block;
    }

}
