Stream.of(
Block.box(12, 0, 0, 16, 2, 4),
Block.box(12, 5, 0, 16, 7, 4),
Block.box(12, 5, 12, 16, 7, 16),
Block.box(12, 0, 12, 16, 2, 16),
Block.box(-16, 0, 12, -12, 2, 16),
Block.box(-16, 5, 12, -12, 7, 16),
Block.box(-16, 5, 0, -12, 7, 4),
Block.box(-16, 0, 0, -12, 2, 4),
Block.box(-15, 2, 1, -13, 5, 3),
Block.box(-15, 2, 13, -13, 5, 15),
Block.box(13, 2, 13, 15, 5, 15),
Block.box(13, 2, 1, 15, 5, 3),
Block.box(-15, 5, 1, 15, 7, 15)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();