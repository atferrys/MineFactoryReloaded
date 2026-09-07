package powercrystals.minefactoryreloaded.modcompat.forgemultiparts;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.lib.vec.uv.IconTransformation;
import codechicken.microblock.BlockMicroMaterial;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import powercrystals.minefactoryreloaded.render.block.FactoryGlassRenderer;
import scala.collection.JavaConversions;
import scala.collection.Seq;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class FactoryGlassMicroMaterial extends BlockMicroMaterial {

	private IconTransformation base;
	private IconTransformation highlights;
	private IconTransformation frame;

	public FactoryGlassMicroMaterial(IBlockState state, String materialID) {
		super(state, materialID);
	}

	@Override
	public void loadIcons() {

		base = new IconTransformation(FactoryGlassRenderer.spriteSheet.getSprite(FactoryGlassRenderer.BASE));
		highlights = new IconTransformation(FactoryGlassRenderer.spriteSheet.getSprite(FactoryGlassRenderer.HIGHLIGHTS));
		frame = new IconTransformation(FactoryGlassRenderer.spriteSheet.getSprite(FactoryGlassRenderer.FULL_FRAME));

		// Set texture used for breaking particles, could also override #getBreakingIcon, but I'm not sure
		// if this is being used anywhere else, so I'm gonna use this random private function. WHO EVEN USES SCALA FOR MODS???
		pIconT_$eq(frame);

	}

	@Override
	public Seq<Seq<IVertexOperation>> getMicroRenderOps(Vector3 pos, int side, BlockRenderLayer layer, Cuboid6 bounds) {
		return JavaConversions.asScalaBuffer(
				Arrays.asList(
						layerOps(pos, layer, base, getColour(layer)),
						layerOps(pos, layer, highlights, null),
						layerOps(pos, layer, frame, null)
				)
		).toSeq();
	}

	private Seq<IVertexOperation> layerOps(Vector3 pos, BlockRenderLayer layer, IconTransformation icon, Integer color) {

		List<IVertexOperation> ops = new ArrayList<>();

		ops.add(pos.translation());
		ops.add(icon);

		if(color != null) {
			ops.add(new ColourMultiplier(color));
		}

		if(layer != null) {
			ops.add(CCRenderState.instance().lightMatrix);
		}

		return JavaConversions.asScalaBuffer(ops).toSeq();

	}

}
