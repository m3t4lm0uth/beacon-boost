# Beacon Boost

**Beacon Boost** is a Fabric mod that makes beacon range scale with the materials you use to build the pyramid, using only vanilla mechanics. It works in singleplayer, and as a server‑side-only mod.

## Features

### Default behavior

By default, Beacon Boost counts base blocks in small groups and adds extra range based on the material. All of these values (group size, per‑material bonuses, max range, vertical behavior, unloaded‑chunk behavior) can be tuned in config/beaconboost.json.

The default behavior is set as follows:

- Group size: 2 blocks

- +0 blocks of range per group of iron blocks
- +1 block of range per group of gold blocks
- +2 blocks of range per group of emerald blocks
- +4 blocks of range per group of diamond blocks
- +6 blocks of range per group of netherite blocks

- Maximum horizontal range: 500 blocks (radius)

- Vertical range now extends infinitely downward as well as upward.

### Additional notes

Beacon Boost is built with servers in mind: it tracks beacons efficiently, continues to apply effects from cached data even when beacon chunks are unloaded (given player is still within the radius), and adds no client‑side UI requirement.

Target version: Minecraft 1.21.11+ with Fabric. Earlier 1.21.x versions may work but are not officially tested.

Currently, only valid vanilla beacon base materials are supported.

## Feedback and feature requests
Found a bug, have a suggestion, or want a new config option?

Please feel free to open an issue on the GitHub repository that describes your setup (Minecraft version, Fabric/Loader version, other relevant mods) and what you’d like to see changed. I actively use these issues to plan fixes and new features, so don’t hesitate to file bug reports, balance feedback, or feature requests. :)

## Inspiration

This mod is inspired by the datapack **“Increased Range Beacons”** by brisai:  
https://www.planetminecraft.com/data-pack/increased-range-beacons-multiplayer-compatible/

BeaconBoost is a from‑scratch reimplementation in Java for the Fabric modding ecosystem. Please support the original datapack and its author.

## License

This project is licensed under the GNU General Public License v3.0 (GPL-3.0).  
See the [LICENSE](LICENSE) file for details.
