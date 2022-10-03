Stream.of(
Block.box(1, 0, 1, 4, 2, 4),
Block.box(2, 2, 2, 4, 3, 4),
Block.box(12, 0, 1, 15, 2, 4),
Block.box(12, 2, 2, 14, 3, 4),
Block.box(2, 2, 12, 4, 3, 14),
Block.box(1, 0, 12, 4, 2, 15),
Block.box(12, 0, 12, 15, 2, 15),
Block.box(12, 2, 12, 14, 3, 14),
Block.box(1, 3, 1, 15, 6, 15),
Block.box(1, 6, 12, 15, 16, 15),
Block.box(1, 9, 1, 3, 11, 12),
Block.box(1, 6, 2, 3, 9, 4),
Block.box(13, 9, 1, 15, 11, 12),
Block.box(13, 6, 2, 15, 9, 4)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();