package com.barnacle.obsolete_prehistory.mixin;

import com.barnacle.obsolete_prehistory.registry.OPBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {

    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void obsoletePrehistory$allowCustomEggBlocks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        BlockEntityType<?> self = (BlockEntityType<?>) (Object) this;

        var registryKey = BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(self);
        if (registryKey != null && registryKey.getNamespace().equals("unusual_prehistory") && registryKey.getPath().equals("egg_block_entity")) {
            boolean isOurEgg = OPBlocks.EGG_BLOCKS.stream().anyMatch(supplier -> supplier.get() == state.getBlock());
            if (isOurEgg) {
                cir.setReturnValue(true);
            }
        }
    }
}