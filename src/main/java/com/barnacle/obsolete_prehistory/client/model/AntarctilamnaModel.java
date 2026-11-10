package com.barnacle.obsolete_prehistory.client.model;

import com.barnacle.obsolete_prehistory.client.model.animation.PaleobalistumAnimations;
import com.barnacle.obsolete_prehistory.entity.Antarctilamna;
import com.platypushasnohat.sinew.client.model.entity.SinewEntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;

public class AntarctilamnaModel extends SinewEntityModel<Antarctilamna> {

    private final ModelPart root;
    private final ModelPart swim_control;
    private final ModelPart head;
    private final ModelPart tail;
    private final ModelPart tail_end;

    public AntarctilamnaModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root.getChild("root");
        this.swim_control = this.root.getChild("swim_control");
        this.head = this.swim_control.getChild("head");
        this.tail = this.swim_control.getChild("tail");
        this.tail_end = this.tail.getChild("tail_end");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    protected void setupAnimations(Antarctilamna entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
        this.animateWalkSmooth(entity.swimAnimationState, PaleobalistumAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
        this.animateIdleSmooth(entity.idleAnimationState, PaleobalistumAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
        this.animateSmooth(entity.flopAnimationState, PaleobalistumAnimations.FLOP, ageInTicks, partialTicks);
        this.swim_control.xRot += entity.swimPitch.getPitch(partialTicks) * Mth.DEG_TO_RAD;
        this.swim_control.zRot += -entity.swimPitch.getRoll(partialTicks) * Mth.DEG_TO_RAD;
    }



    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -10.0F, -10.0F, 10.0F, 10.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(22, 56).addBox(-4.0F, 0.0F, 2.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(32, 59).addBox(4.0F, 0.0F, 2.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition backfins = body.addOrReplaceChild("backfins", CubeListBuilder.create(), PartPose.offset(0.0F, -10.0F, 0.0F));

        PartDefinition front_fin = backfins.addOrReplaceChild("front_fin", CubeListBuilder.create().texOffs(22, 45).addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

        PartDefinition back_fin = backfins.addOrReplaceChild("back_fin", CubeListBuilder.create().texOffs(42, 59).addBox(0.0F, -4.0F, -2.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.0F));

        PartDefinition head = swim_control.addOrReplaceChild("head", CubeListBuilder.create().texOffs(34, 44).addBox(-3.0F, -4.0F, -8.0F, 6.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(56, 21).addBox(-3.0F, -4.0F, -10.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -10.0F));

        PartDefinition tail = swim_control.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(32, 28).addBox(-2.5F, -2.0F, 0.0F, 5.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(56, 9).addBox(0.0F, 2.0F, 3.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 8.0F));

        PartDefinition tail_end = tail.addOrReplaceChild("tail_end", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(0.0F, 1.0F, 0.0F, 0.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 11.0F));

        PartDefinition left_fin = swim_control.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(56, 0).addBox(0.0F, 0.0F, -2.0F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.0F, -6.0F));

        PartDefinition right_fin = swim_control.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(56, 5).addBox(-7.0F, 0.0F, -2.0F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 0.0F, -6.0F));

        return LayerDefinition.create(meshdefinition, 80, 80);
    }

    public static LayerDefinition createBabyBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -5.0F, 8.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(18, 35).addBox(-3.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(26, 35).addBox(3.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition backfins = body.addOrReplaceChild("backfins", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition front_fin = backfins.addOrReplaceChild("front_fin", CubeListBuilder.create().texOffs(18, 27).addBox(0.0F, -4.0F, -2.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

        PartDefinition back_fin = backfins.addOrReplaceChild("back_fin", CubeListBuilder.create().texOffs(34, 35).addBox(0.0F, -3.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

        PartDefinition head = swim_control.addOrReplaceChild("head", CubeListBuilder.create().texOffs(26, 26).addBox(-3.0F, -4.0F, -5.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -5.0F));

        PartDefinition tail = swim_control.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(26, 14).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(34, 0).addBox(0.0F, 1.0F, 2.0F, 0.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 4.0F));

        PartDefinition tail_end = tail.addOrReplaceChild("tail_end", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 27).addBox(0.0F, 1.0F, 0.0F, 0.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 9.0F));

        PartDefinition right_fin = swim_control.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(34, 10).addBox(-5.0F, 0.0F, -1.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, -4.0F));

        PartDefinition left_fin = swim_control.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(34, 10).mirror().addBox(0.0F, 0.0F, -1.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 0.0F, -4.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
