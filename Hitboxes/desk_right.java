Stream.of(
Block.box(-15, 0, 1, -12, 2, 4),
Block.box(-15, 0, 12, -12, 2, 15),
Block.box(-4, 0, 12, -1, 2, 15),
Block.box(-4, 0, 1, -1, 2, 4),
Block.box(-4, 12, 1, -1, 14, 4),
Block.box(-4, 12, 12, -1, 14, 15),
Block.box(-15, 12, 12, -12, 14, 15),
Block.box(-15, 12, 1, -12, 14, 4),
Block.box(-14, 0, 2, -2, 14, 14),
Block.box(11, 0, 1, 15, 2, 5),
Block.box(11, 12, 1, 15, 14, 5),
Block.box(11, 12, 11, 15, 14, 15),
Block.box(12, 2, 12, 14, 12, 14),
Block.box(12, 2, 2, 14, 12, 4),
Block.box(-16, 14, 0, 16, 16, 16),
Block.box(11, 0, 11, 15, 2, 15)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();