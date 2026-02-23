# Beacon Boost

**Beacon Boost** is a Fabric mod that extends vanilla beacon range depending on the materials used in the beacon base.

## Features

### Default behavior

BeaconBoost increases beacon range by counting base blocks in groups and applying different bonuses per material. The defaults are set as follows:

- +0 blocks of range per group of iron blocks  
- +1 block of range per group of gold blocks  
- +2 blocks of range per group of emerald blocks  
- +6 blocks of range per group of diamond blocks  
- +8 blocks of range per group of netherite blocks  

- Group size: 2 blocks  

- Absolute maximum horizontal range: 500 blocks (radius)  

- The default beacon range now extends infinitely downward as well as upward.

## Additional notes

- Currently, only valid vanilla beacon base materials are considered.
- Beacons can continue applying their effects even when their chunks are unloaded. This may not persist after exiting and re‑entering the world.
- The mod targets Minecraft 1.21.11+ with Fabric; it may work on earlier versions but they have not yet been tested.

## Inspiration

This mod is inspired by the datapack **“Increased Range Beacons”** by brisai:  
https://www.planetminecraft.com/data-pack/increased-range-beacons-multiplayer-compatible/

BeaconBoost is a from‑scratch reimplementation in Java for the Fabric modding ecosystem. Please support the original datapack and its author.

## License

This project is licensed under the GNU General Public License v3.0 (GPL-3.0).  
See the [LICENSE](LICENSE) file for details.
