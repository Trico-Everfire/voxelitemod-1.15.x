package com.tricoeverfire.voxelite.init;

import com.tricoeverfire.voxelite.Main;
import com.tricoeverfire.voxelite.util.Reference;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID);
	
	public static final RegistryObject<SoundEvent> SLUGHURT = SOUNDS.register("entity.slug.hurt", ()->
		new SoundEvent(Main.location("entity.slug.hurt"))
	);
	
	public static final RegistryObject<SoundEvent> SLUGAMBIENT = SOUNDS.register("entity.slug.ambient", ()->
	new SoundEvent(Main.location("entity.slug.ambient"))
	);
	
	public static final RegistryObject<SoundEvent> SLUGSING = SOUNDS.register("entity.slug.sing", ()->
	new SoundEvent(Main.location("entity.slug.sing"))
	);
	public static final RegistryObject<SoundEvent> SLUGDEATH = SOUNDS.register("entity.slug.death", ()->
	new SoundEvent(Main.location("entity.slug.death"))
	);
	
	public static final RegistryObject<SoundEvent> SKELEBAT_TAKEOFF = SOUNDS.register("entity.skelebat.takeoff", ()->
	new SoundEvent(Main.location("entity.skelebat.takeoff"))
	);
}
