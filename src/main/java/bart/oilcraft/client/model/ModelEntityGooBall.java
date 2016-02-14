package bart.oilcraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Bart on 14/02/2016.
 */
public class ModelEntityGooBall extends ModelBase {
    //fields
    ModelRenderer back;
    ModelRenderer bottom;
    ModelRenderer base;
    ModelRenderer top;
    ModelRenderer front;
    ModelRenderer left;
    ModelRenderer right;

    public ModelEntityGooBall() {
        textureWidth = 128;
        textureHeight = 64;

        back = new ModelRenderer(this, 70, 0);
        back.addBox(-6F, -5F, -1F, 12, 10, 1);
        back.setRotationPoint(0F, 17F, 8F);
        back.setTextureSize(128, 64);
        back.mirror = true;
        setRotation(back, 0F, 0F, 0F);
        bottom = new ModelRenderer(this, 0, 51);
        bottom.addBox(-6F, 0F, -6F, 12, 1, 12);
        bottom.setRotationPoint(0F, 23F, 0F);
        bottom.setTextureSize(128, 64);
        bottom.mirror = true;
        setRotation(bottom, 0F, 0F, 0F);
        base = new ModelRenderer(this, 0, 0);
        base.addBox(-7F, 0F, -7F, 14, 12, 14);
        base.setRotationPoint(0F, 11F, 0F);
        base.setTextureSize(128, 64);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        top = new ModelRenderer(this, 0, 51);
        top.addBox(-6F, 0F, -6F, 12, 1, 12);
        top.setRotationPoint(0F, 10F, 0F);
        top.setTextureSize(128, 64);
        top.mirror = true;
        setRotation(top, 0F, 0F, 0F);
        front = new ModelRenderer(this, 101, 0);
        front.addBox(-6F, -5F, 0F, 12, 10, 1);
        front.setRotationPoint(0F, 17F, -8F);
        front.setTextureSize(128, 64);
        front.mirror = true;
        setRotation(front, 0F, 0F, 0F);
        left = new ModelRenderer(this, 0, 27);
        left.addBox(-1F, -5F, -6F, 1, 10, 12);
        left.setRotationPoint(8F, 17F, 0F);
        left.setTextureSize(128, 64);
        left.mirror = true;
        setRotation(left, 0F, 0F, 0F);
        right = new ModelRenderer(this, 0, 27);
        right.addBox(0F, -5F, -6F, 1, 10, 12);
        right.setRotationPoint(-8F, 17F, 0F);
        right.setTextureSize(128, 64);
        right.mirror = true;
        setRotation(right, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        back.render(f5);
        bottom.render(f5);
        base.render(f5);
        top.render(f5);
        front.render(f5);
        left.render(f5);
        right.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
