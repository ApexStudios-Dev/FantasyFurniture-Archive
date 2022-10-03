Stream.of(
Block.box(-15, 0, 1, 15, 11, 15),
Block.box(12, 9, 0, 16, 11, 4),
Block.box(-16, 9, 0, -12, 11, 4),
Block.box(-16, 9, 12, -12, 11, 16),
Block.box(12, 9, 12, 16, 11, 16)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();