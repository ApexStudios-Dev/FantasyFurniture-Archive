Stream.of(
Block.box(-16, 0, 0, -13, 2, 3),
Block.box(-16, 0, 13, -13, 2, 16),
Block.box(13, 0, 13, 16, 2, 16),
Block.box(13, 0, 0, 16, 2, 3),
Block.box(13, 14, 0, 16, 16, 3),
Block.box(13, 14, 13, 16, 16, 16),
Block.box(-16, 14, 13, -13, 16, 16),
Block.box(-16, 14, 0, -13, 16, 3),
Block.box(-15, 0, 1, 15, 16, 15)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();