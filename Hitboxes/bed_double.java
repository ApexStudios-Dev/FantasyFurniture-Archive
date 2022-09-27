Stream.of(
Block.box(-16, 0, 0, -12, 2, 4),
Block.box(-16, 0, 28, -12, 2, 32),
Block.box(12, 0, 28, 16, 2, 32),
Block.box(12, 0, 0, 16, 2, 4),
Block.box(12, 13, 0, 16, 15, 4),
Block.box(-16, 13, 0, -12, 15, 4),
Block.box(-16, 12, 28, -12, 14, 32),
Block.box(12, 12, 28, 16, 14, 32),
Block.box(13, 2, 29, 15, 12, 31),
Block.box(-15, 2, 29, -13, 12, 31),
Block.box(-15, 2, 1, -13, 13, 3),
Block.box(13, 2, 1, 15, 13, 3),
Block.box(-13, 3, 1, 13, 12, 3),
Block.box(-13, 3, 29, 13, 11, 31),
Block.box(-15, 3, 3, 15, 8, 29)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();