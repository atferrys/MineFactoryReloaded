package powercrystals.minefactoryreloaded.gui.client.font;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.io.InputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

import powercrystals.minefactoryreloaded.MFRProps;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class PrcFontRenderer extends FontRenderer {

	private static final String FONT_TEXTURE_FOLDER = MFRProps.TEXTURE_FOLDER + "font/";
	private static final ResourceLocation[] UNICODE_PAGE_LOCATIONS = new ResourceLocation[256];

	public PrcFontRenderer(GameSettings settings, TextureManager resourceManager, boolean unicode) {
		super(settings, new ResourceLocation(FONT_TEXTURE_FOLDER + "ascii.png"), resourceManager, unicode);
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	@Override
	protected void readGlyphSizes() {

		try (InputStream inputstream = Minecraft.getMinecraft()
				.getResourceManager()
				.getResource(new ResourceLocation(FONT_TEXTURE_FOLDER + "glyph_sizes.bin"))
				.getInputStream()) {

			inputstream.read(this.glyphWidth);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	@Nonnull
	protected ResourceLocation getUnicodePageLocation(int i) {

		if(UNICODE_PAGE_LOCATIONS[i] == null) {
			UNICODE_PAGE_LOCATIONS[i] = new ResourceLocation(String.format(FONT_TEXTURE_FOLDER + "unicode_page_%02x.png", i));
		}

		return UNICODE_PAGE_LOCATIONS[i];

	}

}