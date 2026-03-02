package org.autismoschosen.mc.btwo;

public class BeaconBoostConfig {
	// how many blocks are needed before range is increased?
	// default = 2
	// i.e. for every 2 netherite blocks, add 6 two beacon range radius.
	public int groupSize = 2;

	// how much range should given block type add?
	// defaults: 0, 1, 2, 4, 6
	public int weightIron = 0;
	public int weightGold = 1;
	public int weightEmerald = 2;
	public int weightDiamond = 4;
	public int weightNetherite = 6;

	// what is the absolute maximum range a beacon should have?
	// default 500.
	public int maxRange = 500;

	// should vertical beacon range extend infinitely in both y directions? (true)
	// or, should it mirror vanilla behavior? (false)
	// i.e. range beneath a beacon = radius of beacon
	// default true
	public boolean infiniteVertical = true;

	// should beacon effect apply when beacon is in unloaded chunk, given player is
	// still within beacon range?
	// default = true
	public boolean allowUnloadedChunks = true;
}
