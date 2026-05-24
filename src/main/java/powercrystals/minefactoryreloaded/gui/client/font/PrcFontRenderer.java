package powercrystals.minefactoryreloaded.gui.client.font;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

import powercrystals.minefactoryreloaded.MFRProps;

@SideOnly(Side.CLIENT)
public class PrcFontRenderer extends FontRenderer {

	public static final String FONT_TEXTURE_FOLDER = MFRProps.TEXTURE_FOLDER + "font/";

	public PrcFontRenderer(GameSettings settings, TextureManager resourceManager, boolean unicode) {
		super(settings, new ResourceLocation(FONT_TEXTURE_FOLDER + "ascii.png"), resourceManager, unicode);
	}

}
