Stream.of(
Block.box(12, 0, 0, 16, 2, 4),
Block.box(-16, 0, 0, -12, 2, 4),
Block.box(-16, 0, 12, -12, 2, 16),
Block.box(12, 0, 12, 16, 2, 16),
Block.box(12, 8, 12, 16, 10, 16),
Block.box(-16, 8, 12, -12, 10, 16),
Block.box(-16, 8, 0, -12, 10, 4),
Block.box(12, 8, 0, 16, 10, 4),
Block.box(12, 30, 0, 16, 32, 4),
Block.box(-16, 30, 0, -12, 32, 4),
Block.box(-16, 30, 12, -12, 32, 16),
Block.box(12, 30, 12, 16, 32, 16),
Block.box(-15, 0, 1, 15, 32, 15)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();