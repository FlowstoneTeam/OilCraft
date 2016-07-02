package imdutch21.oilcraft.recipe;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;

/**
 * Created by Bart on 12/02/2016.
 */
public class OCMaterials extends Material{
    public static final Material OIL = (new MaterialLiquid(MapColor.BLACK));

    public OCMaterials(MapColor color) {
        super(color);
    }
}
