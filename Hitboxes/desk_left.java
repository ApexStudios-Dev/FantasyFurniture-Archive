Stream.of(
Block.box(12, 0, 1, 15, 2, 4),
Block.box(12, 0, 12, 15, 2, 15),
Block.box(1, 0, 12, 4, 2, 15),
Block.box(1, 0, 1, 4, 2, 4),
Block.box(1, 12, 1, 4, 14, 4),
Block.box(1, 12, 12, 4, 14, 15),
Block.box(12, 12, 12, 15, 14, 15),
Block.box(12, 12, 1, 15, 14, 4),
Block.box(2, 0, 2, 14, 14, 14),
Block.box(-15, 0, 1, -11, 2, 5),
Block.box(-15, 12, 1, -11, 14, 5),
Block.box(-15, 12, 11, -11, 14, 15),
Block.box(-14, 2, 12, -12, 12, 14),
Block.box(-14, 2, 2, -12, 12, 4),
Block.box(-16, 14, 0, 16, 16, 16),
Block.box(-15, 0, 11, -11, 2, 15)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();