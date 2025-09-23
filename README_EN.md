# Worn Tear

Want durability to affect tool efficiency and weapon damage? This mod provides a convenient configuration file that allows you to easily set how an item's performance changes with durability loss based on customizable curves.

### Usage Example

Create `config/WornTear/example.json`:

```json
[
  {
    "match": "minecraft:wooden_axe",
    "rule": "4+[0,0]*0"
  },
  {
    "match": "minecraft:shears",
    "rule": "0+[0,1]*-20"
  },
  {
    "match": "minecraft:iron_shovel",
    "rule": "0.3+[0.4,0.5]*0.6"
  }
]
```

- The first rule makes the wooden axe always have 400% efficiency.
- The second rule makes shears have -2000% efficiency at full durability, increasing linearly as durability decreases. At zero durability, efficiency becomes 0%.
- The third rule demonstrates a more typical configuration: 0.3 means the iron shovel always has a base efficiency of 30%. When durability is between 40% and 50%, a linear change occurs:
    - At or below 40% durability, efficiency remains 30%.
    - At 45% durability, efficiency is 0.3 + 0.5 * 0.6 = 60%.
    - At or above 50% durability, efficiency stabilizes at 0.3 + 0.6 = 90%.

You can enable tooltip coefficient displays in the configuration file to visualize these effects.

The efficiency changes mentioned above apply to both block breaking speed and weapon damage.

### Planned Features
- Durability affecting armor toughness.
- More detailed and customizable configuration options.