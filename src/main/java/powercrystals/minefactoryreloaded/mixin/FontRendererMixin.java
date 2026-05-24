package powercrystals.minefactoryreloaded.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import powercrystals.minefactoryreloaded.gui.client.font.PrcFontRenderer;

import java.io.IOException;
import java.io.InputStream;

import static powercrystals.minefactoryreloaded.gui.client.font.PrcFontRenderer.FONT_TEXTURE_FOLDER;

@Mixin(FontRenderer.class)
public abstract class FontRendererMixin {

    @Final
    @Shadow
    protected byte[] glyphWidth;

    @Unique
    private static final ResourceLocation[] prc$unicodePageLocations = new ResourceLocation[256];

    @Inject(method = "readGlyphSizes", at = @At("HEAD"), cancellable = true)
    private void prc$readGlyphSizes(CallbackInfo ci) {

        if (!((Object) this instanceof PrcFontRenderer)) {
            return;
        }

        ci.cancel();

        try (InputStream inputstream = Minecraft.getMinecraft()
                .getResourceManager()
                .getResource(new ResourceLocation(FONT_TEXTURE_FOLDER + "glyph_sizes.bin"))
                .getInputStream()) {

            inputstream.read(this.glyphWidth);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Inject(method = "getUnicodePageLocation", at = @At("HEAD"), cancellable = true)
    private void prc$getUnicodePageLocation(int i, CallbackInfoReturnable<ResourceLocation> cir) {

        if (!((Object) this instanceof PrcFontRenderer)) {
            return;
        }

        if (prc$unicodePageLocations[i] == null) {
            prc$unicodePageLocations[i] = new ResourceLocation(String.format(FONT_TEXTURE_FOLDER + "unicode_page_%02x.png", i));
        }

        cir.setReturnValue(prc$unicodePageLocations[i]);

    }

}