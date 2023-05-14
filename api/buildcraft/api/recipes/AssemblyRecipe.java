package buildcraft.api.recipes;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.tags.ITagManager;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * @deprecated TEMPORARY CLASS DO NOT USE!
 */
@Deprecated
public abstract class AssemblyRecipe implements Comparable<AssemblyRecipe>, IForgeRegistry<AssemblyRecipe> {
    private ResourceLocation key;

    /**
     * The outputs this recipe can generate with any of the given inputs
     * @param inputs Current ingredients in the assembly table
     * @return A Set containing all possible outputs given the given inputs or an empty one if nothing can be assembled from the given inputs
     */
    public abstract Set<ItemStack> getOutputs(NonNullList<ItemStack> inputs);

    /**
     * Used to determine all outputs from this recipe for recipe previews (guide book and/or JEI)
     */
    public abstract Set<ItemStack> getOutputPreviews();

    /**
     * Used to determine what items to use up for the given output
     * @param output The output we want to know the inputs for, only ever called using stacks obtained from getOutputs or getOutputPreviews
     */
    public abstract Set<IngredientStack> getInputsFor(@Nonnull ItemStack output);

    /**
     * Used to determine how much MJ is required to asemble the given output item
     * @param output The output we want to know the MJ cost for, only ever called using stacks obtained from getOutputs or getOutputPreviews
     */
    public abstract long getRequiredMicroJoulesFor(@Nonnull ItemStack output);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AssemblyRecipe that = (AssemblyRecipe) o;

        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public int compareTo(AssemblyRecipe o) {
        return key.toString().compareTo(o.key.toString());
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return key;
    }

    public Class<AssemblyRecipe> getRegistryType() {
        return AssemblyRecipe.class;
    }

    @Override
    public ResourceKey<Registry<AssemblyRecipe>> getRegistryKey() {
        return null;
    }

    @Override
    public void register(String key, AssemblyRecipe value) {
        this.key = new ResourceLocation(key);
    }

    @Override
    public void register(ResourceLocation key, AssemblyRecipe value) {
        this.key = key;
    }

    @Override
    public boolean containsKey(ResourceLocation key) {
        return key == this.key;
    }

    @Override
    public boolean containsValue(AssemblyRecipe value) {
        return value == this;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public @org.jetbrains.annotations.Nullable AssemblyRecipe getValue(ResourceLocation key) {
        return key == this.key ? null : this;
    }

    @Override
    public @org.jetbrains.annotations.Nullable ResourceLocation getKey(AssemblyRecipe value) {
        return value == this ? null : this.key;
    }

    @Override
    public @org.jetbrains.annotations.Nullable ResourceLocation getDefaultKey() {
        return this.key;
    }

    @Override
    public @NotNull Optional<ResourceKey<AssemblyRecipe>> getResourceKey(AssemblyRecipe value) {
        return Optional.empty();
    }

    @Override
    public @NotNull Set<ResourceLocation> getKeys() {
        HashSet<ResourceLocation> set = new HashSet<>();
        set.add(this.key);
        return set;
    }

    @Override
    public @NotNull Collection<AssemblyRecipe> getValues() {
        ArrayList<AssemblyRecipe> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
    public @NotNull Set<Map.Entry<ResourceKey<AssemblyRecipe>, AssemblyRecipe>> getEntries() {
        HashMap<ResourceKey<AssemblyRecipe>, AssemblyRecipe> map = new HashMap<>();
        // map.put(this.key, this);
        // TODO : Fix
        return map.entrySet();
    }

    @Override
    public @NotNull Codec<AssemblyRecipe> getCodec() {
        return null;
        // TODO : Fix
    }

    @Override
    public @NotNull Optional<Holder<AssemblyRecipe>> getHolder(ResourceKey<AssemblyRecipe> key) {
        // TODO : Fix
        return Optional.empty();
    }

    @Override
    public @NotNull Optional<Holder<AssemblyRecipe>> getHolder(ResourceLocation location) {
        // TODO : Fix
        return Optional.empty();
    }

    @Override
    public @NotNull Optional<Holder<AssemblyRecipe>> getHolder(AssemblyRecipe value) {
        // TODO : Fix
        return Optional.empty();
    }

    @Override
    public @org.jetbrains.annotations.Nullable ITagManager<AssemblyRecipe> tags() {
        // TODO : Fix
        return null;
    }

    @Override
    public @NotNull Optional<Holder.Reference<AssemblyRecipe>> getDelegate(ResourceKey<AssemblyRecipe> rkey) {
        // TODO : Fix
        return Optional.empty();
    }

    @Override
    public Holder.@NotNull Reference<AssemblyRecipe> getDelegateOrThrow(ResourceKey<AssemblyRecipe> rkey) {
        // TODO : Fix
        return null;
    }

    @Override
    public @NotNull Optional<Holder.Reference<AssemblyRecipe>> getDelegate(ResourceLocation key) {
        // TODO : Fix
        return Optional.empty();
    }

    @Override
    public Holder.@NotNull Reference<AssemblyRecipe> getDelegateOrThrow(ResourceLocation key) {
        // TODO : Fix
        return null;
    }

    @Override
    public @NotNull Optional<Holder.Reference<AssemblyRecipe>> getDelegate(AssemblyRecipe value) {
        // TODO : Fix
        return Optional.empty();
    }

    @Override
    public Holder.@NotNull Reference<AssemblyRecipe> getDelegateOrThrow(AssemblyRecipe value) {
        // TODO : Fix
        return null;
    }

    @Override
    public <T> T getSlaveMap(ResourceLocation slaveMapName, Class<T> type) {
        // TODO : Fix
        return null;
    }

    @NotNull
    @Override
    public Iterator<AssemblyRecipe> iterator() {
        // TODO : Fix
        return null;
    }
}
