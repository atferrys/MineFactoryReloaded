package powercrystals.minefactoryreloaded.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import powercrystals.minefactoryreloaded.Tags;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.TransformerExclusions({"powercrystals.minefactoryreloaded.asm."})
@IFMLLoadingPlugin.SortingIndex(Integer.MAX_VALUE >>> 1)
@IFMLLoadingPlugin.Name(Tags.MOD_NAME + " ASM Plugin")
public class LoadingPlugin implements IFMLLoadingPlugin, IEarlyMixinLoader {

	@Override
	public List<String> getMixinConfigs() {
		return Collections.singletonList("mixins." + Tags.MOD_ID + ".json");
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[] { WorldTransformer.class.getName() };
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Nullable
	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
