package com.barnacle.obsolete_prehistory.entity;

import com.barl_inc.unusual_prehistory.entity.base.SchoolingPrehistoricMob;
import com.barl_inc.unusual_prehistory.entity.utils.UP2MobUtils;
import com.barnacle.obsolete_prehistory.registry.OPEntities;
import com.barnacle.obsolete_prehistory.registry.OPItems;
import com.platypushasnohat.sinew.client.animation.SmoothAnimationState;
import com.platypushasnohat.sinew.entity.ai.control.SwimmingMoveControl;
import com.platypushasnohat.sinew.entity.ai.goal.AquaticPanicGoal;
import com.platypushasnohat.sinew.entity.utils.SwimPitch;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import org.jetbrains.annotations.Nullable;

public class Antarctilamna extends SchoolingPrehistoricMob implements Bucketable {

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Antarctilamna.class, EntityDataSerializers.BOOLEAN);

    public final SmoothAnimationState swimAnimationState = new SmoothAnimationState();
    public final SmoothAnimationState flopAnimationState = new SmoothAnimationState();

    public SwimPitch swimPitch = new SwimPitch(this, 85.0F, 20.0F);

    public Antarctilamna(EntityType<? extends Antarctilamna> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SwimmingMoveControl(this, 85, 10, 0.02F);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new AquaticPanicGoal(this, 1.7D));
        this.goalSelector.addGoal(5, new RandomSwimmingGoal(this, 1.0D, 10));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FROM_BUCKET, false);
    }

    @Override
    public int getMaxSchoolSize() {
        return 4;
    }

    @Override
    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isInWaterOrBubble(), this.tickCount);
        this.swimIdleAnimationState.animateWhen(this.isInWaterOrBubble(), this.tickCount);
        this.flopAnimationState.animateWhen(!this.isInWaterOrBubble(), this.tickCount);
    }

    @Override
    public void calculateEntityAnimation(boolean flying) {
        float length = (float) Mth.length(this.getX() - this.xo, this.getY() - this.yo, this.getZ() - this.zo);
        float speed = Math.min(length * (this.isBaby() ? 5.0F : 10.0F), 1.0F);
        this.walkAnimation.update(speed, 0.4F);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            this.swimPitch.tick();
        }
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ItemTags.FISHES);
    }

    @Nullable
    @Override
    public Antarctilamna getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return OPEntities.ANTARCTILAMNA.get().create(level);
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
        this.entityData.set(FROM_BUCKET, fromBucket);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(OPItems.ANTARCTILAMNA_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_EMPTY_FISH;
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        UP2MobUtils.savePrehistoricDataToBucket(this, bucket);
    }

    @Override
    public void loadFromBucketTag(CompoundTag compoundTag) {
        UP2MobUtils.loadPrehistoricDataFromBucket(this, compoundTag);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.COD_HURT;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.FISH_SWIM;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
    }
}
