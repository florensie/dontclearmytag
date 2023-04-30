package be.florens.dontclearmytag.mixin;

import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagFile;
import net.minecraft.tags.TagLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Mixin(TagLoader.class)
public class TagLoaderMixin {

    @Inject(method = "load", require = 1, locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "INVOKE", target = "Ljava/util/List;clear()V"))
    private void detectClear(ResourceManager resourceManager, CallbackInfoReturnable<Map> cir, Map map, Iterator var3, Map.Entry entry, ResourceLocation resourceLocation, String string, ResourceLocation resourceLocation2, Iterator var8, Resource resource, Reader reader, JsonElement jsonElement, List<TagLoader.EntryWithSource> list, TagFile tagFile) {
        LogUtils.getLogger().warn("Tag entries for {} cleared by {}", resourceLocation, resource.sourcePackId());
    }
}
