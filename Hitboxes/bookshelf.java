Stream.of(
Block.box(12, 0, 0, 16, 2, 4),
Block.box(-16, 0, 0, -12, 2, 4),
Block.box(-16, 0, 12, -12, 2, 16),
Block.box(12, 0, 12, 16, 2, 16),
Block.box(12, 15, 12, 16, 17, 16),
Block.box(12, 15, 0, 16, 17, 4),
Block.box(-16, 15, 0, -12, 17, 4),
Block.box(-16, 15, 12, -12, 17, 16),
Block.box(-16, 30, 12, -12, 32, 16),
Block.box(12, 30, 12, 16, 32, 16),
Block.box(12, 30, 0, 16, 32, 4),
Block.box(-15, 0, 1, 15, 32, 15)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();