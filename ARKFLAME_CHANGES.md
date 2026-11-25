# ArkFlame/MCProtocolLib changes

Fork of `GeyserMC/MCProtocolLib` at `65efb9da`, used by `ArkFlame/Geyser`.

## Published artifact

JitPack coordinates (tag `af-26.3-codecfix-1`):

```kotlin
implementation("com.github.ArkFlame.MCProtocolLib:protocol:af-26.3-codecfix-1")
```

`jitpack.yml` selects JDK 21. The `text-serializer-nbt` artifact is published by JitPack alongside `protocol` and resolved transitively.

## Fixes on top of upstream `65efb9da`

| Upstream | Fix |
| --- | --- |
| PR #942 | 17 confirmed Minecraft 26.3 codec issue families: block-property list counts, optional range bounds, NBT predicates and click payloads, signed potion color presence (`PotionContents.hasCustomColor()` plus `OptionalInt` constructor/builder), partial chat masks, recipe groups, warden/guardian data, decimal debug-event positions (`Vector3d`), missing path type, nine particle payload writers, all 49 payload-free command argument types, chat-decoration wire IDs, bounded length readers/array allocations, and shared `LightUpdateData` codec. |
| #851 | Proxied clients no longer perform local DNS/SRV resolution; the destination host is left unresolved for proxy-side resolution. |
| #852 | Destination host is IDN-normalized with `IDN.toASCII` when a proxy is configured; handshake keeps the original domain. |
| #723 | SOCKS4/SOCKS5 (and HTTP) proxies can resolve the destination host. |
| #890 | All protocol hotbar buttons (0-40) and drag-button IDs added to `MoveToHotbarAction`/`FillStackAction`. |
| #745 | Chunk palette reads reject truncated entry counts and short long arrays instead of reading past the buffer. |
| #823 | Written chunk sections normalize palettes so biome/block palettes always have index 0 and correctly sized data arrays. |

## Verification

`./gradlew build --no-daemon --no-parallel` -> BUILD SUCCESSFUL in 3m 3s (protocol and text-serializer-nbt tests included).

`javap` on the JitPack jar confirms `PotionContents` has `hasCustomColor()` and the `OptionalInt` constructor. ArkFlame/Geyser compiles against this artifact, and its `PotionContentsHashTest` passes 2/2 tests.
